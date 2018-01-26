package epam.pre.romanenko.store.commands;

public abstract class AbstractCommand implements Command<String> {

    private String id;

    public AbstractCommand(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public abstract void execute();
}
