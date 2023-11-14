package com.praveen.csvupload.services.implementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.praveen.csvupload.dto.UserCsvRepresentation;
import com.praveen.csvupload.models.User;
import com.praveen.csvupload.repository.FileUploadRepository;
import com.praveen.csvupload.services.FileUploadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileUploadImpl implements FileUploadService {

    private final FileUploadRepository uploadRepository;

    @Override
    public Integer uploadCsvFile(MultipartFile file) throws IOException {

        Set<User> users = parseCsv(file);

        // Filter out users whose email already exists in the database
        users.removeIf(user -> isEmailExists(user.getEmail()));

        uploadRepository.saveAll(users);

        return users.size();
    }

    private boolean isEmailExists(String email) {
        Optional<User> user = uploadRepository.findByEmail(email);
        return user.isPresent();
    }

    private Set<User> parseCsv(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<UserCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(UserCsvRepresentation.class);

            CsvToBean<UserCsvRepresentation> csvToBean = new CsvToBeanBuilder<UserCsvRepresentation>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Set<User> users = new HashSet<>();
            for (UserCsvRepresentation csvLine : csvToBean.parse()) {
                users.add(User.builder()
                        .userName(csvLine.getUserName())
                        .email(csvLine.getEmail())
                        .age(csvLine.getAge())
                        .city(csvLine.getCity())
                        .build());
            }
            return users;
        }
    }

}
