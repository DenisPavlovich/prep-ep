package epam.pre.romanenko.store.commands.impl;

import epam.pre.romanenko.store.commands.AbstractCommand;
import epam.pre.romanenko.store.services.CartService;
import epam.pre.romanenko.store.services.OrderContainerService;

import java.util.Date;

public class PurchaseCartCommand extends AbstractCommand {

    private CartService cart;
    private OrderContainerService orderContainer;

    public PurchaseCartCommand(String id, CartService cart, OrderContainerService orderContainer) {
        super(id);
        this.cart = cart;
        this.orderContainer = orderContainer;
    }

    @Override
    public void execute() {
        orderContainer.put(new Date(), cart.getItems());
        System.out.println("$" + cart.getCost());
        cart.clear();
    }

}
