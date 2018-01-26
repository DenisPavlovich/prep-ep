package epam.pre.romanenko.store.commands;

public interface Command<K> {

    K getId();

    void execute();
}
