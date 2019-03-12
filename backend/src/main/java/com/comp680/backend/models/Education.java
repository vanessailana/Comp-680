package com.comp680.backend.models;

import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinTable;

@Entity
@Table(name="educations")
public class Education {

    @Id
    @Column(name="education_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable=false)
    private long user_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName="user_id",insertable=false, updatable=false)
    private User user;

    @Column(name="school_name", nullable=false)
    private String school_name;

    @Column(name="major", nullable=false)
    private String major;

    @Column(name="degree", nullable=false)
    private String degree;

    @Column(name="description", nullable=true,length=1000)
    private String description;

    @Column(name="start_date", nullable=false)
    private String start_date;

    @Column(name="end_date", nullable=false)
    private String end_date;

    @Column(name="in_progress", nullable=false)
    private boolean in_progress;

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getSchoolName() {
        return school_name;
    }

    public void setSchoolName(String school_name){
        this.school_name = school_name;
    }
    
    public String getMajor() {
        return school_name;
    }

    public void setMajor(String major){
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStartDate() {
        return start_date;
    }

    public void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    public String getEndDate() {
        return end_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    public boolean getInProgress() {
        return in_progress;
    }

    public void setInProgress(boolean in_progress) {
        this.in_progress = in_progress;
    }
}