package com.example.tutorial.translation;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class NameAndDescription {

    @Column(name = "name")
    @Convert(converter = TranslationConverter.class)
    private Translation name;

    @Column(name = "description")
    @Convert(converter = TranslationConverter.class)
    private Translation description;
}
