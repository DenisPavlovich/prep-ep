package epam.pre.romanenko.store.services.Impl;


import epam.pre.romanenko.store.menu.MenuItem;
import epam.pre.romanenko.store.services.MenuService;

public class MenuServiceImpl implements MenuService<String> {

    private MenuItem menu;

    public MenuServiceImpl(MenuBuild menuBuild) {
        this.menu = menuBuild.build();
    }

    public void show() {
        if (menu.getParent() == null)
            System.out.println(menu.getTitle());
        else System.out.println("..." + menu.getTitle());

        printMenuItems();
        printCommands();

    }

    private void printMenuItems() {
        for (Object o : menu.getInnerMenuTitles()) {
            String menuItem = o.toString();

            System.out.printf("\t => %s\n", menuItem);
        }
    }

    private void printCommands() {
        for (Object o : menu.getCommandKeys()) {
            String commandKey = o.toString();

            System.out.printf("\t -> %s\n", commandKey);
        }
    }

    public boolean execute(String command) {
        return menu.executeCommand(command) || moveInner(command);
    }

    public boolean moveInner(String titleMenu) {
        MenuItem inner = menu.getInnerMenuItem(titleMenu);
        if (inner != null) {
            menu = inner;
            return true;
        }
        return false;
    }

    public boolean back() {
        MenuItem parent = menu.getParent();
        if (parent != null) {
            menu = parent;
            return true;
        }
        return false;
    }

    public interface MenuBuild {
        MenuItem build();
    }
}

