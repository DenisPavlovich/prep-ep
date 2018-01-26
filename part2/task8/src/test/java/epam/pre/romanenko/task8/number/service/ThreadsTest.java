package epam.pre.romanenko.task8.number.service;

import epam.pre.romanenko.task8.number.service.impl.MultiContainerImpl;
import epam.pre.romanenko.task8.number.service.impl.MultiExecutorsContainerImpl;
import epam.pre.romanenko.task8.number.service.impl.SingleContainerImpl;
import epam.pre.romanenko.task8.number.service.impl.SingleExecutorsContainerImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ThreadsTest {

    private ThreadManager threadManager;

    private Integer countOfThreads = 1;
    private Integer from = 1;
    private Integer to = 15;

    private Integer[] expected = new Integer[]{2, 3, 5, 7, 11, 13};

    private Integer[] getArray() {
        List<Integer> result = Arrays.asList(threadManager.getResult().toArray(new Integer[expected.length]));
        return result.toArray(new Integer[result.size()]);
    }

    @Test
    public void singleExecutorContainerThread() throws InterruptedException {
        threadManager = new SingleExecutorsContainerImpl(from, to, countOfThreads);
        threadManager.start();
        Assert.assertArrayEquals(expected, getArray());
    }

    @Test
    public void multiExecutorContainerThread() throws InterruptedException {
        threadManager = new MultiExecutorsContainerImpl(from, to, countOfThreads);
        threadManager.start();
        Assert.assertArrayEquals(expected, getArray());
    }

    @Test
    public void singleContainerThread() throws InterruptedException {
        threadManager = new SingleContainerImpl(from, to, countOfThreads);
        threadManager.start();
        Assert.assertArrayEquals(expected, getArray());
    }

    @Test
    public void multiContainerThread() throws InterruptedException {
        threadManager = new MultiContainerImpl(from, to, countOfThreads);
        threadManager.start();
        Assert.assertArrayEquals(expected, getArray());
    }

}