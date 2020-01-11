package com.gym.appointments.Controller;

import com.gym.appointments.Model.Coach;
import com.gym.appointments.Service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CoachController {

    @Autowired
    CoachService coachService;

    @GetMapping("/coach")
    public List<Coach> findAll(){
        return coachService.findAll();
    }

    @GetMapping("/coach/{id}")
    public Coach getCoachById(@PathVariable(value = "id") Integer coachId){
        return coachService.getCoachById(coachId);
    }

    @PostMapping("/coach")
    public Coach createCoach(@Valid @RequestBody Coach coach){
        return coachService.add(coach);
    }

    @PutMapping("/coach/{id}")
    public Coach editCoach(@PathVariable(value = "id") Integer coachId, @Valid @RequestBody Coach coach){
        return coachService.edit(coachId, coach);
    }

    @DeleteMapping("/coach/{id}")
    public void deleteCoach(@PathVariable(value = "id") Integer coachId){
        coachService.delete(coachId);
    }
}
