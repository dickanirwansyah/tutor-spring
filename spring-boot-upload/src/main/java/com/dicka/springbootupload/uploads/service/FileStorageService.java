package com.dicka.springbootupload.uploads.service;

import com.dicka.springbootupload.uploaded.model.UploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileStorageService {

    /** store is upload file **/
    public String storeFile(MultipartFile file) throws IOException;

    /** resource is download file **/
    public Resource resourceFile(String fileName);

    /** multiple upload file
    public List<UploadResponse> multipleStoreFile(MultipartFile[] files);
     **/
}
