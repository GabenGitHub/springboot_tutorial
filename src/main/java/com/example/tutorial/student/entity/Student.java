package com.example.tutorial.student.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Data
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;
    private String telephone;

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
