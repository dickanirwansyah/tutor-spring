package com.dicka.springbootupload.uploads.config;

import com.dicka.springbootupload.uploads.utilies.ConstantMessages;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ConstantMessages.FILE_PROPERTIES_PREFIX)
public class ConfigFileStorageProperties {

    private String uploadDir;

    public String getUploadDir(){
        return uploadDir;
    }

    public void setUploadDir(String uploadDir){
        this.uploadDir = uploadDir;
    }
}
