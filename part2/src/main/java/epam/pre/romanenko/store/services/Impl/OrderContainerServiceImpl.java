package epam.pre.romanenko.store.services.Impl;

import epam.pre.romanenko.store.repository.OrderContainer;
import epam.pre.romanenko.store.repository.impl.OrderContainerImpl;
import epam.pre.romanenko.store.services.OrderContainerService;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class OrderContainerServiceImpl implements OrderContainerService {

    private OrderContainer orders;

    public OrderContainerServiceImpl() {
        this.orders = new OrderContainerImpl();
    }

    @Override
    public void put(Date key, Set cartContent) {
        orders.put(key, cartContent);
    }

    @Override
    public Collection<Set> getOrders(Date from, Date to) {
        return orders.getOrders(from, to);
    }

    @Override
    public Set getOrderClosestTo(Date key) {
        return orders.getOrderClosestTo(key);
    }

}