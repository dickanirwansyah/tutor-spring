package com.dicka.commandpattern.repository;

import com.dicka.commandpattern.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
