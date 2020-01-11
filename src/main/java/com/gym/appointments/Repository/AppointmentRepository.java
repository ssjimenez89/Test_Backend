package com.gym.appointments.Repository;

import com.gym.appointments.Model.Appointment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, Integer> {
}
