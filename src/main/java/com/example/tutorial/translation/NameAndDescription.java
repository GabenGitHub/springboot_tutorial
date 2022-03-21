package com.example.tutorial.translation;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class NameAndDescription {

    @Column(name = "name")
    private Translation name;

    @Column(name = "description")
    private Translation description;
}
