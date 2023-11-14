package com.praveen.csvupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.csvupload.models.User;

public interface FileUploadRepository extends JpaRepository<User, Long> {
    
}
