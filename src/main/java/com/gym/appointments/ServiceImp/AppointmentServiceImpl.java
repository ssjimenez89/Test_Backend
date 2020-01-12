package com.gym.appointments.ServiceImp;

import com.gym.appointments.Model.Appointment;
import com.gym.appointments.Model.AppointmentType;
import com.gym.appointments.Model.Member;
import com.gym.appointments.Model.TrainingSchedule;
import com.gym.appointments.Repository.AppointmentRepository;
import com.gym.appointments.Repository.AppointmentTypeRepository;
import com.gym.appointments.Repository.MemberRepository;
import com.gym.appointments.Repository.TrainingScheduleRepository;
import com.gym.appointments.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointmentList = (List<Appointment>) appointmentRepository.findAll();
        return appointmentList;
    }

    @Override
    public Appointment getAppointmentById(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }

    @Override
    public Appointment add(Integer memberId, Integer appointmentTypeId, Integer trainingScheduleId, Appointment appointment) {
        Member member = memberRepository.findById(memberId).get();
        AppointmentType appointmentType = appointmentTypeRepository.findById(appointmentTypeId).get();
        TrainingSchedule trainingSchedule = trainingScheduleRepository.findById(trainingScheduleId).get();

        appointment.setMember(member);
        appointment.setAppointmentType(appointmentType);
        appointment.setTrainingSchedule(trainingSchedule);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment edit(Integer memberId, Integer appointmentTypeId, Integer trainingScheduleId, Integer appointmentId, Appointment appointmentNew) {
        Member member = memberRepository.findById(memberId).get();
        AppointmentType appointmentType = appointmentTypeRepository.findById(appointmentTypeId).get();
        TrainingSchedule trainingSchedule = trainingScheduleRepository.findById(trainingScheduleId).get();
        Appointment appointment = appointmentRepository.findById(appointmentId).get();

        appointment.setName(appointmentNew.getName());
        appointment.setMember(member);
        appointment.setAppointmentType(appointmentType);
        appointment.setTrainingSchedule(trainingSchedule);

        return appointmentRepository.save(appointment);
    }

    @Override
    public void delete(Integer appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

}
