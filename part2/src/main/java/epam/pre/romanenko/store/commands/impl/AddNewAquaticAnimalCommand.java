package epam.pre.romanenko.store.commands.impl;

import epam.pre.romanenko.entities.Animal;
import epam.pre.romanenko.entities.AquaticAnimal;
import epam.pre.romanenko.store.commands.AbstractNewAnimalCommand;
import epam.pre.romanenko.store.services.ProductService;
import epam.pre.romanenko.store.strategy.DataReader;

public class AddNewAquaticAnimalCommand extends AbstractNewAnimalCommand {

    public AddNewAquaticAnimalCommand(String id, ProductService productService, DataReader dataReader) {
        super(id, productService, dataReader);
    }

    @Override
    protected Animal createAnimal() {
        return readAquaticAnimal();
    }

    protected AquaticAnimal readAquaticAnimal() {
        AquaticAnimal animal = new AquaticAnimal(readAnimal());

        animal.setNumberOfFins(dataReader.readInt(String.format(MSG_INPUT, "number of fins"), 0, Integer.MAX_VALUE));

        return animal;
    }

}
