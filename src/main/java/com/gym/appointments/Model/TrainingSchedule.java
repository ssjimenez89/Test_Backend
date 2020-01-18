package com.gym.appointments.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private String startTime;

    @NonNull
    private String endTime;

    //Date of the Training
    @NonNull
    private String date;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "coach_id")
    /*@JsonIgnore*/
    private Coach coach;

    /*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "trainingSchedule")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Appointment> appointmentList;*/

    @Override
    public String toString() {
        return "TrainingSchedule{" +
                "id=" + String.valueOf(id) +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", coach=" + coach +
                '}';
    }
}
