package com.comp680.backend.models;



import javax.annotation.Generated;
import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="jobs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Job implements Serializable  {

  @Id
  @Column(name="job_id",unique=true, nullable=false)
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;


  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;


  
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "job")
  private List<Applicant> applicant;
  

  
  @Column(name="status",nullable=false,columnDefinition = "boolean default 1")
  private boolean status = true;

  @Column(name="title",nullable=false)
  private String title;

  @Column(name="description",nullable=true)
  public String description;

  @Column(name="start_compensation",nullable=false)
  private double start_compensation;


  @Column(name="employment_type",nullable=true)
  public String employment_type;

  @Column(name="location",nullable=false)
  private String location;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private Date created_at;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at")
  private Date updated_at;

  public Long getId() {
    return id;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getDescription()  {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setId(Long id) {
    this.id=id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getEmploymentType() {
    return employment_type;
  }

  public void setEmploymentType(String employment_type) {
    this.employment_type = employment_type;
  }
  
  public String getLocation() {
    return location;
  }

  public void  setLocation(String location) {
    this.location = location;
  }

  public double getStartCompensation() {
    return start_compensation;
  }

  public void setStartCompensation(Double  start_compensation) {
    this.start_compensation = start_compensation;
  }


  public User getUser()
  {
    return this.user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }


  public List<Applicant> getApplicant()
  {
    return this.applicant;
  }

  public void setApplicant( List<Applicant> applicant)
  {
    this.applicant=applicant;
  }
   


}
