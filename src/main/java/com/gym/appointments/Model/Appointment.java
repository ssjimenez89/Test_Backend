package com.gym.appointments.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String name;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "appointment_type_id")
    @JsonIgnore
    private AppointmentType appointmentType;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "training_schedule_id")
    @JsonIgnore
    private TrainingSchedule trainingSchedule;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;


}
