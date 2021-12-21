package com.example.universityapp.controller;

import com.example.universityapp.entity.University;
import com.example.universityapp.payload.ApiResponse;
import com.example.universityapp.payload.UniversityDTO;
import com.example.universityapp.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @PostMapping("/add")
    public ApiResponse addUniversity(@RequestBody UniversityDTO dto) {
        return universityService.addUniver(dto);
    }

    @GetMapping("/list")
    public List<University> getList() {
        return universityService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable int id) {
        return universityService.getOneById(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse updateUniver(@PathVariable int id, @RequestBody UniversityDTO dto) {
        return universityService.edit(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteUniver(@PathVariable int id) {
        return universityService.deleteUniver(id);
    }

}
