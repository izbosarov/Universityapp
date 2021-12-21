package com.example.universityapp.controller;

import com.example.universityapp.entity.Faculty;
import com.example.universityapp.payload.FacultyDTO;
import com.example.universityapp.payload.ApiResponse;
import com.example.universityapp.repository.FacultyRepository;
import com.example.universityapp.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyService;
    @Autowired
    FacultyRepository facultyRepository;

    @PostMapping("/add")
    public ApiResponse addFaculty(@RequestBody FacultyDTO req) {
        return facultyService.addFaculty(req);
    }

    @GetMapping("/list")
    public List<Faculty> getFaculty(){
        List<Faculty> all = facultyRepository.findAll();
        return all;
    }
    @GetMapping("/one/{id}")
    public ApiResponse getById(@PathVariable int id){
        return facultyService.getOne(id);
    }

}
