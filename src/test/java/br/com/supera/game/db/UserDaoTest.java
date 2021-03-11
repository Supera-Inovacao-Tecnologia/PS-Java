package br.com.supera.game.db;

import static com.github.dbunit.rules.util.EntityManagerProvider.em;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dbunit.rules.DBUnitRule;
import com.github.dbunit.rules.api.configuration.DBUnit;
import com.github.dbunit.rules.api.dataset.DataSet;
import com.github.dbunit.rules.util.EntityManagerProvider;

import br.com.supera.game.model.User;

public class UserDaoTest{

	Logger LOGGER;
	
    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("productDS");  

    @Rule
	public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection()); 
	
    @Before
    public void initializeTestClass() {
    	 LOGGER = LoggerFactory.getLogger(this.getClass());
    }
    
    
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("users.yml") 
    public void readTest() {
		try {
			LOGGER.info("Testing READ operations");
			
			User p = new UserDao(em()).getById(12345);
			assertNotNull(p);
			
			User p2 = new UserDao(em()).getByField("name", "fakeName");
			assertNotNull(p2);
			
			List<User> pList = new UserDao(em()).getAll();
			assertNotNull(pList);
			
			LOGGER.info("Asserting size of the list of Users");
			assertNotEquals(pList.size(), 0);
			assertEquals(pList.size(), 1);
			
			LOGGER.info("Printing the result");
			LOGGER.info(pList.get(0).toString());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			fail(e.getMessage());
			
		}
					
    }
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("Users.yml") 
    public void writeTest() {
	try {	
			LOGGER.info("Testing WRITE operations");
			
			User p = User.builder()
					.withName("fakeName1")
					.withLastName("fakeLastName2")
					.withEmail("fakeEmail2")
					.build();
			
			UserDao UserDao = new UserDao(em());
			UserDao.persistAutoCommit(p);
			User p2 = UserDao.getByField("name", p.getName());
			
			LOGGER.info("Asserting that the inserted User and retrived have the same hashcode");
			assertEquals(p.hashCode(), p2.hashCode());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			fail(e.getMessage());
			
		}
    }
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("Users.yml") 
    public void updateTest() {
		try {
			LOGGER.info("Testing UPDATE operations");
			
			UserDao UserDao = new UserDao(em());
			User u = UserDao.getByField("name", "fakeName");
			assertNotNull(u);
			
			LOGGER.info("Updating email of {}", u.toString());
			String previousEmailValue = u.getEmail();
			Integer previousIdValue = u.getId();
			//should be certainly different
			u.setEmail(previousEmailValue+previousEmailValue);
			
			UserDao.mergeAutoCommit(u);
			
			User u2 = UserDao.getByField("name", u.getName());
			
			LOGGER.info("The inserted User and retrived one must have different values for updated field "
					+ "even though they has the same Id");
			assertEquals(u2.getId(), previousIdValue);
			assertNotEquals(previousEmailValue, u2.getEmail());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			fail(e.getMessage());
			
		}					
    }
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("Users.yml") 
    public void deleteTest() {
		try {
			List<User> uList = new UserDao(em()).getAll();
			assertNotNull(uList);
			int UserListInitialSize = uList.size();
			
			LOGGER.info("Testing DELETE operations");
			
			UserDao UserDao = new UserDao(em());
			String queryField = "fakeName";
			User u = UserDao.getByField("name", queryField);
			assertNotNull(u);
			
			LOGGER.info("Deleting {} ", u.toString());
			UserDao.remove(u);
			
			LOGGER.info("Asserting that the deleted User is not in the database anymore");
			User u2 = UserDao.getByField("name", queryField);
			assertNull(u2);
	
			LOGGER.info("Asserting that the size of the list of User has decreased");
			List<User> uList2 = new UserDao(em()).getAll();
			int finalSize = uList2.size();
			
			assertNotNull(uList2);
			assertEquals(UserListInitialSize-1, finalSize);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			fail(e.getMessage());
			
		}					
    }
}
