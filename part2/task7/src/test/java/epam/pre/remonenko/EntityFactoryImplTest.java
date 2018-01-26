package epam.pre.remonenko;

import epam.pre.remonenko.entity.Entity;
import epam.pre.remonenko.factory.EntityFactory;
import epam.pre.remonenko.factory.EntityFactoryImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static junit.framework.TestCase.assertEquals;

public class EntityFactoryImplTest {

    @Test
    public void setUnmodifiableItem() throws Exception {
        int expected = 2;
        int actually = 0;

        EntityFactory entityFactory = new EntityFactoryImpl();
        Entity unmodifiableEntity = entityFactory.createEntity(EntityFactory.Type.ENTITY_UNMODIFIABLE);

        try {
            unmodifiableEntity.setAge(2);
        } catch (UnsupportedOperationException ex) {
            actually++;
        }
        try {
            unmodifiableEntity.setName("item name2");
        } catch (UnsupportedOperationException ex) {
            actually++;
        }

        assertEquals(expected, actually);
    }

    @Test
    public void newProxyMapItemTestSet() throws Exception {
        Map<String, Object> expected = new HashMap<>();
        expected.put("Name", "name item");
        expected.put("Age", 10);

        Entity entity = new EntityFactoryImpl().createEntity(EntityFactory.Type.MAP);

        entity.setName("name item");
        entity.setAge(10);

        assertEquals(expected.toString(), entity.toString());
    }

    @Test
    public void newProxyMapItemTestGet() throws Exception {
        int expected = 2;

        Entity entity = new EntityFactoryImpl().createEntity(EntityFactory.Type.MAP);
        entity.setName("name item");
        entity.setAge(10);

        int actually = 0;
        if (entity.getAge() == 10) {
            actually++;
        }
        if (Objects.equals(entity.getName(), "name item")) {
            actually++;
        }

        assertEquals(expected, actually);
    }

    @Test
    public void newProxyMapItemTestGetDefault() throws Exception {
        Entity entity = new EntityFactoryImpl().createEntity(EntityFactory.Type.MAP);
        entity.setName("name item");
        assertEquals(0, entity.getAge());
    }

}