package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.exception.StudentNotFoundException;

import java.util.List;

import static com.example.student.constant.StudentConstant.SAVED;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) throws StudentNotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student is not found with this id " + id));
    }

    public String saveStudent(Student student){
        studentRepository.save(student);
        return SAVED;
    }
}
