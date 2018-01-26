package epam.pre.romanenko.store.components.impl;

import epam.pre.romanenko.store.commands.impl.ChangeLanguageCommand;
import epam.pre.romanenko.store.components.LanguageComponent;
import epam.pre.romanenko.store.menu.MenuItem;
import epam.pre.romanenko.store.menu.impl.MenuItemImpl;

import java.util.ResourceBundle;

public class LanguageComponentImpl implements LanguageComponent {

    private ResourceBundle resourceBundle;

    public LanguageComponentImpl() {
        this.resourceBundle = ResourceBundle.getBundle(LanguageComponent.MESSAGE_BUNDLE);
    }

    @Override
    public MenuItem<String> getMenuItem() {
        return MenuItemImpl.newBuilder()
                .setTitle("language")
                .addCommand(new ChangeLanguageCommand("ru", "ru", this))
                .addCommand(new ChangeLanguageCommand("en", "en", this))
                .build();
    }

    @Override
    public ResourceBundle getLocale() {
        return resourceBundle;
    }

    @Override
    public void setLocale(ResourceBundle locale) {
        resourceBundle = locale;
    }
}
