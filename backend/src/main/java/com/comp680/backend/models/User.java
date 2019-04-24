package com.comp680.backend.models;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinTable;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

 
  
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, 
    orphanRemoval = true, mappedBy = "users")
    private List<Skill> skills;
    
    /*
    @OneToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL,mappedBy = "users")
    private Social social;
    */

    @Column(name="first_name", nullable=true)
    private String first_name;

    @Column(name="last_name", nullable=true)
    private String last_name;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="address", nullable=true)
    private String address;

    @Column(name="city", nullable=true)
    private String city;

    @Column(name="state", nullable=true, length=2)
    private String state;

    @Column(name="zip_code", nullable=true)
    private int zip_code;

    @Column(name="phone_number", nullable=true)
    private String phone_number;

    @Column(name="objective",nullable=true, length=1000)
    private String objective;

    @Column(name="resume", nullable=true)
    private String resume;

    @Column(name="profile_image", nullable=true)
    private String profile_image;

    public Long getId(){
        return id;
    }

    public String getFirstName(){
        return first_name;
    }

    public void setFirstName(String first_name){
        this.first_name=first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName( String last_name) {
        this.last_name=last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city=city;
    }

    public int getZipCode() {
        return zip_code;
    }

    public void setZipCode(int zip_code) {
        this.zip_code=zip_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state=state;
    }

    public String getNumber() {
        return phone_number;
    }

    public void setNumber(String phone_number){
        this.phone_number=phone_number;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective=objective;
    }

    public String getResume(){
        return resume;
    }

    public void setResume(String resume) {
        this.resume=resume;
    }

    public String getImage() {
        return profile_image;
    }

    public void setImage(String profile_image){
        this.profile_image=profile_image;
    }

    
  

    public List<Skill> getSkill(){
        return skills;
    }

    public void  setSkills(List<Skill> skills) {
        this.skills=skills;
    }
  
}
