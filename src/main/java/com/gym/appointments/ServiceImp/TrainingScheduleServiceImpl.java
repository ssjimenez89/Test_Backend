package com.gym.appointments.ServiceImp;

import com.gym.appointments.Model.Coach;
import com.gym.appointments.Model.TrainingSchedule;
import com.gym.appointments.Repository.CoachRepository;
import com.gym.appointments.Repository.TrainingScheduleRepository;
import com.gym.appointments.Service.TrainingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingScheduleServiceImpl implements TrainingScheduleService {

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Autowired
    CoachRepository coachRepository;

    @Override
    public List<TrainingSchedule> findAll() {
        return (List<TrainingSchedule>) trainingScheduleRepository.findAll();
    }

    @Override
    public TrainingSchedule getTrainingScheduleById(Integer trainingScheduleId) {
        return trainingScheduleRepository.findById(trainingScheduleId).get();
    }

    @Override
    public TrainingSchedule add(Integer coachId, TrainingSchedule trainingSchedule) {
        Coach coach = coachRepository.findById(coachId).get();
        trainingSchedule.setCoach(coach);
        return trainingScheduleRepository.save(trainingSchedule);
    }

    @Override
    public TrainingSchedule edit(Integer coachId, Integer trainingScheduleId, TrainingSchedule trainingScheduleNew) {
        Coach coach = coachRepository.findById(coachId).get();
        TrainingSchedule trainingSchedule = trainingScheduleRepository.findById(trainingScheduleId).get();
        trainingSchedule.setStartTime(trainingScheduleNew.getStartTime());
        trainingSchedule.setEndTime(trainingScheduleNew.getEndTime());
        trainingSchedule.setDate(trainingScheduleNew.getDate());
        trainingSchedule.setCoach(coach);

        return trainingScheduleRepository.save(trainingSchedule);
    }

    @Override
    public void delete(Integer trainingScheduleId) {
        trainingScheduleRepository.deleteById(trainingScheduleId);
    }


}
