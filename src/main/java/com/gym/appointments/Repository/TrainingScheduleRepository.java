package com.gym.appointments.Repository;

import com.gym.appointments.Model.TrainingSchedule;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingScheduleRepository extends PagingAndSortingRepository<TrainingSchedule, Integer> {
}
