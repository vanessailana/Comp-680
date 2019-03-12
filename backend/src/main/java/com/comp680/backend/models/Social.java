package com.comp680.backend.models;

import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Social {
    public Social(){}

    public Social(long id, User users, String linkedin, String twitter, 
        String facebook, String github, String website
    ){
        this.id = id;
        this.user=users;
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

    @Column(name="user_id",nullable=false)
    private long user_id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",referencedColumnName="user_id",insertable=false, updatable=false)
    private User user;

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

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
