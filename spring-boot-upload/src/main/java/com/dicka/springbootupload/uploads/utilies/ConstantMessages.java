package com.dicka.springbootupload.uploads.utilies;

public class ConstantMessages {

    public static final String EMPLOYEE_URI = "/employees";
    public static final String EMPLOYEE_JSON_PARAM = "empJson";
    public static final String EMPLOYEE_FILE_PARAM = "file";
    public static final String SUCCESS_CODE = "EMPLOYEE-200";
    public static final String SUCCESS_MSG = "DATA EMPLOYEE SUCESS CREATE";
    public static final String FILE_SEPARATOR = "_";
    public static final String DOWNLOAD_FILE = "/downloadTheFile/";
    public static final String DOWNLOAD_URI = "/downloadTheFile/{fileName:.+}";
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    public static final String FILE_DOWNLOAD_HTTP_HEADER = "attachment;filename=\"%s\"";
    public static final String FILE_PROPERTIES_PREFIX = "file";
    public static final String FILE_STORAGE_EXCEPTION_PATH_NOT_FOUND
            = "could not create the directory where the upload will be stored";
    public static final String INVALID_FILE_PATH_NAME = "sorry! filename contains invalid path sequence";
    public static final String FILE_NOT_FOUND = "file not found";
    public static final String FILE_STORAGE_EXCEPTION = "could not store %s !! please try again";
    public static final CharSequence INVALID_DELIMITER = "..";
    public static final String INDEX_PAGE_URI = "/upload-page";
    public static final String INDEX_PAGE = "upload-page";
    public static final String TEMP_DIR = "";
    public static final String INVALID_FILE_DIMENSIONS = "invalid dimensions";
    public static final String INVALID_FILE_FORMAT = "sorry only jpeg, jpg, png format";
    public static final String PNG_FILE_FORMAT = ".png";
    public static final String JPEG_FILE_FORMAT = ".jpeg";
    public static final String JPG_FILE_FORMAT = ".jpg";
}
