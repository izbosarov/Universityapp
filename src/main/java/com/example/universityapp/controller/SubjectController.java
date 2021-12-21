package com.example.universityapp.controller;

import com.example.universityapp.entity.Subject;
import com.example.universityapp.payload.ApiResponse;
import com.example.universityapp.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping("/add")
    public ApiResponse addSubject(@RequestBody Subject subject) {
        subjectRepository.save(subject);
        return new ApiResponse("Saved!", true);
    }

}
