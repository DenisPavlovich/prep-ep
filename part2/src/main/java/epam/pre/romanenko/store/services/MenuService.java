package epam.pre.romanenko.store.services;


/**
 * @param <K> - command's key
 */
public interface MenuService<K> {

    void show();

    boolean execute(K commandKey);

    boolean moveInner(String titleMenu);

    boolean back();

}

