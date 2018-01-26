package epam.pre.romanenko.store.repository.impl;

import epam.pre.romanenko.store.repository.History;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class HistoryImpl<K, E> implements History<K, E> {

    private static final int DEFAULT_CAPACITY = 5;
    private static final float LOAD_FACTOR = 1f;

    private final int size;
    private Map<K, E> history;

    public HistoryImpl() {
        size = DEFAULT_CAPACITY;
        setUp();
    }

    public HistoryImpl(int size) {
        this.size = size;
        setUp();
    }

    private void setUp() {
        history = new LinkedHashMap<K, E>(size + 1, LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, E> eldest) {
                return size() > HistoryImpl.this.size;
            }
        };
    }

    @Override
    public Collection<E> getHistory() {
        return history.values();
    }

    @Override
    public void add(K key, E element) {
        history.put(key, element);
    }


}
