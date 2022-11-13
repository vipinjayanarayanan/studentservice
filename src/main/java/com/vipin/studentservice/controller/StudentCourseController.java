package com.vipin.studentservice.controller;

import com.vipin.studentservice.request.StudentCourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RequestMapping("api")
@RestController
public class StudentCourseController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/student/course/enroll")
    public ResponseEntity<String> enroll(@RequestBody StudentCourseRequest studentCourseRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<StudentCourseRequest> entity = new HttpEntity<StudentCourseRequest>(studentCourseRequest,headers);
        restTemplate.exchange("http://localhost:8085/api/course/enroll", HttpMethod.POST,entity,String.class).getBody();
        return new ResponseEntity<>("Enrolled successfully", HttpStatus.OK);
    }
}
