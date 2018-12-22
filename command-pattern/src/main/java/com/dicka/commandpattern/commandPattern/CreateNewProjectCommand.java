package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.Project;
import com.dicka.commandpattern.model.ProjectRequest;

public interface CreateNewProjectCommand extends Command<Project, ProjectRequest>{
}
