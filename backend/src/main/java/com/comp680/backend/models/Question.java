
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
@Table(name="questions")
public class Question {

  @Id
  @Column(name="question_id")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(name="job_id",nullable=false)
  private long job_id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="job_id",referencedColumnName="job_id",insertable=false, updatable=false)
  private Job job;
  
  @Column(name="question",nullable=true)
  public String question;

  public String getQuestion(){
    return question;
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