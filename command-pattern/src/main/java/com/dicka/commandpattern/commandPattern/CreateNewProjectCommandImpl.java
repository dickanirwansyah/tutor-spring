package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.Project;
import com.dicka.commandpattern.exception.ResourceNotFoundException;
import com.dicka.commandpattern.model.ProjectRequest;
import com.dicka.commandpattern.repository.PicRepository;
import com.dicka.commandpattern.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateNewProjectCommandImpl extends AbstractCommand<Project, ProjectRequest>
    implements CreateNewProjectCommand{

    private final ProjectRepository projectRepository;
    private final PicRepository picRepository;

    @Autowired
    public CreateNewProjectCommandImpl(ProjectRepository projectRepository,
                                       PicRepository picRepository){
        this.projectRepository = projectRepository;
        this.picRepository = picRepository;
    }

    @Override
    public Project doExecute(ProjectRequest request) {
        return picRepository.findById(request.getPicId())
                .map(currentPic -> {
                    Project project = Project
                            .builder()
                            .name(request.getProjectName())
                            .pic(currentPic)
                            .updateProjectDate(new Date())
                            .projectDate(request.getProjectDate())
                            .build();
                    return projectRepository.save(project);
                }).orElseThrow(() -> new ResourceNotFoundException(
                        "sorry pic : "+request.getPicId()+" tidak ada."
                ));
    }


}
