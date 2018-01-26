package epam.pre.romanenko.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;

public class LocalizationTextBuilder {

    private static Logger LOGGER = Logger.getLogger(LocalizationTextBuilder.class);

    private StringBuilder result;
    private ResourceBundle resourceBundle;

    public LocalizationTextBuilder(ResourceBundle resourceBundle) {
        this.result = new StringBuilder();
        this.resourceBundle = resourceBundle;
    }

    public static LocalizationTextBuilder getTextBuilder(ResourceBundle resourceBundle) {
        return new LocalizationTextBuilder(resourceBundle);
    }

    public LocalizationTextBuilder addTextFromBundle(String key, String endString) {
        addTextFromBundle(key);
        result.append(endString);
        return this;
    }

    public LocalizationTextBuilder addTextFromBundle(String key) {
        addText(resourceBundle.getString(key));
        return this;
    }

    public LocalizationTextBuilder addText(String text, String endString) {
        addText(text);
        result.append(endString);
        return this;
    }

    public LocalizationTextBuilder addText(String text) {
        addString(text);
        return this;
    }

    private void addString(String str) {
        if (result.length() > 0) {
            result.append(" ");
        }
        result.append(str);
    }

    public String buildText() {
        return StringUtils.capitalize(result.toString().toLowerCase());
    }

}
