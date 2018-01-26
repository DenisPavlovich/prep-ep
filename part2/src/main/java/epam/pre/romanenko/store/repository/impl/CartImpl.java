package epam.pre.romanenko.store.repository.impl;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.repository.Cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CartImpl implements Cart {

    private Map<Being, Integer> items;

    public CartImpl() {
        items = new HashMap<>();
    }

    @Override
    public void add(Being key) {
        items.merge(key, 1, (a, b) -> a + b);
    }

    @Override
    public Set<Map.Entry<Being, Integer>> getItems() {
        return items.entrySet();
    }

    @Override
    public void clear() {
        items = new HashMap<>();
    }

    @Override
    public void remove(Being key) {
        items.remove(key);
    }

    @Override
    public String toString() {
        if (items.entrySet().size() == 0) return "NULL";

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Being, Integer> entry : items.entrySet()) {
            result.append(String.format("%s\n", entry.toString()));
        }
        return result.toString();
    }

}
