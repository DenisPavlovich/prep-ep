package epam.pre.romanenko.store.commands.impl.show;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.commands.AbstractOrderCommand;
import epam.pre.romanenko.store.services.OrderContainerService;
import epam.pre.romanenko.store.strategy.DataReader;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class ShowOrdersCommand extends AbstractOrderCommand {

    public ShowOrdersCommand(String id, OrderContainerService orderService, DataReader dataReader) {
        super(id, orderService, dataReader);
    }

    private static boolean datesIsNotNull(Date... dates) {
        for (Date date : dates) {
            if (date == null) return false;
        }
        return true;
    }

    @Override
    public void execute() {
        System.out.println(TEXT_DATE_FORMAT);
        Date from = dataReader.readDate(String.format(TEXT_INPUT, "from"));
        Date to = dataReader.readDate(String.format(TEXT_INPUT, "to"));

        if (datesIsNotNull(from, to)) {
            Collection<Set> resultCart = orderService.getOrders(from, to);

            System.out.println("- - - orders - - -");
            if (resultCart != null) {
                for (Set<Being> cart : resultCart) {
                    for (Being being : cart) {
                        System.out.println(being);
                    }
                    System.out.println(cart);
                }
            }
            System.out.println("- - - - - - - - - -");
        }
    }
}
