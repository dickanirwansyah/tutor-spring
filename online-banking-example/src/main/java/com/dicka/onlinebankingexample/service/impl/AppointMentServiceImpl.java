package com.dicka.onlinebankingexample.service.impl;

import com.dicka.onlinebankingexample.entity.Appointment;
import com.dicka.onlinebankingexample.repository.AppointMentRepository;
import com.dicka.onlinebankingexample.service.AppointMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AppointMentServiceImpl implements AppointMentService {

    private final AppointMentRepository appointMentRepository;

    @Autowired
    public AppointMentServiceImpl(AppointMentRepository appointMentRepository){
        this.appointMentRepository = appointMentRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointMentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment : appointMentRepository.findAll()){
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public Appointment findAppointment(Long id) {
        return appointMentRepository.findAppointmentById(id);
    }

    @Override
    public void confirmAppointment(Long id) {
        Appointment appointment = this.findAppointment(id);
        appointment.setConfirmed(true);
        appointMentRepository.save(appointment);
    }
}
