package com.gym.appointments.Controller;

import com.gym.appointments.Model.AppointmentType;
import com.gym.appointments.Service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentTypeController {

    @Autowired
    AppointmentTypeService appointmentTypeService;

    @GetMapping("/appointmenttype")
    public List<AppointmentType> findAll(){
        return appointmentTypeService.findAll();
    }

    @GetMapping("/appointmenttype/{id}")
    public AppointmentType getAppointmentTypeById(@PathVariable(value = "id") Integer appointmentTypeId){
        return appointmentTypeService.getAppointmentTypeById(appointmentTypeId);
    }

    @PostMapping("/appointmenttype")
    public AppointmentType createAppointmentType(@Valid @RequestBody AppointmentType appointmentType){
        return appointmentTypeService.add(appointmentType);
    }

    @PutMapping("/appointmenttype/{id}")
    public AppointmentType editAppointmentType(@PathVariable(value = "id") Integer appointmentTypeId, @Valid @RequestBody AppointmentType appointmentType){
        return appointmentTypeService.edit(appointmentTypeId, appointmentType);
    }

    @DeleteMapping("/appointmenttype/{id}")
    public void deleteAppointmentType(@PathVariable(value = "id") Integer appointmentTypeId){
        appointmentTypeService.delete(appointmentTypeId);
    }

}
