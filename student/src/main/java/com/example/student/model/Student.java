package com.example.student.model;

import jakarta.persistence.*;

@Entity
public class Student extends User {

    private int admissionNo;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private SchoolClass schoolClass;
}
