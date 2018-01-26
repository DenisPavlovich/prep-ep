package epam.pre.romanenko.store.menu;

/**
 * * Menu items
 *
 * @param <K> - command's and menuItem's id
 */
public interface MenuItem<K> {

    /**
     * Parent MenuItem, if a MenuItem is a root, than parent returns null
     *
     * @return - parent MenuItem
     */
    MenuItem getParent();

    void setParent(MenuItem parent);

    /**
     * Returns all command's keys
     *
     * @return - all command's keys
     */
    K[] getCommandKeys();

    /**
     * run method execute() in Command
     *
     * @param commandKey - command's key
     */
    boolean executeCommand(K commandKey);

    K[] getInnerMenuTitles();

    MenuItem getInnerMenuItem(K menuItemId);

    String getTitle();

    void update();
}
