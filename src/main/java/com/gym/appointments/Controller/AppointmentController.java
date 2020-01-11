package com.gym.appointments.Controller;

import com.gym.appointments.Model.Appointment;
import com.gym.appointments.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/appointment")
    public List<Appointment> findAll(){
        return appointmentService.findAll();
    }

    @GetMapping("/appointment/{id}")
    public Appointment getAppointmentTypeById(@PathVariable(value = "id") Integer appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }

    @PostMapping("member/{memberId}/appointmentType/{appointmentTypeId}/trainingSchedule/{trainingScheduleId}/appointment")
    public Appointment createAppointmentType(@PathVariable(value = "memberId") Integer memberId, @PathVariable(value = "appointmentTypeId") Integer appointmentTypeId, @PathVariable(value = "trainingScheduleId") Integer trainingScheduleId,   @Valid @RequestBody Appointment appointment){
        return appointmentService.add(memberId, appointmentTypeId, trainingScheduleId, appointment);
    }

}
