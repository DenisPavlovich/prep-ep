package epam.pre.romanenko.store.menu.impl;

import epam.pre.romanenko.store.commands.Command;
import epam.pre.romanenko.store.menu.MenuItem;
import epam.pre.romanenko.store.menu.MenuResourceBundle;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuItemImpl implements MenuItem<String> {

    private static final int DEFAULT_CAPACITY = 3;
    private static final float LOAD_FACTOR = 1f;

    private final Map<String, MenuItem> innerMenuItems;
    private final Map<String, Command<String>> commands;

    private String title;
    private MenuItem parent;
    private MenuResourceBundle commandBundle;

    private MenuItemImpl() {
        innerMenuItems = new HashMap<>(DEFAULT_CAPACITY);
        commands = new LinkedHashMap<>(DEFAULT_CAPACITY, LOAD_FACTOR, false);
    }

    public MenuItemImpl(MenuResourceBundle commandBundle) {
        this();
        this.commandBundle = commandBundle;
    }

    public static MenuItem newMenuItem(String title, MenuResourceBundle bundle) {
        return new MenuItemImpl().new MenuItemBuilder(title, bundle).build();
    }

    public static MenuItemBuilder newBuilder() {
        return new MenuItemImpl().new MenuItemBuilder();
    }

    @Override
    public MenuItem getParent() {
        return parent;
    }

    @Override
    public void setParent(MenuItem menuItem) {
        parent = menuItem;
    }

    @Override
    public String[] getCommandKeys() {
        return commands.keySet().toArray(new String[commands.size()]);
    }

    @Override
    public boolean executeCommand(String commandKey) {
        Command command = commands.get(commandKey);
        if (command != null) {
            commands.get(commandKey).execute();
            return true;
        }
        return false;
    }

    @Override
    public String[] getInnerMenuTitles() {
        return innerMenuItems.keySet().toArray(new String[innerMenuItems.size()]);
    }

    @Override
    public MenuItem getInnerMenuItem(String menuItemId) {
        MenuItem item = innerMenuItems.get(menuItemId);
        if (item != null)
            item.update();
        return item;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void update() {
        if (commandBundle != null) {
            for (Command command : commandBundle.getUpdatedCommands()) {
                this.commands.putIfAbsent(command.getId().toString(), command);
            }
        }
    }

    public class MenuItemBuilder {

        public MenuItemBuilder() {
        }

        public MenuItemBuilder(String title, MenuResourceBundle bundle) {
            setTitle(title);
            commandBundle = bundle;
        }

        public MenuItemBuilder setTitle(String title) {
            MenuItemImpl.this.title = title;
            return this;
        }

        public MenuItemBuilder addCommand(Command<String> command) {
            MenuItemImpl.this.commands.put(command.getId(), command);
            return this;
        }

        public MenuItemBuilder addInnerMenuItem(MenuItem menuItem) {
            MenuItemImpl.this.innerMenuItems.put(menuItem.getTitle(), menuItem);
            menuItem.setParent(MenuItemImpl.this);
            return this;
        }

        public MenuItemImpl build() {
            MenuItemImpl.this.update();
            return MenuItemImpl.this;
        }

    }

}
