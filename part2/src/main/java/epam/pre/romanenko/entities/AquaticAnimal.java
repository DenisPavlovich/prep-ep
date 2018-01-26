package epam.pre.romanenko.entities;

import epam.pre.romanenko.store.annotations.FieldName;

import java.math.BigDecimal;

public class AquaticAnimal extends Animal {

    @FieldName("number.of.fins")
    private Integer numberOfFins;

    public AquaticAnimal() {
    }

    public AquaticAnimal(BigDecimal price, String name, String breed, Integer numberOfFins) {
        super(price, name, breed);
        this.numberOfFins = numberOfFins;
    }

    public AquaticAnimal(Animal animal) {
        this(animal.price, animal.name, animal.breed, null);
    }

    public static AquaticAnimalsBuilder newBuilder() {
        return new AquaticAnimal().new AquaticAnimalsBuilder();
    }

    public Integer getNumberOfFins() {
        return numberOfFins;
    }

    public void setNumberOfFins(Integer numberOfFins) {
        this.numberOfFins = numberOfFins;
    }

    class AquaticAnimalsBuilder extends AnimalBuilder {

        public AquaticAnimalsBuilder setNumberOfFins(Integer numberOfFins) {
            AquaticAnimal.this.numberOfFins = numberOfFins;
            return this;
        }

        @Override
        public AquaticAnimal build() {
            return AquaticAnimal.this;
        }
    }
}
