package epam.pre.romanenko.task8.number.service.impl;

import epam.pre.romanenko.task8.number.finder.impl.PrimeNumberFinder;
import epam.pre.romanenko.task8.number.service.AbstractThreadManager;

import java.util.*;

public class MultiContainerImpl extends AbstractThreadManager {

    private List<Thread> threads;
    private Collection<Collection<Integer>> results;

    public MultiContainerImpl(Integer from, Integer to, Integer countOfThreads) {
        super(new LinkedList<>(), from, to, countOfThreads);
        threads = new ArrayList<>(countOfThreads);
        results = new ArrayList<>();
    }

    @Override
    protected void createAndStart() {
        List<Integer> collection = new ArrayList<>();
        Thread thread = new Thread(new PrimeNumberFinder(collection, nextIndex, to));
        thread.start();
        threads.add(thread);
        results.add(collection);
    }

    @Override
    protected void waitForEnd() throws InterruptedException {
        Iterator<Collection<Integer>> iterator = results.iterator();
        for (Thread thread : threads) {
            thread.join();
            result.addAll(iterator.next());
        }
    }

}