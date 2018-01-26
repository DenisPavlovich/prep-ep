package epam.pre.romanenko.entities;

import epam.pre.romanenko.store.annotations.FieldName;

import java.math.BigDecimal;

public class TerrestrialAnimal extends Animal {

    @FieldName("number.of.paws")
    private Integer numberOfPaws;

    public TerrestrialAnimal() {
    }

    public TerrestrialAnimal(BigDecimal price, String name, String breed, Integer numberOfPaws) {
        super(price, name, breed);
        this.numberOfPaws = numberOfPaws;
    }

    public TerrestrialAnimal(Animal animal) {
        this(animal.price, animal.name, animal.breed, null);
    }

    public static TerrestrialAnimalBuilder newBuilder() {
        return new TerrestrialAnimal().new TerrestrialAnimalBuilder();
    }

    public Integer getNumberOfPaws() {
        return numberOfPaws;
    }

    public void setNumberOfPaws(Integer numberOfPaws) {
        this.numberOfPaws = numberOfPaws;
    }

    @Override
    public String toString() {
        return String.format("%s, number of paws:%s", super.toString(), numberOfPaws);
    }

    public class TerrestrialAnimalBuilder extends AnimalBuilder {

        public TerrestrialAnimalBuilder setNumberOfPaws(Integer numberOfPaws) {
            TerrestrialAnimal.this.numberOfPaws = numberOfPaws;
            return this;
        }

        @Override
        public TerrestrialAnimal build() {
            return TerrestrialAnimal.this;
        }
    }

}
