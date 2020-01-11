package com.gym.appointments.ServiceImp;

import com.gym.appointments.Model.Coach;
import com.gym.appointments.Repository.CoachRepository;
import com.gym.appointments.Service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    CoachRepository coachRepository;


    @Override
    public List<Coach> findAll() {
        return (List<Coach>) coachRepository.findAll();
    }

    @Override
    public Coach getCoachById(Integer coachId) {
        return coachRepository.findById(coachId).get();
    }

    @Override
    public Coach add(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public Coach edit(Integer coachId, Coach coachNew) {
        Coach coach = coachRepository.findById(coachId).get();
        coach.setName(coachNew.getName());
        coach.setFirstSurname(coachNew.getFirstSurname());
        coach.setSecondSurname(coachNew.getSecondSurname());
        coach.setAge(coachNew.getAge());
        coach.setSex(coachNew.getSex());
        coach.setPhone(coachNew.getPhone());
        return coachRepository.save(coach);
    }

    @Override
    public void delete(Integer coachId) {
        coachRepository.deleteById(coachId);
    }


}
