package epam.pre.romanenko.store.services.Impl;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.repository.ProductRepository;
import epam.pre.romanenko.store.repository.impl.ProductRepositoryImpl;
import epam.pre.romanenko.store.serialization.Serializer;
import epam.pre.romanenko.store.services.ProductService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);

    private Serializer serializer;
    private ProductRepository<String, Being> repository;

    public ProductServiceImpl(Serializer serializer) {
        this.serializer = serializer;

        try {
            repository = new ProductRepositoryImpl<>(serializer.read());
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Can't serialize data repository from file!", e);
        } finally {
            if (repository == null) {
                repository = new ProductRepositoryImpl<>();
            }
        }
    }

    @Override
    public void add(String key, Being element) {
        repository.add(key, element);
    }

    @Override
    public void remove(String key) {
        repository.remove(key);
    }

    @Override
    public Set<Being> getElements() {
        return repository.getElements();
    }

    public void writeToFile() throws IOException {
        serializer.write(repository.getElements());
    }

}