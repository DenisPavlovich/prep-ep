package epam.pre.romanenko.store.strategy.impl;

import epam.pre.romanenko.store.strategy.DataReader;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class RandomDataReader implements DataReader {

    private static final int MAX_INT = 1_000_000;

    private Random random = new Random();

    @Override
    public Integer readInt(String msg, Integer from, Integer to) {
        int max = to != null ? to : Integer.MAX_VALUE;

        if (from != null) {
            return (random.nextInt(max - from) + from);
        }
        Integer value = random.nextInt(max);
        print(msg, String.valueOf(value));
        return value;
    }

    @Override
    public String readString(String msg) {
        String value = String.valueOf(random.nextInt(MAX_INT));
        print(msg, value);
        return value;
    }

    @Override
    public String readString(String msg, String startWith) {
        String value = String.format("%s_%s", startWith, readString(msg));
        print(msg, value);
        return value;
    }

    @Override
    public Date readDate(String msg) {
        Date value = new Date();
        print(msg, value.toString());
        return value;
    }

    @Override
    public BigDecimal readBigDecimal(String msg, BigDecimal from, BigDecimal to) {
        BigDecimal value;
        int max = to != null ? to.intValue() : Integer.MAX_VALUE;

        if (from != null) {
            value = new BigDecimal(random.nextInt(max - from.intValue()) + from.intValue());
        } else {
            value = new BigDecimal(random.nextInt(max));
        }

        print(msg, value.toString());
        return value;
    }

    private void print(String msg, String value) {
        System.out.printf("%s%s", msg, value);
    }
}