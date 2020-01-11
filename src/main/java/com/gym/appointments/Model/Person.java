package com.gym.appointments.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String firstSurname;

    @NonNull
    private String secondSurname;

    @NonNull
    private Sex sex;

    @NonNull
    private Integer age;

    @NonNull
    private Integer phone;

}



