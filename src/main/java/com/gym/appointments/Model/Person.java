package com.gym.appointments.Model;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", sex=" + sex +
                ", age=" + String.valueOf(age) +
                ", phone=" + String.valueOf(phone) +
                '}';
    }
}



