package com.example.universityapp.service;

import com.example.universityapp.entity.Student;
import com.example.universityapp.entity.Subject;
import com.example.universityapp.payload.ApiResponse;
import com.example.universityapp.payload.StudentSubjectDTO;
import com.example.universityapp.repository.StudentRepository;
import com.example.universityapp.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public ApiResponse selectSubjects(StudentSubjectDTO req) {
        Optional<Student> optionalStudent = studentRepository.findById(req.getStudentId());
        if (!optionalStudent.isPresent()) return new ApiResponse("Xato", false);
        List<Subject> list = subjectRepository.findAllById(req.getSubjectIds());

        Student student = optionalStudent.get();

        student.setSubjectList(list);
        studentRepository.save(student);
        return new ApiResponse("Success", true, student);

    }

}
