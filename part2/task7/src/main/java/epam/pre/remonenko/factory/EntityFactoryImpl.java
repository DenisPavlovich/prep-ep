package epam.pre.remonenko.factory;

import epam.pre.remonenko.entity.Entity;
import epam.pre.remonenko.entity.Item;
import epam.pre.remonenko.proxy.EntityBasedOnMapImpl;
import epam.pre.remonenko.proxy.UnmodifiableEntityProxy;

import java.lang.reflect.Proxy;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity createEntity(Type type) {
        switch (type) {
            case MAP:
                return (Entity) Proxy.newProxyInstance(Item.class.getClassLoader(), Item.class.getInterfaces(),
                        new EntityBasedOnMapImpl());
            default:
                return (Entity) Proxy.newProxyInstance(Item.class.getClassLoader(), Item.class.getInterfaces(),
                        new UnmodifiableEntityProxy(new Item()));
        }
    }

}
