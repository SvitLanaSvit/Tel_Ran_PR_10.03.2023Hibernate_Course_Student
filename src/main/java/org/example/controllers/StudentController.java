package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.StudentDTO;
import org.example.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public String student(){
        return "Hello world!";
    }

    @PostMapping("/createStudent")
    @ResponseStatus(HttpStatus.OK)
    public String createStudent(@RequestBody StudentDTO newStudent){
        log.info("Student: " + newStudent);
        return studentService.createStudent(newStudent);
    }
}
