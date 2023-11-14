package com.praveen.csvupload.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    
    Integer uploadCsvFile(MultipartFile file) throws IOException;
}
