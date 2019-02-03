package com.dicka.springbootupload.uploads.service;

import com.dicka.springbootupload.uploaded.exception.FileStorageException;
import com.dicka.springbootupload.uploaded.model.UploadResponse;
import com.dicka.springbootupload.uploads.config.ConfigFileStorageProperties;
import com.dicka.springbootupload.uploads.utilies.ConstantMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    /** location storage file **/
    private final Path fileStorageLocation;
    private final Logger LOGGER = LoggerFactory.getLogger(FileStorageServiceImpl.class);

    @Autowired
    public FileStorageServiceImpl(ConfigFileStorageProperties configFileStorageProperties){
        this.fileStorageLocation = Paths
                .get(configFileStorageProperties.getUploadDir())
                .toAbsolutePath()
                .normalize();

        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception ex){
            throw new FileStorageException(ConstantMessages.FILE_STORAGE_EXCEPTION_PATH_NOT_FOUND);
        }
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        /** validasi format image **/
        LOGGER.info("--storeFile()--> action upload file <---");
        /**
        if (!file.getOriginalFilename().endsWith(ConstantMessages.PNG_FILE_FORMAT) ||
                file.getOriginalFilename().endsWith(ConstantMessages.JPEG_FILE_FORMAT) ||
                file.getOriginalFilename().endsWith(ConstantMessages.JPG_FILE_FORMAT))
            throw new FileStorageException(ConstantMessages.INVALID_FILE_FORMAT);
         **/

        if (!(file.getOriginalFilename().endsWith(ConstantMessages.PNG_FILE_FORMAT)||
        file.getOriginalFilename().endsWith(ConstantMessages.JPEG_FILE_FORMAT) ||
        file.getOriginalFilename().endsWith(ConstantMessages.JPG_FILE_FORMAT)))
            throw new FileStorageException(ConstantMessages.INVALID_FILE_FORMAT);

        if (fileName.contains("..")) {
            throw new FileStorageException(ConstantMessages.INVALID_FILE_PATH_NAME);
        }

        try{
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (Exception e){
            e.printStackTrace();
            throw new FileStorageException("failed to upload ", e);
        }

        /** check path location file **/

    }

    @Override
    public Resource resourceFile(String fileName) {
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName)
                    .normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()){
                return resource;
            }else{
                throw new FileStorageException(ConstantMessages.FILE_NOT_FOUND+fileName);
            }
        }catch (Exception ex){
            throw new FileStorageException(ConstantMessages.FILE_NOT_FOUND+fileName, ex);
        }
    }
}
