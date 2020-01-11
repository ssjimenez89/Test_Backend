package com.gym.appointments.Model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NonNull
    @ManyToOne
    private AppointmentType appointmentType;

    @NonNull
    @ManyToOne
    private TrainingSchedule trainingSchedule;

    @NonNull
    @ManyToOne
    private Member member;


}
