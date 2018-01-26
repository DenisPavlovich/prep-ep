package epam.pre.romanenko.store.launcher;

import epam.pre.romanenko.store.repository.impl.CartImpl;
import epam.pre.romanenko.store.services.*;
import epam.pre.romanenko.store.services.Impl.CartServiceImpl;
import epam.pre.romanenko.store.services.Impl.HistoryServiceImpl;
import epam.pre.romanenko.store.services.Impl.OrderContainerServiceImpl;

public class BaseApplication {

    protected CartService cart;
    protected HistoryService history;
    protected MenuService menu;
    protected OrderContainerService orderService;
    protected ProductService productService;

    protected void setUp(ProductService productService) {
        cart = new CartServiceImpl(new CartImpl());
        history = new HistoryServiceImpl();
        orderService = new OrderContainerServiceImpl();
        this.productService = productService;
    }

}
