package com.dicka.springbootupload.uploaded.controller;

import com.dicka.springbootupload.uploaded.model.UploadResponse;
//import com.dicka.springbootupload.uploaded.service.FileStorageService;
import com.dicka.springbootupload.uploads.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/upload")
public class RestControllerUpload {

    //@Autowired
    //private FileStorageService fileStorageService;

    @Autowired
    private FileStorageService storageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerUpload.class);

    /**
    //Download file
    @GetMapping(value = "/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName")String fileName,
                                                 HttpServletRequest request){

        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try{
            contentType = request.getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException e){
            LOGGER.info("failed download file", e);
        }

        if (contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }

    **/
    /**
    @PostMapping(value = "/multiple-upload")
    public List<UploadResponse> multipleUpload(@RequestParam("files")MultipartFile[] files){
        return Arrays.asList(files)
                .stream()
                .map(file -> singleUpload(file))
                .collect(Collectors.toList());
    }
     **/

    /**
    @PostMapping(value = "/multiple-upload")
    public List<UploadResponse> multipleUpload(@RequestParam("files")MultipartFile[] files){
        // files is array data
        return Arrays.asList(files)
                .stream()
                .map(file -> singleUpload(file))
                .collect(Collectors.toList());
    }
    **/

    @PostMapping(value = "/single-upload")
    public UploadResponse singleUpload(@RequestParam("file")MultipartFile file) throws IOException{
        //String fileName = fileStorageService.storeFile(file);
        String fileName = storageService.storeFile(file);
        System.out.print("---fileName--- ::"+"--("+fileName+")--");

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/upload")
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        System.out.println("--fileDownloadUri-- ::"+"(--"+fileDownloadUri+"---)");
        return new UploadResponse(
                //fileName
                fileName,
                //fileDownloadUri
                fileDownloadUri,
                //fileType
                file.getContentType(),
                //size
                file.getSize()
        );
    }
}
