package com.gym.appointments.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Appointment implements Serializable {

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
