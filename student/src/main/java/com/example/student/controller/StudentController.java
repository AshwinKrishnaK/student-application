package com.example.student.controller;

import com.example.student.exception.UserNotFoundException;
import com.example.student.model.Response;
import com.example.student.model.Student;
import com.example.student.service.StudentService;
import com.example.student.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import static com.example.student.constant.SchoolConstants.SUCCESS;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public ResponseEntity<Response> getAllStudents() {
        return ResponseEntity.ok(new Response(studentService.getAllStudents(), SUCCESS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getStudent(@PathVariable(value = "id", required = false) int id)
            throws UserNotFoundException {
        return ResponseEntity.ok(new Response(studentService.getStudentDetails(id), SUCCESS));
    }

    @PostMapping("/")
    public ResponseEntity<Response> createStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(studentService.createStudent(student), SUCCESS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteStudent(@PathVariable int id) {
        return ResponseEntity.ok(new Response(studentService.deleteStudent(id), SUCCESS));  // Return 200 OK with success message
    }

    @PutMapping("/")
    public ResponseEntity<Response> updateStudent(@RequestBody Student student) throws UserNotFoundException {
        return ResponseEntity.ok(new Response(studentService.updateStudent(student),SUCCESS)); //
    }
}
