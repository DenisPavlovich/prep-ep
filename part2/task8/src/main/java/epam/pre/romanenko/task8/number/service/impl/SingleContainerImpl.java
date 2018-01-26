package epam.pre.romanenko.task8.number.service.impl;

import epam.pre.romanenko.task8.number.finder.impl.PrimeNumberFinder;
import epam.pre.romanenko.task8.number.service.AbstractThreadManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SingleContainerImpl extends AbstractThreadManager {

    private List<Thread> threads;

    public SingleContainerImpl(int from, int to, int countOfThreads) {
        super(new ConcurrentLinkedQueue<>(), from, to, countOfThreads);
        threads = new ArrayList<>(countOfThreads);
    }

    @Override
    protected void createAndStart() {
        Thread thread = new Thread(new PrimeNumberFinder(result, nextIndex, to));
        thread.start();
        threads.add(thread);
    }

    @Override
    protected void waitForEnd() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }

}