package com.example.student.service;

import com.example.student.exception.UserNotFoundException;
import com.example.student.model.Role;
import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import com.example.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.student.constant.SchoolConstants.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student getStudentDetails(int id) throws UserNotFoundException{
        return studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Student not found in this Id "+id));
    }

    public String deleteStudent(int id){
        studentRepository.deleteById(id);
        return DELETED;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public String createStudent(Student student){
        student.getUser().setRole(Role.STUDENT);
        student.getUser().setPassword(passwordEncoder.encode(student.getUser().getPassword()));
        studentRepository.save(student);
        return SAVED;
    }

    public String updateStudent(Student student) throws UserNotFoundException {
        var existingStudent = studentRepository.findById(student.getStudentId())
                .orElseThrow(()-> new UserNotFoundException("Student with id is not found "+student.getStudentId()));
        existingStudent.getUser().setEmail(student.getUser().getEmail());
        existingStudent.getUser().setName(student.getUser().getName());
        existingStudent.getUser().setPassword(student.getUser().getPassword());
        existingStudent.getUser().setMobileNumber(student.getUser().getMobileNumber());
        studentRepository.save(existingStudent);
        return UPDATED;
    }
}
