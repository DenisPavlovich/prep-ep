package epam.pre.romanenko.store.launcher;

import epam.pre.romanenko.entities.AquaticAnimal;
import epam.pre.romanenko.entities.TerrestrialAnimal;
import epam.pre.romanenko.store.commands.impl.AddNewAnimalReflectCommand;
import epam.pre.romanenko.store.commands.impl.AddNewAquaticAnimalCommand;
import epam.pre.romanenko.store.commands.impl.AddNewTerrestrialAnimalCommand;
import epam.pre.romanenko.store.commands.impl.PurchaseCartCommand;
import epam.pre.romanenko.store.commands.impl.show.*;
import epam.pre.romanenko.store.components.LanguageComponent;
import epam.pre.romanenko.store.components.impl.LanguageComponentImpl;
import epam.pre.romanenko.store.menu.impl.MenuItemImpl;
import epam.pre.romanenko.store.menu.impl.bundle.ProductRepositoryResourceBundle;
import epam.pre.romanenko.store.serialization.impl.ObjectByteSerializer;
import epam.pre.romanenko.store.services.Impl.MenuServiceImpl;
import epam.pre.romanenko.store.services.Impl.ProductServiceImpl;
import epam.pre.romanenko.store.services.MenuService;
import epam.pre.romanenko.store.services.ProductService;
import epam.pre.romanenko.store.strategy.DataReader;
import epam.pre.romanenko.store.strategy.impl.InputDataReader;
import epam.pre.romanenko.store.strategy.impl.RandomDataReader;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Application extends BaseApplication {

    private static final String CONTEXT_FILE = "order.repository.content";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_BACK = "back";
    private static final String COMMAND_NOT_FOUND = "Command not found...";

    private LanguageComponent language;

    public Application() {
        String filePath = ResourceBundle.getBundle("app").getString(CONTEXT_FILE);
        ProductService productService = new ProductServiceImpl(new ObjectByteSerializer(filePath));

        language = new LanguageComponentImpl();

        setUp(productService);
    }

    public static void main(String[] args) throws IOException {
        Application app = new Application();
        app.start(getDataReader());
    }

    private static DataReader getDataReader() {
        Scanner in = new Scanner(System.in);

        System.out.println("random - 0 (default)\ntyping - 1");

        switch (Integer.valueOf(in.nextLine())) {
            case 0:
                return new RandomDataReader();
            case 1:
                return new InputDataReader();
            default:
                System.out.println(COMMAND_NOT_FOUND);
                return new RandomDataReader();
        }
    }

    private MenuService getMenu(DataReader dataReader) {
        if (menu == null) {
            menu = new MenuServiceImpl(() -> MenuItemImpl.newBuilder()
                    .setTitle("MAIN MENU")
                    .addInnerMenuItem(MenuItemImpl.newBuilder()
                            .setTitle("new_reflect")
                            .addCommand(new AddNewAnimalReflectCommand("terrestrial", TerrestrialAnimal.class, productService, dataReader, language))
                            .addCommand(new AddNewAnimalReflectCommand("aquatic", AquaticAnimal.class, productService, dataReader, language))
                            .build())
                    .addInnerMenuItem(MenuItemImpl.newBuilder()
                            .setTitle("new")
                            .addCommand(new AddNewTerrestrialAnimalCommand("terrestrial", productService, dataReader))
                            .addCommand(new AddNewAquaticAnimalCommand("aquatic", productService, dataReader))
                            .build())
                    .addInnerMenuItem(MenuItemImpl.newMenuItem("add", new ProductRepositoryResourceBundle(productService, history, cart)))
                    .addInnerMenuItem(MenuItemImpl.newBuilder()
                            .setTitle("orders")
                            .addCommand(new ShowOrderClosestToCommand("nearDate", orderService, dataReader))
                            .addCommand(new ShowOrdersCommand("fromToDate", orderService, dataReader))
                            .build())
                    .addInnerMenuItem(language.getMenuItem())
                    .addCommand(new PurchaseCartCommand("buy", cart, orderService))
                    .addCommand(new ShowHistoryCommand("showHistory", history))
                    .addCommand(new ShowCartCommand("showCart", cart))
                    .addCommand(new ShowCartTotalCostCommand("cost", cart))
                    .build());
        }
        return menu;
    }

    private void start(DataReader dataReader) throws IOException {
        menu = getMenu(dataReader);

        Scanner in = new Scanner(System.in);
        String command;
        menu.show();
        System.out.printf("Can use commands: \n\t*%s\n\t*%s\n", COMMAND_BACK, COMMAND_EXIT);
        while (!COMMAND_EXIT.equals(command = in.nextLine())) {

            if (COMMAND_BACK.equals(command)) {
                menu.back();
            } else if (!menu.execute(command)) {
                System.out.println(COMMAND_NOT_FOUND);
            }

            menu.show();
        }

        productService.writeToFile();
    }
}
