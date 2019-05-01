package com.comp680.backend.models;

import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Date;

import org.hibernate.annotations.ForeignKey;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinTable;


@Entity
@Table(name="socials")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Social {
    public Social(){}

    public Social(long id, String linkedin, String twitter, 
        String facebook, String github, String website
    ){
        this.id = id;
        this.linkedin=linkedin;
        this.twitter=twitter;
        this.facebook=facebook;
        this.github = github;
        this.website = website;
    }

    @Id
    @Column(name="social_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the user
     */
    @JsonBackReference
    public User getUser() {
        return user;
    }



    @Column(name="linkedin",nullable=true)
    private String linkedin;

    @Column(name="twitter",nullable=true)
    private String twitter;

    @Column(name="facebook",nullable=true)
    private String facebook;

    @Column(name="github",nullable=true)
    private String github;

    @Column(name="website",nullable=true)
    private String website;

    public String getLinkedin(){
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin=linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter=twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook=facebook;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github=github;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website=website;
    }

 
}
