package com.gym.appointments.ServiceImp;

import com.gym.appointments.Model.TrainingSchedule;
import com.gym.appointments.Repository.TrainingScheduleRepository;
import com.gym.appointments.Service.TrainingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingScheduleServiceImpl implements TrainingScheduleService {

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Override
    public List<TrainingSchedule> findAll() {
        return (List<TrainingSchedule>) trainingScheduleRepository.findAll();
    }
}
