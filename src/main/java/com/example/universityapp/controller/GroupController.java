package com.example.universityapp.controller;

import com.example.universityapp.entity.Faculty;
import com.example.universityapp.entity.Group;
import com.example.universityapp.payload.ApiResponse;
import com.example.universityapp.repository.GroupRepository;
import com.example.universityapp.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
 @Autowired
 GroupService groupService;

    @PostMapping("/add")
    public ApiResponse addGroup(@RequestBody Group group) {
        groupRepository.save(group);
        return new ApiResponse("Saved!", true);
    }
    @GetMapping("/listall")
    public List<Group> getFaculty(){
        List<Group> all = groupRepository.findAll();
        return all;
    }
    @GetMapping("/one/{id}")
    public ApiResponse getById(@PathVariable int id){
        return groupService.getOne(id);
    }


}
