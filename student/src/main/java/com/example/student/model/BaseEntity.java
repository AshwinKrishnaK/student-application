package com.example.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    @JsonIgnore
    private Date createdDate;

    @CreatedBy
    @Column(updatable = false)
    @JsonIgnore
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    @JsonIgnore
    private Date lastUpdatedDate;

    @LastModifiedBy
    @Column(insertable = false)
    @JsonIgnore
    private String lastUpdatedBy;
}
