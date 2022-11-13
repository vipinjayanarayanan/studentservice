package com.vipin.studentservice.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class StudentDto {

    private String firstName;

    private String lastName;

    private int rollNo;

    private String address;

    private Date dob;
}
