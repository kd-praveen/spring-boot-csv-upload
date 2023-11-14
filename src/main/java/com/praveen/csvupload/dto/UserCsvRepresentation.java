package com.praveen.csvupload.dto;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCsvRepresentation {

    @CsvBindByName(column = "name")
    private String userName;

    @CsvBindByName(column = "emailId")
    private String email;

    @CsvBindByName(column = "age")
    private Integer age;

    @CsvBindByName(column = "city")
    private String city;
}
