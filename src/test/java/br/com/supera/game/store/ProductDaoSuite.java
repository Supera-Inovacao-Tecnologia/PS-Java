package br.com.supera.game.store;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.supera.game.service.ProductServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	ProductDaoTest.class,
	ProductServiceTest.class
	})
public class ProductDaoSuite {

}
