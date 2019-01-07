package com.dicka.onlinebankingexample.repository;

import com.dicka.onlinebankingexample.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointMentRepository extends JpaRepository<Appointment, Long>{

    List<Appointment> findAll();

    Appointment findAppointmentById(Long id);
}
