package com.vipin.studentservice.service;

import com.vipin.studentservice.entity.Student;
import com.vipin.studentservice.repository.StudentRepository;
import com.vipin.studentservice.request.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> listStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id){

        if(!studentRepository.findById(id).isEmpty()){
            return studentRepository.findById(id).get();
        }
        else{
            return null;
        }
    }

    public void createStudent(StudentDto studentDto){
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setRollNo(studentDto.getRollNo());
        student.setAddress(studentDto.getAddress());
        student.setDob(studentDto.getDob());

        studentRepository.save(student);
    }

    public void updateStudent(int id,StudentDto studentDto){
        if(!studentRepository.findById(id).isEmpty()){
            Student student = studentRepository.findById(id).get();

            student.setFirstName(studentDto.getFirstName());
            student.setLastName(studentDto.getLastName());
            student.setRollNo(studentDto.getRollNo());
            student.setAddress(studentDto.getAddress());
            student.setDob(studentDto.getDob());

            studentRepository.save(student);
        }
    }

    public void deleteStudent(int id){
        if(!studentRepository.findById(id).isEmpty()){
            Student student = studentRepository.findById(id).get();
            studentRepository.delete(student);
        }
    }
}
