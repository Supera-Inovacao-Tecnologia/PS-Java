package br.com.supera.game.store;

import java.math.BigDecimal;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;

import br.com.supera.game.db.AbstractEntity;

@Entity
public class Product extends AbstractEntity {

	private String name;

	private BigDecimal price;

	private short score;

	private String image;

	//GETTERS/SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public short getScore() {
		return score;
	}

	public void setScore(short score) {
		this.score = score;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", score=" + score + ", image=" + image + "]";
	}

	@Generated("SparkTools")
	private Product(Builder builder) {
		this.name = builder.name;
		this.price = builder.price;
		this.score = builder.score;
		this.image = builder.image;
	}
	/**
	 * Creates builder to build {@link Product}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Product}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String name;
		private BigDecimal price;
		private short score;
		private String image;

		private Builder() {
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withPrice(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder withScore(short score) {
			this.score = score;
			return this;
		}

		public Builder withImage(String image) {
			this.image = image;
			return this;
		}

		public Product build() {
			return new Product(this);
		}
	}

	public Product() {
		super();
	}
	
}