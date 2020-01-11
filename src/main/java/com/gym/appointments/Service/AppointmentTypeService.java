package com.gym.appointments.Service;

import com.gym.appointments.Model.AppointmentType;

import java.util.List;

public interface AppointmentTypeService {

    List<AppointmentType> findAll();

    AppointmentType getAppointmentTypeById(Integer appointmentTypeId);

    AppointmentType add(AppointmentType appointmentType);

    AppointmentType edit(Integer id, AppointmentType appointmentType);

    void delete(Integer appointmentTypeId);
}
