package epam.pre.romanenko.store.repository;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Contains elements in treeMap
 */
public interface OrderContainer {

    void put(Date key, Set set);

    /**
     * Returns an array elements which keys between from and to
     *
     * @param from - from date
     * @param to   - to date
     * @return - an array elements which keys between from and to
     */
    Collection<Set> getOrders(Date from, Date to);

    /**
     * @param date - input date
     * @return an element which has key the closest to input key
     */
    Set getOrderClosestTo(Date date);

}
