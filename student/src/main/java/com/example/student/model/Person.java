package com.example.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Person extends BaseEntity {

    private int personId;

     private String name;

    private String mobileNumber;

    private String email;

    private String confirmEmail;

    private String pwd;

    private String confirmPwd;
}
