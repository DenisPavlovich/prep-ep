package epam.pre.romanenko.store.commands.impl;

import epam.pre.romanenko.entities.Animal;
import epam.pre.romanenko.entities.TerrestrialAnimal;
import epam.pre.romanenko.store.commands.AbstractNewAnimalCommand;
import epam.pre.romanenko.store.services.ProductService;
import epam.pre.romanenko.store.strategy.DataReader;

public class AddNewTerrestrialAnimalCommand extends AbstractNewAnimalCommand {

    public AddNewTerrestrialAnimalCommand(String id, ProductService productService, DataReader dataReader) {
        super(id, productService, dataReader);
    }

    @Override
    protected Animal createAnimal() {
        return readTerrestrialAnimal();
    }

    protected TerrestrialAnimal readTerrestrialAnimal() {
        TerrestrialAnimal animal = new TerrestrialAnimal(readAnimal());

        animal.setNumberOfPaws(4);

        return animal;
    }

}
