package com.gym.appointments.Controller;

import com.gym.appointments.Model.TrainingSchedule;
import com.gym.appointments.Service.TrainingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainingScheduleController {

    @Autowired
    TrainingScheduleService trainingScheduleService;

    @GetMapping("/trainingschedule")
    public List<TrainingSchedule> findAll(){
        return trainingScheduleService.findAll();
    }
}
