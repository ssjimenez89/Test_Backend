package com.gym.appointments.Controller;

import com.gym.appointments.Model.Appointment;
import com.gym.appointments.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Appointment getAppointmentById(@PathVariable(value = "id") Integer appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }

    @PostMapping("/appointment/{memberId}/{appointmentTypeId}/{trainingScheduleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment createAppointment(@PathVariable(value = "memberId") Integer memberId, @PathVariable(value = "appointmentTypeId") Integer appointmentTypeId, @PathVariable(value = "trainingScheduleId") Integer trainingScheduleId,   @Valid @RequestBody Appointment appointment){
        return appointmentService.add(memberId, appointmentTypeId, trainingScheduleId, appointment);
    }

    @PutMapping("/appointment/{memberId}/{appointmentTypeId}/{trainingScheduleId}/{id}")
    public Appointment editAppointment(@PathVariable(value = "memberId") Integer memberId, @PathVariable(value = "appointmentTypeId") Integer appointmentTypeId, @PathVariable(value = "trainingScheduleId") Integer trainingScheduleId, @PathVariable(value = "id") Integer appointmentId,   @Valid @RequestBody Appointment appointment){
        return appointmentService.edit(memberId, appointmentTypeId, trainingScheduleId, appointmentId, appointment);
    }

    @DeleteMapping("/appointment/{id}")
    public void deleteAppointment(@PathVariable(value = "id") Integer appointmentId){
        appointmentService.delete(appointmentId);
    }



}
