package com.example.tutorial.translation;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class NameAndDescription {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
