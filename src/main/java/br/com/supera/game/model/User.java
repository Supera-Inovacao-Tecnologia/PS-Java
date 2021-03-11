package br.com.supera.game.model;

import javax.persistence.Entity;

import br.com.supera.game.db.AbstractEntity;
import javax.annotation.Generated;

@Entity
public class User extends AbstractEntity {

	private String name;
	
	private String lastName;
	
	private String email;

	@Generated("SparkTools")
	private User(Builder builder) {
		this.name = builder.name;
		this.lastName = builder.lastName;
		this.email = builder.email;
	}
	
	public User() {
		super();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * Creates builder to build {@link User}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}


	/**
	 * Builder to build {@link User}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String name;
		private String lastName;
		private String email;

		private Builder() {
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
	
	
}
