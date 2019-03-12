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
@Table(name="skills")
public class Skill {

    @Id
    @Column(name="skill_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable=false)
    private long user_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",referencedColumnName="user_id",insertable=false, updatable=false)
    private User user;

    @Column(name="skill",nullable=true)
    private String skill;

    @Column(name="novice",nullable=false)
    private boolean novice;

    @Column(name="intermediate",nullable=true)
    private boolean intermediate;

    @Column(name="expert",nullable=true)
    private boolean expert;

    public  String getSkill(){
        return skill;
    }

    public void setSkill(String skill){
        this.skill=skill;
    }

    public boolean getNovice(){
        return novice;
    }

    public void setNovice(boolean novice){
        this.novice=novice;
    }

    public boolean getIntermediate(){
        return intermediate;
    }

    public void setIntermediate(boolean intermediate){
        this.intermediate=intermediate;
    }

    public boolean getExpert(){
        return expert;
    }

    public void setExpert(boolean expert){
        this.expert=expert;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user=user;
    }
}
