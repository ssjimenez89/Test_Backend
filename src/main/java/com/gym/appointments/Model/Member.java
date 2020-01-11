package com.gym.appointments.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Member extends Person {

    @NonNull
    private Date registrationDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    private List<Appointment> appointmentList;

    @Builder
    public Member(Integer id, String name, String firstSurname, String secondSurname, Sex sex, Integer age, Integer phone, Date registrationDate, List<Appointment> appointmentList){
        super(id, name, firstSurname, secondSurname, sex, age,phone);
        this.registrationDate = registrationDate;
        this.appointmentList = appointmentList;
    }

}
