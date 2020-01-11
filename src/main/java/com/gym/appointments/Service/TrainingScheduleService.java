package com.gym.appointments.Service;

import com.gym.appointments.Model.Coach;
import com.gym.appointments.Model.TrainingSchedule;

import java.util.List;

public interface TrainingScheduleService {

    List<TrainingSchedule> findAll();

    TrainingSchedule getTrainingScheduleById(Integer trainingScheduleId);

    TrainingSchedule add(Integer coachId, TrainingSchedule trainingSchedule);

    TrainingSchedule edit(Integer coachId, Integer trainingScheduleId, TrainingSchedule trainingSchedule);

    void delete(Integer trainingScheduleId);
}
