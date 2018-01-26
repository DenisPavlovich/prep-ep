package epam.pre.romanenko.store.menu.impl.bundle;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.commands.Command;
import epam.pre.romanenko.store.commands.impl.AddItemToCartCommand;
import epam.pre.romanenko.store.menu.MenuResourceBundle;
import epam.pre.romanenko.store.services.CartService;
import epam.pre.romanenko.store.services.HistoryService;
import epam.pre.romanenko.store.services.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryResourceBundle implements MenuResourceBundle {

    private ProductService productService;
    private HistoryService historyService;
    private CartService cartService;

    public ProductRepositoryResourceBundle(ProductService productService, HistoryService historyService,
                                           CartService cartService) {
        this.productService = productService;
        this.historyService = historyService;
        this.cartService = cartService;
    }

    @Override
    public List<Command> getUpdatedCommands() {
        List<Command> result = new ArrayList<>();
        for (Being entry : productService.getElements())
            result.add(new AddItemToCartCommand(entry.getName(), cartService, historyService, entry));

        return result;
    }
}
