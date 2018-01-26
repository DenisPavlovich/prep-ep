package epam.pre.romanenko.store.serialization;

import java.io.IOException;

public interface Serializer {

    /**
     * Writes object to file
     *
     * @param obj - object which will be write to file
     */
    void write(Object obj) throws IOException;

    /**
     * Returns an object from file
     *
     * @param <T> - returned type
     * @return - an object from file
     */
    <T> T read() throws IOException, ClassNotFoundException;

}
