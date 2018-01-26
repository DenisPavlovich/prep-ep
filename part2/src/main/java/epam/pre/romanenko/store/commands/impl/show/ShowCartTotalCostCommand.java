package epam.pre.romanenko.store.commands.impl.show;

import epam.pre.romanenko.store.commands.AbstractCommand;
import epam.pre.romanenko.store.services.CartService;

public class ShowCartTotalCostCommand extends AbstractCommand {

    private CartService cart;

    public ShowCartTotalCostCommand(String id, CartService cart) {
        super(id);
        this.cart = cart;
    }

    @Override
    public void execute() {
        System.out.println("$" + cart.getCost());
    }

}
