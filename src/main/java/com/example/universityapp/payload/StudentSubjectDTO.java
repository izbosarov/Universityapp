package com.example.universityapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubjectDTO {
    private int studentId;
    private List<Integer> subjectIds;
}
