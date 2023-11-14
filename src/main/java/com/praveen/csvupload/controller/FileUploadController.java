package com.praveen.csvupload.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.praveen.csvupload.services.FileUploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/upload/")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping(value = "csv", consumes = { "multipart/form-data" })
    private ResponseEntity<Integer> uploadCsvFile(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileUploadService.uploadCsvFile(file));
    }
}
