package br.com.supera.game.db;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/* CONSTRUCTORS */
	public AbstractEntity() {
		super();
	}

	/* GETTERS AND SETTERS */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
