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
@Table(name="answers")
public class Answer {


    public Answer()
    {

    }

    public Answer(String answer, Applicant applicant, Question question)
    {
        this.answer = answer;
        this.applicant = applicant;
        this.question = question;
    }

    @Id
    @Column(name="answer_id")
    @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name="applicant_id", nullable=false)
    private Applicant applicant;

    @OneToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    @Column(name="answer", nullable=false)
    private String answer;

    @Column(name="created_at", nullable=false)
    private Date created_at;

    @PrePersist
    protected void onCreated() {
      created_at = new Date();
    }

    public Date getCreatedAt(){
        return this.created_at;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String answer){
        this.answer=answer;
    }

    public Question getQuestion(){
        return question;
    }

    public void setQuestion(Question question){
        this.question=question;
    }


    public Applicant getApplicant()
    {
        return this.applicant;
    }
    public void setApplicant(Applicant applicant)
    {
        this.applicant = applicant;
    }



}