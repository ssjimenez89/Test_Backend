package com.gym.appointments.Model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TrainingSchedule implements Serializable {

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "trainingSchedule")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Appointment> appointmentList;

}
