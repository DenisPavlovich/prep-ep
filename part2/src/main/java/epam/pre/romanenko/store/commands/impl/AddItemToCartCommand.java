package epam.pre.romanenko.store.commands.impl;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.commands.AbstractCommand;
import epam.pre.romanenko.store.services.CartService;
import epam.pre.romanenko.store.services.HistoryService;

public class AddItemToCartCommand extends AbstractCommand {

    private CartService cart;
    private HistoryService historyService;
    private Being addedBeing;

    public AddItemToCartCommand(String id, CartService cart, HistoryService historyService, Being addedBeing) {
        super(id);
        this.cart = cart;
        this.addedBeing = addedBeing;
        this.historyService = historyService;
    }

    @Override
    public void execute() {
        cart.add(addedBeing);
        historyService.add(addedBeing.getId(), addedBeing);
    }

}
