package epam.pre.romanenko.store.repository;

import epam.pre.romanenko.entities.Being;

import java.util.Map;
import java.util.Set;

public interface Cart {

    void add(Being element);

    void clear();

    void remove(Being element);

    Set<Map.Entry<Being, Integer>> getItems();
}