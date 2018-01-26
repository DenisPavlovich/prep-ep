package epam.pre.romanenko.store.repository;

import java.io.Serializable;
import java.util.Set;

/**
 * @param <K> - Element's key
 * @param <E> - Element to contain
 */
public interface ProductRepository<K, E extends Serializable> {

    void add(K key, E element);

    void remove(K key);

    Set<E> getElements();

}