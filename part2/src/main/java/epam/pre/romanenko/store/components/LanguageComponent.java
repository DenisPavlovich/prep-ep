package epam.pre.romanenko.store.components;

import java.util.ResourceBundle;

public interface LanguageComponent extends AppComponent {

    String MESSAGE_BUNDLE = "messages";

    ResourceBundle getLocale();

    void setLocale(ResourceBundle locale);

}
