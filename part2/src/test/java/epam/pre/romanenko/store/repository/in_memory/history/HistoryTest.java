package epam.pre.romanenko.store.repository.in_memory.history;

import epam.pre.romanenko.store.repository.History;
import epam.pre.romanenko.store.repository.impl.HistoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HistoryTest {

    private History<Integer, Integer> history = new HistoryImpl<>();
    private Integer[] expArr = new Integer[]{1, 2, 3};

    @Before
    public void setUp() {
        for (Integer value : expArr)
            history.add(value, value);
    }

    @Test
    public void getHistory() throws Exception {
        assertArrayEquals(expArr, history.getHistory().toArray());
    }

    @Test
    public void addNewElement() throws Exception {
        expArr = new Integer[]{1, 2, 3, 4};
        history.add(4, 4);
        assertArrayEquals(expArr, history.getHistory().toArray());
    }

    @Test
    public void addTheSameElement() throws Exception {
        expArr = new Integer[]{2, 3, 1};
        history.add(1, 1);
        assertArrayEquals(expArr, history.getHistory().toArray());
    }

}