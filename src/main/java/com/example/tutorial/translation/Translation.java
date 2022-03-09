package com.example.tutorial.translation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
//@JsonIgnoreProperties(value = { "textValue" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class Translation implements Serializable {
    private Map<String, String> text;

    public void addValueForLanguage(String language, String value) {
        if (text == null) {
            text = new HashMap<>();
        }
        text.put(language, value);
    }

    public String getTextValue() {
        final String language = LocaleContextHolder.getLocaleContext().getLocale().getLanguage();
        if (language == null) {
            return null;
        }
        String translation = text.get(language);

        return translation;
    }
}
