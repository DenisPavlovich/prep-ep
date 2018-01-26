package epam.pre.romanenko.store.repository.impl;

import epam.pre.romanenko.store.repository.OrderContainer;

import java.util.*;

public class OrderContainerImpl implements OrderContainer {

    private TreeMap<Date, Set> orders;

    public OrderContainerImpl() {
        orders = new TreeMap<>();
    }

    @Override
    public void put(Date key, Set cartContent) {
        orders.put(key, cartContent);
    }

    @Override
    public Collection<Set> getOrders(Date from, Date to) {
        SortedMap<Date, Set> result = orders.subMap(from, to);
        return result.values();
    }

    @Override
    public Set getOrderClosestTo(Date key) {
        if (orders.size() == 0 || key == null) {
            return null;
        }
        if (orders.containsKey(key)) {
            return orders.get(key);
        }
        Date higherKey = orders.higherKey(key);
        Date lowerKey = orders.lowerKey(key);

        long higherTimeDelta = higherKey != null ? Math.abs(higherKey.getTime() - key.getTime()) : Long.MAX_VALUE;
        long lowerTimeDelta = lowerKey != null ? Math.abs(lowerKey.getTime() - key.getTime()) : Long.MAX_VALUE;

        key = higherTimeDelta > lowerTimeDelta ? lowerKey : higherKey;
        return orders.get(key);
    }

}
