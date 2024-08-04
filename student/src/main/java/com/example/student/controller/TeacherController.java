package com.example.student.controller;

import com.example.student.exception.UserNotFoundException;
import com.example.student.model.Address;
import com.example.student.model.Response;
import com.example.student.model.Teacher;
import com.example.student.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.student.constant.SchoolConstants.SUCCESS;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/")
    public ResponseEntity<Response> getAllTeachers(@RequestParam(value = "subject", required = false) String subject) {
        return ResponseEntity.ok(new Response(teacherService.getAllTeachers(), SUCCESS));
    }

    @PostMapping("/")
    public ResponseEntity<Response> createTeacher(@RequestBody Teacher teacher) throws UserNotFoundException {
        return ResponseEntity.ok(new Response(teacherService.createTeacher(teacher), SUCCESS));
    }

    @DeleteMapping("/")
    public ResponseEntity<Response> deleteTeacher(@PathVariable("id") int id) throws UserNotFoundException {
        return ResponseEntity.ok(new Response(teacherService.deleteTeacher(id), SUCCESS));
    }

    @PutMapping("/")
    public ResponseEntity<Response> updateTeacher(@RequestBody Teacher teacher) throws UserNotFoundException {
        return ResponseEntity.ok(new Response(teacherService.updateTeacher(teacher), SUCCESS));
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<Response> updateTeacherAddress(@PathVariable("id") int id, @RequestBody Address address) throws UserNotFoundException {
        return ResponseEntity.ok(new Response(teacherService.updateTeacherAddress(id, address), SUCCESS));
    }

}
