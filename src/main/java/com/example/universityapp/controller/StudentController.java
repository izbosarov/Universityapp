package com.example.universityapp.controller;

import com.example.universityapp.entity.Student;
import com.example.universityapp.payload.ApiResponse;
import com.example.universityapp.payload.StudentSubjectDTO;
import com.example.universityapp.repository.StudentRepository;
import com.example.universityapp.repository.SubjectRepository;
import com.example.universityapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return new ApiResponse("Saved!", true);
    }

    @PostMapping("/selectSubject")
    public ApiResponse selectSub(@RequestBody StudentSubjectDTO req) {
        return studentService.selectSubjects(req);
    }

}
