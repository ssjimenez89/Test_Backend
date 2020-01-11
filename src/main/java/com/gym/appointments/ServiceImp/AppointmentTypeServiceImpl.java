package com.gym.appointments.ServiceImp;

import com.gym.appointments.Model.AppointmentType;
import com.gym.appointments.Repository.AppointmentTypeRepository;
import com.gym.appointments.Service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {

    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public List<AppointmentType> findAll() {
        return (List<AppointmentType>) appointmentTypeRepository.findAll();
    }

    @Override
    public AppointmentType getAppointmentTypeById(Integer appointmentTypeId) {
        return appointmentTypeRepository.findById(appointmentTypeId).get();
    }

    @Override
    public AppointmentType add(AppointmentType appointmentType) {
        return appointmentTypeRepository.save(appointmentType);
    }

    @Override
    public AppointmentType edit(Integer id, AppointmentType appointmentTypeNew) {
        AppointmentType appointmentType = appointmentTypeRepository.findById(id).get();
        appointmentType.setName(appointmentTypeNew.getName());
        appointmentType.setDescription(appointmentTypeNew.getDescription());
        return appointmentTypeRepository.save(appointmentType);
    }

    @Override
    public void delete(Integer appointmentTypeId) {
        appointmentTypeRepository.deleteById(appointmentTypeId);
    }
}
