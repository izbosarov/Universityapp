package com.example.universityapp.service;

import com.example.universityapp.entity.Faculty;
import com.example.universityapp.entity.Group;
import com.example.universityapp.payload.GroupDTO;
import com.example.universityapp.payload.ApiResponse;
import com.example.universityapp.repository.FacultyRepository;
import com.example.universityapp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    GroupRepository groupRepository;

    public ApiResponse addGroup(GroupDTO req) {
        Group group = new Group();
        group.setName(req.getName());
        Optional<Faculty> facultyOptional = facultyRepository.findById(req.getFacultyId());
        if (!facultyOptional.isPresent()) return new ApiResponse("not found!", false);
        group.setFaculty(facultyOptional.get());
        groupRepository.save(group);
        return new ApiResponse("Saved!", true);
    }


    public ApiResponse getOne(int id) {
        Optional<Group> byId = groupRepository.findById(id);
        if (!byId.isPresent())return new ApiResponse("Not found",false);
        return new ApiResponse("Found",true, byId.get());
    }
}
