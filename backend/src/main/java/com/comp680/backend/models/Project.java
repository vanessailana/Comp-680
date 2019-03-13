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
@Table(name="projects")
public class Project {

    @Id
    @Column(name="project_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="project_name" ,nullable=false)
    private String project_name;

    @Column(name="description" ,nullable=true, length=1000)
    private String description;

    @Column(name="link" ,nullable=true)
    private String link;

    @Column(name="start_date", nullable=true)
    private String start_date;

    @Column(name="end_date", nullable=true)
    private String end_date;

    @Column(name="technologies" ,nullable=true)
    private String technologies;

    public long getId()
    {
        return this.id;
    }

    public String getProjectName(){
        return project_name;
    }

    public void setProjectName(String project_name){
        this.project_name=project_name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getTechnologies(){
        return technologies;
    }

    public void setTechnologies(String technologies){
        this.technologies=technologies;
    }

    public String getLink(){
        return link;
    }

    public void setLinks(String link){
        this.link=link;
    }

    public String getStartDate(){
        return start_date;
    }

    public void setStartDate(String start_date){
        this.start_date=start_date;
    }

    public String getEndDate(){
        return end_date;
    }

    public void setEndDate(String end_date){
        this.end_date=end_date;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user=user;
    }
}
