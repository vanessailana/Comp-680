package com.comp680.backend;

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
@Table(name="applicants")

public class Applicants {


@Id
@Column(name="id")
private Long applicationid;



@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name="user_id",referencedColumnName="id",insertable=false, updatable=false)
private Users user ;

private long user_id;

@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name="job_id",referencedColumnName="job_id",insertable=false, updatable=false)
private Jobs job;

private long job_id;

private String profile_link;



private Date created;



  @PrePersist
  protected void onCreate() {
    created = new Date();
  }
  
  
  
private Users getUser(){
return user;
}

private void setUsers(){
user=user;
}

private Jobs getJob(){
return job;
}

private void setJobs(){
job=job;
}


private String getProfileLink(){

return profile_link;

}


private void setProfileLink(String profile_link){

profile_link=profile_link;
}

}