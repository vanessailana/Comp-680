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
import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="questions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question  {


  @Id
  @Column(name="question_id")
  @GeneratedValue
  private Long id;



  @ManyToOne(fetch = FetchType.LAZY,optional = false)
  @JoinColumn(name="job_id",nullable=false)
  private Job job;

  private Question()
  {

  }
  public Question(Long id,String question, Job job)
  {
    this.id = id;
    this.question = question;
    this.job = job;
  }
  
  @Column(name="question",nullable=true)
  public String question;

  public String getQuestion(){
    return question;
  }

  public Long getId(){
 	return id;
  }

  public  void setId(Long id){
 	this.id=id;
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