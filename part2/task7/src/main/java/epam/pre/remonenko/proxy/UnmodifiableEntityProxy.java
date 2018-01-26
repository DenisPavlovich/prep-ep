package epam.pre.remonenko.proxy;

import epam.pre.remonenko.entity.Entity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UnmodifiableEntityProxy implements InvocationHandler {

    private Entity entity;

    public UnmodifiableEntityProxy(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            throw new UnsupportedOperationException();
        }
        return method.invoke(entity, args);
    }
}
