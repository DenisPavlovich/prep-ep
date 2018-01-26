package epam.pre.romanenko.store.services;

import epam.pre.romanenko.entities.Being;

import java.util.Collection;
import java.util.UUID;

public interface HistoryService {

    Collection<Being> getHistory();

    void add(UUID key, Being element);

}
