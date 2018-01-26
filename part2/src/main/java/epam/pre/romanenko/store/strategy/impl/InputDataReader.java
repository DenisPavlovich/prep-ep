package epam.pre.romanenko.store.strategy.impl;

import epam.pre.romanenko.store.strategy.DataReader;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputDataReader implements DataReader {

    private static final String BAD_FORMAT = "bad format...";

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss");

    private Scanner in = new Scanner(System.in);

    @Override
    public Integer readInt(String msg, Integer from, Integer to) throws NumberFormatException {
        int max = to != null ? to : Integer.MAX_VALUE;
        Integer value;

        if (msg != null) {
            print(msg);
        }
        value = Integer.valueOf(in.nextLine());
        if ((value > max) && (from != null && value < from)) {
            value = null;
            System.out.println(BAD_FORMAT);
        }

        return value;
    }

    @Override
    public String readString(String msg) {
        print(msg);
        return in.nextLine();
    }

    @Override
    public String readString(String msg, String startWith) {
        print(msg);
        return String.format("%s_%s", startWith, readString(null));
    }

    @Override
    public Date readDate(String msg) {
        Date date = null;
        try {
            print(msg);
            date = format.parse(in.nextLine());
        } catch (ParseException e) {
            System.out.println(BAD_FORMAT);
        }
        return date;
    }

    @Override
    public BigDecimal readBigDecimal(String msg, BigDecimal from, BigDecimal to) throws NumberFormatException {
        print(msg);
        return new BigDecimal(readInt(msg, from.intValue(), to.intValue()));
    }

    private void print(String msg) {
        System.out.print(msg);
    }

}