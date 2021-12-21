package com.example.universityapp.service;

import com.example.universityapp.entity.Faculty;
import com.example.universityapp.entity.University;
import com.example.universityapp.payload.FacultyDTO;
import com.example.universityapp.payload.ApiResponse;
import com.example.universityapp.repository.FacultyRepository;
import com.example.universityapp.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    public ApiResponse addFaculty(FacultyDTO req) {

        Faculty faculty = new Faculty();
        faculty.setName(req.getName());
        faculty.setCode(String.valueOf(req
                .getName() + "-" + UUID.randomUUID()));

        Optional<University> optionalUniversity = universityRepository.findById(req.getUniversityId());
        if (!optionalUniversity.isPresent()) return new ApiResponse("Unv not found!", false);
        University university = optionalUniversity.get();
        faculty.setUniversity(university);

        facultyRepository.save(faculty);
        return new ApiResponse("Saved!", true);
    }


    public ApiResponse getOne(int id){
        Optional<Faculty> byId = facultyRepository.findById(id);
        if (!byId.isPresent())return new ApiResponse("Not found",false);
        return new ApiResponse("Found",true,byId.get());

    }


}
