package epam.pre.romanenko.task8.sequence.finder;

import epam.pre.romanenko.task8.sequence.dto.Sequence;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SequenceMultiSearcher implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(SequenceMultiSearcher.class);

    private Object monitor = new Object();

    private int countOfThreads;
    private byte[] bytes;

    private List<SequenceSearcher> sequenceSearchers;
    private ExecutorService executor;

    private Sequence sequence;

    public SequenceMultiSearcher() {
        countOfThreads = 1;
        sequenceSearchers = new ArrayList<>();
        sequence = new Sequence();
        executor = Executors.newFixedThreadPool(countOfThreads);
    }

    public SequenceMultiSearcher(int countOfThreads) {
        this.countOfThreads = countOfThreads;
        sequenceSearchers = new ArrayList<>();
        sequence = new Sequence();
        executor = Executors.newFixedThreadPool(countOfThreads);
    }

    private void createAndStartSequenceSearcher(int start) {
        SequenceSearcher sequenceSearcher = new SequenceSearcher(bytes, start, countOfThreads);
        sequenceSearchers.add(sequenceSearcher);
        executor.execute(sequenceSearcher);
    }

    public boolean isReady() {
        return bytes == null;
    }

    public boolean setData(byte[] data) {
        if (isReady()) {
            this.bytes = data;
            return true;
        }
        return false;
    }

    public void find() throws InterruptedException {
        clearResult();
        for (int i = 0; i < countOfThreads; i++) {
            createAndStartSequenceSearcher(i);
        }
        waitAndUpdateResult();
    }

    private void clearResult() {
        sequenceSearchers = new ArrayList<>();
        sequence = new Sequence();
    }

    public void waitAndUpdateResult() throws InterruptedException {
        while (!isFree(100)) {
            updateResult();
        }
        updateResult();
        bytes = null;
    }

    private void updateResult() {
        sequence = sequenceSearchers.stream()
                .map(SequenceSearcher::getSequence)
                .max(Sequence::compareTo)
                .orElse(new Sequence());
    }

    private boolean isFree() {
        for (SequenceSearcher searcher : sequenceSearchers) {
            if (!searcher.isFree()) {
                return false;
            }
        }
        return true;
    }

    private boolean isFree(long millisWait) throws InterruptedException {
        Thread.sleep(millisWait);
        return isFree();
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void run() {
        synchronized (monitor) {
            while (true) {
                try {
                    if (bytes != null) {
                        find();
                    } else {
                        monitor.wait();
                    }
                } catch (InterruptedException e) {
                    LOGGER.warn("Sequence searcher is interrupted...", e);
                }
            }
        }
    }

    public void start() {
        synchronized (monitor) {
            monitor.notify();
        }
    }

}
