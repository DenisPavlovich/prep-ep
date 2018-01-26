package epam.pre.romanenko.store.commands;

import epam.pre.romanenko.entities.Animal;
import epam.pre.romanenko.store.services.ProductService;
import epam.pre.romanenko.store.strategy.DataReader;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public abstract class AbstractNewAnimalCommand extends AbstractCommand {

    protected static final String MSG_INPUT = "Input %s:";
    private static final Logger LOGGER = Logger.getLogger(AbstractNewAnimalCommand.class);

    protected ProductService productService;
    protected DataReader dataReader;

    public AbstractNewAnimalCommand(String id, ProductService productService, DataReader dataReader) {
        super(id);
        this.productService = productService;
        this.dataReader = dataReader;
    }

    @Override
    public void execute() {
        try {
            Animal animal = createAnimal();
            productService.add(animal.getName(), animal);
        } catch (NumberFormatException e) {
            LOGGER.error("Bad format...", e);
        }
    }

    abstract protected Animal createAnimal();

    protected Animal readAnimal() {
        Animal animal = new Animal();

        animal.setPrice(dataReader.readBigDecimal(String.format(MSG_INPUT, "price"), new BigDecimal(10),
                new BigDecimal(Integer.MAX_VALUE)));
        animal.setName(dataReader.readString(String.format(MSG_INPUT, "name")));
        animal.setBreed(dataReader.readString(String.format(MSG_INPUT, "breed")));

        return animal;
    }

}
