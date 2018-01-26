package epam.pre.remonenko.proxy;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EntityBasedOnMapImpl implements InvocationHandler {

    private static final Logger LOGGER = Logger.getLogger(EntityBasedOnMapImpl.class);

    private Map<String, Object> map;

    public EntityBasedOnMapImpl() {
        map = new HashMap<>();
        map.put("Age", 0);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.debug(method.getName());
        if (method.getName().startsWith("set")) {
            map.put(method.getName().replace("set", ""), args[0]);
            return null;
        }
        if (method.getName().startsWith("get")) {
            return map.get(method.getName().replace("get", ""));
        }
        throw new UnsupportedOperationException();
    }

}
