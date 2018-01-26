package epam.pre.romanenko.store.commands.impl;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.annotations.FieldName;
import epam.pre.romanenko.store.commands.AbstractCommand;
import epam.pre.romanenko.store.components.LanguageComponent;
import epam.pre.romanenko.store.services.ProductService;
import epam.pre.romanenko.store.strategy.DataReader;
import epam.pre.romanenko.util.LocalizationTextBuilder;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AddNewAnimalReflectCommand extends AbstractCommand {

    private static final Logger LOGGER = Logger.getLogger(AddNewAnimalReflectCommand.class);

    private static final String MSG_INPUT = "input";
    private static final String MSG_FIELD = "field";

    private ProductService productService;
    private DataReader dataReader;
    private Class<? extends Being> classToCreate;
    private LanguageComponent languageComponent;

    public AddNewAnimalReflectCommand(String id, Class<? extends Being> classToCreate, ProductService productService,
                                      DataReader dataReader, LanguageComponent languageComponent) {
        super(id);
        this.productService = productService;
        this.dataReader = dataReader;
        this.classToCreate = classToCreate;
        this.languageComponent = languageComponent;
    }

    @Override
    public void execute() {
        try {
            Being animal = createAnimal(classToCreate);
            productService.add(animal.getName(), animal);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException
                | NoSuchMethodException e) {
            LOGGER.error("Animal creation was failed...", e);
        }
    }

    private Being createAnimal(Class<? extends Being> clazz) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        Being animal = clazz.newInstance();
        setUpObject(animal, animal.getClass());

        return animal;
    }

    private void setUpObject(Object obj, Class clazz) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        Class superClazz = clazz.getSuperclass();
        if (superClazz != Object.class) {
            setUpObject(obj, superClazz);
        }
        for (Field field : clazz.getDeclaredFields()) {
            FieldName fieldName = field.getAnnotation(FieldName.class);
            if (fieldName != null) {
                field.setAccessible(true);
                field.set(obj, newInstanceByStringConstructor(getInputMessage(fieldName.value()), field));
            }
        }
    }

    private Object newInstanceByStringConstructor(String msg, Field field) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        return field.getType()
                .getConstructor(String.class)
                .newInstance(dataReader.readString(msg));
    }

    private String getInputMessage(String fieldName) {
        return LocalizationTextBuilder.getTextBuilder(languageComponent.getLocale())
                .addTextFromBundle(MSG_INPUT)
                .addTextFromBundle(MSG_FIELD)
                .addTextFromBundle(fieldName, ":")
                .buildText();
    }
}