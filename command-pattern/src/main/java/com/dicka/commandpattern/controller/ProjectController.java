package com.dicka.commandpattern.controller;

import com.dicka.commandpattern.commandPattern.CreateNewProjectCommand;
import com.dicka.commandpattern.commandPattern.ServiceExecutor;
import com.dicka.commandpattern.model.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {

    private final ServiceExecutor serviceExecutor;
    private Map<String, String> errorsValidation;

    @Autowired
    public ProjectController(ServiceExecutor serviceExecutor){
        this.serviceExecutor = serviceExecutor;
    }

    /** create new project **/
    @PostMapping(value = "/create/{picId}")
    public ResponseEntity<Object> createData(@RequestBody @Valid ProjectRequest request,
                                             @PathVariable("picId") Long picId,
                                             BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            errorsValidation = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                errorsValidation.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errorsValidation, HttpStatus.NOT_ACCEPTABLE);
        }

        ProjectRequest projectRequest = ProjectRequest
                .builder()
                .projectName(request.getProjectName())
                .projectDate(request.getProjectDate())
                .picId(picId)
                .build();

        serviceExecutor.execute(CreateNewProjectCommand.class, projectRequest);

        return new ResponseEntity<Object>(projectRequest, HttpStatus.CREATED);
    }

}
