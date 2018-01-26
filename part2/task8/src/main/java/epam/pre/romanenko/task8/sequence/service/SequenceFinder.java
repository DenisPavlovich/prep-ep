package epam.pre.romanenko.task8.sequence.service;

import epam.pre.romanenko.task8.sequence.finder.SequenceMultiSearcher;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SequenceFinder {

    private static final Logger LOGGER = Logger.getLogger(SequenceFinder.class);
    private SequenceMultiSearcher finder;
    private String path;

    public SequenceFinder(int countOfThreads) {
        finder = new SequenceMultiSearcher(countOfThreads);
        Thread thread = new Thread(finder);
        thread.start();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void startFinding() {
        try {
            byte[] data = Files.readAllBytes(Paths.get(path));
            finder.setData(data);
            finder.start();
        } catch (IOException e) {
            LOGGER.warn("File not found for reading!", e);
        }
    }

    public int getLength() {
        return finder.getSequence().getMaxLength();
    }

    public boolean isReady() {
        return finder.isReady();
    }

    public String getResult() {
        return finder.getSequence().toString();
    }

}
