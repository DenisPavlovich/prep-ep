package epam.pre.romanenko.store.commands.impl.show;

import epam.pre.romanenko.entities.Being;
import epam.pre.romanenko.store.commands.AbstractCommand;
import epam.pre.romanenko.store.services.HistoryService;

public class ShowHistoryCommand extends AbstractCommand {

    private HistoryService history;

    public ShowHistoryCommand(String id, HistoryService history) {
        super(id);
        this.history = history;
    }

    @Override
    public void execute() {
        System.out.println("- - - history - - -");
        for (Being being : history.getHistory()) {
            System.out.println(being);
        }
        System.out.println("- - - - - - - - - -");
    }
}
