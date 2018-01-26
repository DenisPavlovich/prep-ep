package epam.pre.romanenko.task8.number.finder.impl;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeNumberFinder implements Runnable {

    private Collection<Integer> result;
    private AtomicInteger nextIndex;
    private int to;

    public PrimeNumberFinder(Collection<Integer> result, AtomicInteger nextIndex, int to) {
        this.result = result;
        this.to = to;
        this.nextIndex = nextIndex;
    }

    private static boolean isPrime(Integer number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        int number = nextIndex.get();
        while (number <= to) {
            if (isPrime(number)) {
                result.add(number);
            }
            number = nextIndex.addAndGet(1);
        }
    }

}
