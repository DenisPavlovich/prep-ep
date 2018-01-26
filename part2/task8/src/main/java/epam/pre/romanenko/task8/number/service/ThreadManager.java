package epam.pre.romanenko.task8.number.service;

import java.util.Collection;

public interface ThreadManager {

    void start() throws InterruptedException;

    int countOfNumbers();

    Collection<Integer> getResult();
}
