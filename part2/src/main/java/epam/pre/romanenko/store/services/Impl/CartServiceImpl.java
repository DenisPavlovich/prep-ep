package epam.pre.romanenko.store.services.Impl;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.repository.Cart;
import epam.pre.romanenko.store.services.CartService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CartServiceImpl implements CartService {

    private Cart cart;

    public CartServiceImpl(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void add(Being element) {
        cart.add(element);
    }

    @Override
    public Set<Map.Entry<Being, Integer>> getItems() {
        return cart.getItems();
    }

    @Override
    public void clear() {
        cart.clear();
    }

    @Override
    public void remove(Being element) {
        cart.remove(element);
    }

    @Override
    public String toString() {
        return cart.toString();
    }

    @Override
    public BigDecimal getCost() {
        return cart.getItems().stream()
                .map(o -> o.getKey().getPrice().multiply(new BigDecimal(o.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
