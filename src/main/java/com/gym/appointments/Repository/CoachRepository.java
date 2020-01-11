package com.gym.appointments.Repository;

import com.gym.appointments.Model.Coach;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends PagingAndSortingRepository<Coach, Integer> {
}
