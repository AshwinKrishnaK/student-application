package com.example.student.service;

import com.example.student.exception.UserNotFoundException;
import com.example.student.model.Address;
import com.example.student.model.Role;
import com.example.student.model.Teacher;
import com.example.student.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.student.constant.SchoolConstants.*;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public String updateTeacherAddress(int id, Address address) throws UserNotFoundException{
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Teacher with id is not found "+id));
        Address teacherAddress = existingTeacher.getAddress();
        teacherAddress.setAddress1(address.getAddress1());
        teacherAddress.setAddress2(address.getAddress2());
        teacherAddress.setCity(address.getCity());
        teacherAddress.setState(address.getState());
        teacherAddress.setZipCode(address.getZipCode());
        existingTeacher.setAddress(teacherAddress);
        teacherRepository.save(existingTeacher);
        return UPDATED;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public String createTeacher(Teacher teacher){
        teacher.getUser().setRole(Role.TEACHER);
        teacherRepository.save(teacher);
        return SAVED;
    }

    public String updateTeacher(Teacher teacher) throws UserNotFoundException{
        Teacher existingTeacher = teacherRepository.findById(teacher.getTeacherId())
                .orElseThrow(()-> new UserNotFoundException("Teacher with id is not found "+teacher.getTeacherId()));
        existingTeacher.getUser().setEmail(teacher.getUser().getEmail());
        existingTeacher.getUser().setName(teacher.getUser().getName());
        existingTeacher.getUser().setPassword(teacher.getUser().getPassword());
        existingTeacher.getUser().setMobileNumber(teacher.getUser().getMobileNumber());
        teacherRepository.save(existingTeacher);
        return UPDATED;
    }

    public String deleteTeacher(int id){
        teacherRepository.deleteById(id);
        return DELETED;
    }

}
