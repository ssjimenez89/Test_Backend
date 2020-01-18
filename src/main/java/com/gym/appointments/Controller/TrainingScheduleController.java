package com.gym.appointments.Controller;

import com.gym.appointments.Model.TrainingSchedule;
import com.gym.appointments.Service.TrainingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/trainingschedule/{id}")
    public TrainingSchedule getTrainingScheduleById(@PathVariable(value = "id") Integer trainingScheduleId){
        return trainingScheduleService.getTrainingScheduleById(trainingScheduleId);
    }

    @PostMapping("/trainingschedule/{coachId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingSchedule createTrainingSchedule(@PathVariable (value = "coachId") Integer coachId, @Valid @RequestBody TrainingSchedule trainingSchedule){
        return trainingScheduleService.add(coachId, trainingSchedule);
    }

    @PutMapping("/trainingschedule/{coachId}/{id}")
    public TrainingSchedule editTrainingSchedule(@PathVariable (value = "coachId") Integer coachId, @PathVariable(value = "id") Integer trainingScheduleId, @Valid @RequestBody TrainingSchedule trainingSchedule){
        return trainingScheduleService.edit(coachId, trainingScheduleId, trainingSchedule);
    }

    @DeleteMapping("/trainingschedule/{id}")
    public void deleteTrainingSchedule(@PathVariable(value = "id") Integer trainingScheduleId){
        trainingScheduleService.delete(trainingScheduleId);
    }
}
