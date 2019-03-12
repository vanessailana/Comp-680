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
@Table(name="education")

public class Education {


@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;


@Column(name="school_name")
private String schoolName;

@Column(name="major")
private String major;


@Column(name="degree")
private String degree;




@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "users_id", nullable = false)
private Users users;


@Column(name="start_date")
private String startDate;



@Column(name="end_date")
private String endDate;




@Column(name="description", nullable=true,length=1000)
private String description;



@Column(name="in_progress")
private boolean in_progress;

public void setMajor(String major)
{
    this.major = major;
}

public String getMajor()
{
    return this.major;
}

public long getId()
{
    return this.id;
}

public String getSchool() {

return schoolName;

}


public void setSchool(String schoolName){
this.schoolName=schoolName;

}


public String getDegree() {

return degree;

}


public void setDegree(String degree) {

this.degree=degree;
}


public String getStart() {

return startDate;

}


public void setStart(String startDate) {

this.startDate=startDate;
}


public String getEnd() {

return endDate;

}



public void setEnd(String endDate) {

this.endDate=endDate;

}


public String getDescription(){
return description;

}


public void setDescription(String description) {

this.description=description;

}

public Users getUsers(){
return users;
}

public void setUsers(Users users){
this.users=users;
}


public boolean getInProgress() {

return in_progress;
}


public void setInProgress(boolean in_progress) {
    this.in_progress=in_progress;
}

}


