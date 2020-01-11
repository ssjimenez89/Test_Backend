package com.gym.appointments.Repository;

import com.gym.appointments.Model.AppointmentType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentTypeRepository extends PagingAndSortingRepository<AppointmentType, Integer> {
}
