package com.vipin.studentservice.controller;

import com.vipin.studentservice.entity.Student;
import com.vipin.studentservice.request.StudentCourseRequest;
import com.vipin.studentservice.request.StudentDto;
import com.vipin.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<Student>> listStudents() {
        return new ResponseEntity<>(studentService.listStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") int id){
        if(studentService.getStudentById(id)!=null) {
            return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No records found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<String> createStudent(@RequestBody StudentDto studentDto){
        studentService.createStudent(studentDto);
        return new ResponseEntity<>("Successfully created student", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") int id,@RequestBody StudentDto studentDto){
        studentService.updateStudent(id,studentDto);
        return new ResponseEntity<>("Successfully updated student",HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Successfully deleted student",HttpStatus.OK);
    }

    @GetMapping("courses")
    public String listAllCourses(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange("http://localhost:8085/api/course",HttpMethod.GET,entity,String.class).getBody();
    }

    @GetMapping("courses/{id}")
    public ResponseEntity<Object> listCoursesByStudentId(@PathVariable("id") int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return new ResponseEntity<>(restTemplate.exchange("http://localhost:8085/api/course/student/"+id,HttpMethod.GET,entity,String.class).getBody(),HttpStatus.OK);
    }

    @PostMapping("enroll")
    public ResponseEntity<Object> enroll(@RequestBody StudentCourseRequest studentCourseRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<StudentCourseRequest> entity = new HttpEntity<StudentCourseRequest>(studentCourseRequest,headers);

        return new ResponseEntity<>(restTemplate.exchange("http://localhost:8085/api/course/enroll",HttpMethod.POST,entity,String.class).getBody(),HttpStatus.OK);
    }
}
