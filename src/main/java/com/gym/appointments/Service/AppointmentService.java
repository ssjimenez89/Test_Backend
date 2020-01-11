package com.gym.appointments.Service;

import com.gym.appointments.Model.Appointment;

import java.util.List;

public interface AppointmentService {
    
    List<Appointment> findAll();

    Appointment getAppointmentById(Integer appointmentId);

    Appointment add(Integer memberId, Integer appointmentTypeId, Integer trainingScheduleId, Appointment appointment);
}
