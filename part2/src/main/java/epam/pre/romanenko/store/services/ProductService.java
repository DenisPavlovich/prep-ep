package epam.pre.romanenko.store.services;

import epam.pre.romanenko.entities.Being;

import java.io.IOException;
import java.util.Set;

public interface ProductService {

    void add(String key, Being element);

    void remove(String key);

    Set<Being> getElements();

    void writeToFile() throws IOException;

}
