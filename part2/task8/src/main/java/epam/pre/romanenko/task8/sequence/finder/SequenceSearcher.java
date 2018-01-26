package epam.pre.romanenko.task8.sequence.finder;

import epam.pre.romanenko.task8.sequence.dto.Sequence;

public class SequenceSearcher implements Runnable {

    private Sequence sequence;
    private byte[] bytes;
    private int step;
    private int from;

    public SequenceSearcher(byte[] bytes, int from, int step) {
        this.bytes = bytes;
        this.step = step;
        this.from = from;
    }

    @Override
    public void run() {
        sequence = new Sequence();
        for (int start = from; start < bytes.length - sequence.getMaxLength() - 1; start += step) {
            for (int secondIndex = start + 1; secondIndex < bytes.length - sequence.getMaxLength();
                 secondIndex++) {
                int length = 0;
                for (int i = 0; i + secondIndex < bytes.length; i++) {
                    if (bytes[start + i] == bytes[secondIndex + i]) {
                        length++;
                    } else {
                        break;
                    }
                }
                if (length > sequence.getMaxLength()) {
                    sequence.setFirstSequenceIndex(start);
                    sequence.setSecondSequenceIndex(secondIndex);
                    sequence.setMaxLength(length);
                }
            }
        }
        bytes = null;
    }

    public boolean isFree() {
        return bytes == null;
    }

    public Sequence getSequence() {
        return sequence;
    }

}
