package epam.pre.remonenko.factory;

import epam.pre.remonenko.entity.Entity;

public interface EntityFactory {

    Entity createEntity(Type type);

    enum Type {
        MAP,
        ENTITY_UNMODIFIABLE
    }

}
