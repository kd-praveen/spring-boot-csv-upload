package com.praveen.csvupload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.csvupload.models.User;

public interface FileUploadRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
