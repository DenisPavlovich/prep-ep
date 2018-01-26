package epam.pre.romanenko.task8.sequence.dto;

public class Sequence implements Comparable<Sequence> {
    private int maxLength = 0;
    private int firstSequenceIndex = 0;
    private int secondSequenceIndex = 0;

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getFirstSequenceIndex() {
        return firstSequenceIndex;
    }

    public void setFirstSequenceIndex(int firstSequenceIndex) {
        this.firstSequenceIndex = firstSequenceIndex;
    }

    public int getSecondSequenceIndex() {
        return secondSequenceIndex;
    }

    public void setSecondSequenceIndex(int secondSequenceIndex) {
        this.secondSequenceIndex = secondSequenceIndex;
    }

    @Override
    public int compareTo(Sequence sequence) {
        return Integer.compare(this.maxLength, sequence.maxLength);
    }

    @Override
    public String toString() {
        return String.format("first index:%d second index:%d length:%d", firstSequenceIndex, secondSequenceIndex,
                maxLength);
    }
}