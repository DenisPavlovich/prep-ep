package epam.pre.romanenko.store.services;

import epam.pre.romanenko.entities.Being;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public interface CartService {

    void add(Being element);

    void clear();

    void remove(Being element);

    BigDecimal getCost();

    Set<Map.Entry<Being, Integer>> getItems();

}
