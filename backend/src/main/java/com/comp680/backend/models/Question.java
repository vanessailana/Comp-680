
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
import java.io.Serializable ;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinTable;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="questions")

public class Question  {

  @Id
  @Column(name="question_id")
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @Column(name="job_id",nullable=false)
  private long job_id;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="job_id",referencedColumnName="job_id",insertable=false, updatable=false)
  private Job job;
  
  @Column(name="question",nullable=true)
  public String question;

  public String getQuestion(){
    return question;
  }

  public Long getJobId(){
  	return job_id;
  }

  public void setJobId(Long job_id) {
  	this.job_id=job_id;
  }

  public void setQuestion(String question){
    this.question=question;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job=job;
  }




}