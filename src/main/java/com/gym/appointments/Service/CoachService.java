package com.gym.appointments.Service;

import com.gym.appointments.Model.Coach;

import java.util.List;

public interface CoachService {

    List<Coach> findAll();

    Coach getCoachById(Integer coachId);

    Coach add(Coach coach);

    Coach edit(Integer coachId, Coach coach);

    void delete(Integer coachId);
}
