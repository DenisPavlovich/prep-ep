package epam.pre.romanenko.task8.number.service;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractThreadManager implements ThreadManager {

    protected Collection<Integer> result;
    protected AtomicInteger nextIndex;
    protected int to;
    private int countOfThreads;

    public AbstractThreadManager(Collection<Integer> collection, int from, int to, int countOfThreads) {
        this.to = to;
        this.countOfThreads = countOfThreads;
        result = collection;

        if (from <= 2) {
            result.add(2);
            from = 3;
        }
        nextIndex = new AtomicInteger(from % 2 == 0 ? from + 1 : from);
    }

    @Override
    public void start() throws InterruptedException {
        for (int i = 0; i < countOfThreads; i++) {
            createAndStart();
        }
        waitForEnd();
    }

    abstract protected void createAndStart();

    abstract protected void waitForEnd() throws InterruptedException;

    @Override
    public int countOfNumbers() {
        return result.size();
    }

    @Override
    public Collection<Integer> getResult() {
        return Collections.unmodifiableCollection(result);
    }

}