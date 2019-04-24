package com.comp680.backend.models;

import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinTable;

@Entity
@Table(name="applicants")
public class Applicant {

  @Id
  @Column(name="applicant_id")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;



  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;



  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "job_id", nullable = false)
  @JsonBackReference
  private Job job;

  @Column(name="profile_link", nullable=true)
  private String profile_link;

  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="created_at", nullable=true)
  private Date created_at;


  @PrePersist
  protected void onCreated() {
    created_at = new Date();
  }

  public Date getCreatedAt(){
      return this.created_at;
  }

  public User getUser(){
    return user;
  }

  public void setUser(){
    this.user = user;
  }


  
  public Job getJob(){
    return job;
  }

  public void setJob(){
    this.job = job;
  }

  public String getProfileLink(){
    return profile_link;
  }

  public void setProfileLink(String profile_link){
    this.profile_link = profile_link;
  }
}