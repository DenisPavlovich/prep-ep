package epam.pre.romanenko.store.repository.impl;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.repository.ProductRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProductRepositoryImpl<E extends Being> implements ProductRepository<String, E> {

    private Map<String, E> products;

    public ProductRepositoryImpl() {
        products = new HashMap<>();
    }

    public ProductRepositoryImpl(Set<E> beings) {
        products = new HashMap<>();
        for (E being : beings) {
            products.put(being.getName(), being);
        }
    }

    @Override
    public void add(String key, E element) {
        products.put(key, element);
    }

    @Override
    public void remove(String key) {
        products.remove(key);
    }

    @Override
    public Set<E> getElements() {
        return new HashSet<>(products.values());
    }

}