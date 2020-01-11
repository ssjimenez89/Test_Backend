package com.gym.appointments.Model;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Setter
@Getter
public class Person {

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



