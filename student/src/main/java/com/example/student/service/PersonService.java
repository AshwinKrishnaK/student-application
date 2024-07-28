package com.example.student.service;

import com.example.student.exception.PersonNotFoundException;
import com.example.student.model.Person;
import com.example.student.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.student.constant.SchoolConstants.SAVED;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllStudents(){
        return personRepository.findAll();
    }

    public Person getStudentById(int id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Student is not found with this id " + id));
    }

    public String saveStudent(Person person){
        personRepository.save(person);
        return SAVED;
    }
}
