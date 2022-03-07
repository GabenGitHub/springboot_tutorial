package com.example.tutorial.translation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.*;

@Converter(autoApply = true)
public class TranslationConverter implements AttributeConverter<List<Translation>, String> {
    ObjectMapper mapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(List<Translation> text) {
        String json = "";
        try {
            if (text == null) {
                return null;
            }
            // convert map to JSON string
            json = mapper.writeValueAsString(text);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
            return json;
    }

    @SneakyThrows
    @Override
    public List<Translation> convertToEntityAttribute(String dbData) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        if (dbData != null) {
            List<Translation> list = Arrays.asList(mapper.readValue(dbData, Translation[].class));
            System.out.println(list.get(0));
            return list;
        }


        return null;
    }
}
