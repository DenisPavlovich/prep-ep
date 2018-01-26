package epam.pre.romanenko.entities;

import epam.pre.romanenko.store.annotations.FieldName;

import java.math.BigDecimal;

public class Animal extends Being {

    @FieldName("breed")
    protected String breed;

    public Animal() {
    }

    public Animal(BigDecimal price, String name, String breed) {
        super(price, name);
        this.breed = breed;
    }

    public Animal(Being being, String breed) {
        this(being.price, being.name, breed);
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return String.format("%s, breed:%s", super.toString(), breed);
    }

    public class AnimalBuilder {

        public AnimalBuilder setBreed(String breed) {
            Animal.this.breed = breed;
            return this;
        }

        public AnimalBuilder setPrice(BigDecimal price) {
            Animal.this.price = price;
            return this;
        }

        public AnimalBuilder setName(String name) {
            Animal.this.name = name;
            return this;
        }

        public Animal build() {
            return Animal.this;
        }
    }
}