package epam.pre.romanenko.store.commands.impl.show;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.commands.AbstractCommand;
import epam.pre.romanenko.store.services.CartService;

import java.util.Map;

public class ShowCartCommand extends AbstractCommand {

    private CartService cart;

    public ShowCartCommand(String id, CartService cart) {
        super(id);
        this.cart = cart;
    }

    @Override
    public void execute() {
        System.out.println("- - - cart - - -");
        for (Map.Entry<Being, Integer> entry : cart.getItems()) {
            System.out.println(entry);
        }
        System.out.println("- - - - - - - - -");
    }
}
