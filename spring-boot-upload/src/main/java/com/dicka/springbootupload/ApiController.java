package com.dicka.springbootupload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/api/upload")
public class ApiController {

    private static String UPLOAD_DIR = System.getProperty("user.home")+"/test";

    @PostMapping(value = "/multiple")
    public ResponseEntity<?> multipleUploadFile(@ModelAttribute UploadForm form){
        System.out.println(form.getDescription());
        String resultOut = null;
        try{
            resultOut = this.saveUpload(form.getFiles());
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<Object>("Error : "+e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Uploaded To : </br>" + resultOut,
                HttpStatus.OK);
    }

    private String saveUpload(MultipartFile[] files) throws IOException{
        File uploadDir = new File(UPLOAD_DIR);
        uploadDir.mkdirs();
        StringBuilder sb = new StringBuilder();

        for (MultipartFile file: files){
            if (file.isEmpty()){
                continue;
            }
            String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);
            sb.append(uploadFilePath).append("</br>");
        }
        return sb.toString();
    }
}
