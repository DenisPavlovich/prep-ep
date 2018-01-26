package epam.pre.romanenko.store.strategy;

import java.math.BigDecimal;
import java.util.Date;

public interface DataReader {

    Integer readInt(String msg, Integer min, Integer max);

    String readString(String msg);

    String readString(String msg, String startWith);

    Date readDate(String msg);

    BigDecimal readBigDecimal(String msg, BigDecimal min, BigDecimal max);

}
