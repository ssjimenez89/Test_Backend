package com.gym.appointments.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Member extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    //Date of Registration as member
    @NonNull
    private LocalDate registrationDate = LocalDate.now();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    private List<Appointment> appointmentList;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + String.valueOf(id) +
                ", registrationDate=" + registrationDate.toString() +
                '}';
    }
}
