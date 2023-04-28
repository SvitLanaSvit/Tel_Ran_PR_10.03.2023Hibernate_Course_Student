package org.example.services.impl;

import org.example.dto.StudentDTO;
import org.example.services.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public String createStudent(StudentDTO studentDTO) {
        return studentDTO.getId();
    }
}
