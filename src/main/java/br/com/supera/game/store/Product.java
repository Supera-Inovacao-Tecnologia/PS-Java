package br.com.supera.game.store;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

   @Id
   @GeneratedValue
   public long id;

   public String name;

   public BigDecimal price;

   public short score;

   public String image;

}