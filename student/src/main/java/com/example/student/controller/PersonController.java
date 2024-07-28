package com.example.student.controller;

import com.example.student.model.Person;
import com.example.student.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService studentService;

    public ResponseEntity<List<Person>> getAllPerson(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}
