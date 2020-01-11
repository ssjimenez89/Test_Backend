package com.gym.appointments.Service;

import com.gym.appointments.Model.Appointment;

import java.util.List;

public interface AppointmentService {
    
    List<Appointment> findAll();

    Appointment getAppointmentById(Integer appointmentId);

    Appointment add(Integer memberId, Integer appointmentTypeId, Integer trainingScheduleId, Appointment appointment);

    Appointment edit(Integer memberId, Integer appointmentTypeId, Integer trainingScheduleId, Integer appointmentId, Appointment appointment);

    void delete(Integer appointmentId);
}
