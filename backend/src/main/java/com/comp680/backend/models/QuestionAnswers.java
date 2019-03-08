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
@Table(name="question_answers")

public class QuestionAnswers {


@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

private long user_id;

@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name="user_id",referencedColumnName="id",insertable=false, updatable=false)
private Users user;

@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name="question_id",referencedColumnName="question_id",insertable=false, updatable=false)
private Questions question ;


private long question_id;


@Column(name="answer", nullable=false)
private String answer;


public String getAnswer(){

return answer;

}


public void setAnswer(String answer){

answer=answer;
}

public Questions getQuestion(){
return question;
}

public void setQuestion(Questions question){

question=question;

}
public Users getUser(){
return user;
}

public void setUser(Users user){

user=user;
}
}