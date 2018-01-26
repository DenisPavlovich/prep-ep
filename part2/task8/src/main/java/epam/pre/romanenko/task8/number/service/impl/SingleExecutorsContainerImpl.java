package epam.pre.romanenko.task8.number.service.impl;

import epam.pre.romanenko.task8.number.finder.impl.PrimeNumberFinder;
import epam.pre.romanenko.task8.number.service.AbstractThreadManager;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleExecutorsContainerImpl extends AbstractThreadManager {

    private ExecutorService executor;

    public SingleExecutorsContainerImpl(int from, int to, int countOfThreads) {
        super(new ConcurrentLinkedQueue<>(), from, to, countOfThreads);
        executor = Executors.newFixedThreadPool(countOfThreads);
    }

    @Override
    protected void createAndStart() {
        executor.execute(new PrimeNumberFinder(result, nextIndex, to));
    }

    @Override
    protected void waitForEnd() throws InterruptedException {
        executor.shutdownNow();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }
}