package epam.pre.romanenko.store.commands;

import epam.pre.romanenko.store.services.OrderContainerService;
import epam.pre.romanenko.store.strategy.DataReader;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class AbstractOrderCommand extends AbstractCommand {

    protected final static String TEXT_DATE_FORMAT = "0000.00.00T00:00:00";
    protected final static String TEXT_INPUT = "input %s:\n";

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss");
    protected OrderContainerService orderService;
    protected DataReader dataReader;
    private Scanner in;

    public AbstractOrderCommand(String id, OrderContainerService orderService, DataReader dataReader) {
        super(id);
        this.orderService = orderService;
        format.setLenient(false);
        in = new Scanner(System.in);
        this.dataReader = dataReader;
    }

    @Override
    abstract public void execute();

}
