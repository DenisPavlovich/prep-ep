package epam.pre.romanenko.store.services.Impl;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.repository.History;
import epam.pre.romanenko.store.repository.impl.HistoryImpl;
import epam.pre.romanenko.store.services.HistoryService;

import java.util.Collection;
import java.util.UUID;

public class HistoryServiceImpl implements HistoryService {

    private History<UUID, Being> history;

    public HistoryServiceImpl() {
        this.history = new HistoryImpl<>();
    }

    @Override
    public Collection<Being> getHistory() {
        return history.getHistory();
    }

    @Override
    public void add(UUID key, Being element) {
        history.add(key, element);
    }
}
