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
@Table(name="experiences")
public class Experience {

    @Id
    @Column(name="experience_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;



    @Column(name="title",nullable=false)
    private String title;

    @Column(name="company",nullable=false)
    private String company;

    @Column(name="start_date",nullable=false)
    private String start_date;

    @Column(name="end_date",nullable=false)
    private String end_date;

    @Column(name="description",nullable=false,length=1000)
    private String description;

    @Column(name="current",nullable=false)
    private boolean current=true;


    public long getId()
    {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany(){
        return company;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getStartDate(){
        return start_date;
    }

    public void setStartDate(String start_date){
        this.start_date = start_date;
    }

    public String getEndDate(){
        return end_date;
    }

    public void setEndDate(String end_date){
        this.end_date = end_date;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean getCurrent(){
        return current;
    }

    public void setCurrent(boolean current){
        this.current = current;
    }


}