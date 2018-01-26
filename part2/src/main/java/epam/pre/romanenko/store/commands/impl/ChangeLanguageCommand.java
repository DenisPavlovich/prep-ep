package epam.pre.romanenko.store.commands.impl;

import epam.pre.romanenko.store.commands.AbstractCommand;
import epam.pre.romanenko.store.components.LanguageComponent;

import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLanguageCommand extends AbstractCommand {

    private String language;
    private LanguageComponent languageComponent;

    public ChangeLanguageCommand(String id, String newLanguage, LanguageComponent languageComponent) {
        super(id);
        this.language = newLanguage;
        this.languageComponent = languageComponent;
    }

    @Override
    public void execute() {
        languageComponent.setLocale(ResourceBundle.getBundle(LanguageComponent.MESSAGE_BUNDLE, new Locale(language)));
    }
}