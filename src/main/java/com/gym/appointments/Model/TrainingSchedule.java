package com.gym.appointments.Model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class TrainingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NonNull
    private Date startTime;

    @NonNull
    private Date endTime;

    @NonNull
    private Date date;

    @NonNull
    @ManyToOne
    private Coach coach;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "trainingSchedule")
    private List<Appointment> appointmentList;

}
