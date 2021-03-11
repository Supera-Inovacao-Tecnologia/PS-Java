package br.com.supera.game.model;

import javax.annotation.Generated;
import javax.persistence.Entity;

import br.com.supera.game.db.AbstractEntity;
import br.com.supera.game.store.Cart;

@Entity
public class User extends AbstractEntity {
	
	private String name;
	
	private String lastName;

	private String email;

	private Cart cart;

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

		public User build() {
			return new User(this);
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}
	}
	
	/**
	 * Creates builder to build {@link User}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	public User() {
		super();
	}
	
	@Generated("SparkTools")
	private User(Builder builder) {
		this.name = builder.name;
		this.lastName = builder.lastName;
		this.email = builder.email;
	}


	public Cart getCart() {
		return cart;
	}


	public String getEmail() {
		return email;
	}


	public String getLastName() {
		return lastName;
	}


	public String getName() {
		return name;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
