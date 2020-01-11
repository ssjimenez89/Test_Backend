package com.gym.appointments.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Coach extends Person {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "coach")
    private List<TrainingSchedule> trainingScheduleList;

    @Builder
    public Coach(Integer id, String name, String firstSurname, String secondSurname, Sex sex, Integer age, Integer phone, List<TrainingSchedule> trainingScheduleList){
       super(id, name, firstSurname, secondSurname, sex, age,phone);
       this.trainingScheduleList = trainingScheduleList;
    }



}
