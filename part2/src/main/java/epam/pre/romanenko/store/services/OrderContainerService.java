package epam.pre.romanenko.store.services;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public interface OrderContainerService {

    void put(Date key, Set set);

    Collection<Set> getOrders(Date from, Date to);

    Set getOrderClosestTo(Date date);

}
