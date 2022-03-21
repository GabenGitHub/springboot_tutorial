package com.example.tutorial.translation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter(autoApply = true)
public class TranslationConverter implements AttributeConverter<Translation, String> {
    ObjectMapper mapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(Translation text) {
        String json = "";
        try {
            if (text == null) {
                return null;
            }
            // convert map to JSON string
            json = mapper.writeValueAsString(Collections.singletonList(text));


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
            return json;
    }

    @SneakyThrows
    @Override
    public Translation convertToEntityAttribute(String dbData) {

//        [{"text": {"en": "Hungarian"}}, {"text": {"hu": "Magyar"}}]
        if (dbData != null) {
            List<Translation> list = Arrays.asList(mapper.readValue(dbData, Translation[].class));
            var language = LocaleContextHolder.getLocaleContext().getLocale().getLanguage();
            if (language == null) {
                return null;
            }

            return list.stream().findFirst().orElse(null);
//            return list.stream().filter(translation ->
//                    translation.getText().containsKey(language)
//            ).findFirst().orElse(null);
        }
        return null;
    }
}
