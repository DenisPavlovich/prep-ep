package epam.pre.romanenko.store.repository;

import java.util.Collection;

/**
 * Contains elements in the order of their call
 *
 * @param <K> - Key
 * @param <E> - Element
 */
public interface History<K, E> {

    /**
     * Returns an array of element in the order of their call
     *
     * @return - an array of element in the order of their call
     */
    Collection<E> getHistory();

    void add(K key, E element);

}
