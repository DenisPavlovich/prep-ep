package epam.pre.romanenko.store.commands.impl.show;

import epam.pre.romanenko.store.commands.AbstractOrderCommand;
import epam.pre.romanenko.store.services.OrderContainerService;
import epam.pre.romanenko.store.strategy.DataReader;

import java.util.Date;
import java.util.Set;

public class ShowOrderClosestToCommand extends AbstractOrderCommand {

    public ShowOrderClosestToCommand(String id, OrderContainerService orderService, DataReader dataReader) {
        super(id, orderService, dataReader);
    }

    @Override
    public void execute() {
        System.out.println(TEXT_DATE_FORMAT);

        Date date = dataReader.readDate(TEXT_INPUT);
        Set resultCart = orderService.getOrderClosestTo(date);

        System.out.println("- - - orders - - -");
        if (resultCart != null)
            for (Object being : resultCart) {
                System.out.println(being);
            }

        System.out.println("- - - - - - - - - -");
    }

}
