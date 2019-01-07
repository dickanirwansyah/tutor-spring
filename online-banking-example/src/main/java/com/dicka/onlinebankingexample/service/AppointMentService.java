package com.dicka.onlinebankingexample.service;

import com.dicka.onlinebankingexample.entity.Appointment;

import java.util.List;

public interface AppointMentService {

    Appointment createAppointment(Appointment appointment);

    List<Appointment> findAll();

    Appointment findAppointment(Long id);

    void confirmAppointment(Long id);
}
