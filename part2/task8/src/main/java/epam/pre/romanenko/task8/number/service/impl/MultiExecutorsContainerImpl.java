package epam.pre.romanenko.task8.number.service.impl;

import epam.pre.romanenko.task8.number.finder.impl.PrimeNumberFinder;
import epam.pre.romanenko.task8.number.service.AbstractThreadManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiExecutorsContainerImpl extends AbstractThreadManager {

    private ExecutorService executor;
    private Collection<Collection<Integer>> results;

    public MultiExecutorsContainerImpl(int from, int to, int countOfThreads) {
        super(new LinkedList<>(), from, to, countOfThreads);
        executor = Executors.newFixedThreadPool(countOfThreads);
        results = new ArrayList<>();
    }

    @Override
    protected void createAndStart() {
        List<Integer> result = new LinkedList<>();
        executor.execute(new PrimeNumberFinder(result, nextIndex, to));
        results.add(result);
    }

    @Override
    protected void waitForEnd() throws InterruptedException {
        executor.shutdownNow();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        for (Collection<Integer> collection : results) {
            result.addAll(collection);
        }
    }

}