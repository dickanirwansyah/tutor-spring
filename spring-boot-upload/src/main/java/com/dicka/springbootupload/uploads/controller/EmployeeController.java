package com.dicka.springbootupload.uploads.controller;

import com.dicka.springbootupload.uploads.Employee;
import com.dicka.springbootupload.uploads.model.EmployeeResponse;
import com.dicka.springbootupload.uploads.repository.EmployeeRepository;
import com.dicka.springbootupload.uploads.service.EmployeeService;
import com.dicka.springbootupload.uploads.service.FileStorageService;
import com.dicka.springbootupload.uploads.utilies.ConstantMessages;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private FileStorageService fileStoreService;

    /** list file **/
    @GetMapping
    public List<Employee> listEmployee(){
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeService.getAll()){
            employees.add(employee);
        }
        return employees;
    }

    @PostMapping(value = "/create")
    public EmployeeResponse createEmployee(
            @RequestParam(value = "empJson") String empJson,
            @RequestParam(value = "file")MultipartFile file) throws JsonParseException, JsonMappingException, IOException{

        String fileName = this.fileStoreService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/employee")
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        System.out.println("CONTROLLER --> fileDownloadUri : "+fileDownloadUri);

        Employee employee = objectMapper.readValue(empJson, Employee.class);
        employee.setProfilePicPath(fileDownloadUri);
        employeeService.createEmployee(employee);

        return new EmployeeResponse(
                ConstantMessages.SUCCESS_CODE,
                ConstantMessages.SUCCESS_MSG
        );
    }


    /** unduh file **/
    @GetMapping(value = "/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> unduhFile(@PathVariable(value = "fileName")String fileName,
                                              HttpServletRequest request){

        Resource resource = this.fileStoreService.resourceFile(fileName);
        String contentType = null;
        try{
            contentType = request.getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());

        }catch (IOException io){
            io.printStackTrace();
        }

        if (contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; " +
                        "filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }
}
