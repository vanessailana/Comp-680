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

public class Questions {

@Id
@Column(name="question_id")
private Long question_id;



  private long job_id;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Jobs job;
    
    @Column(name="question",nullable=true)
    public String question;
    
    
public String getQuestion(){

return question;
}


public Long getId() {
return job_id;
}

public Long getQuestionId() {
	return question_id;
}

public void setQuestionId(Long question_id) {
	this.question_id=question_id;
}

public void setId() {
this.job_id=job_id;
}


public Jobs getJob() {

return job;

}

public void setJob(Jobs job) {

this.job=job;


}


public void setQuestion(String question){

this.question=question;


}
 


}