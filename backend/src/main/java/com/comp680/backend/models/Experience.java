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
@Table(name="experiences")
public class Experience {



@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;



@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "users_id", nullable = false)
private Users users;


@Column(name="title",nullable=false)
private String title;



@Column(name="company",nullable=false)
private String company;


@Column(name="startDate",nullable=false)
private String startDate;



@Column(name="endDate",nullable=false)
private String endDate;



@Column(name="description",nullable=false,length=1000)
private String description;


@Column(name="current",nullable=false)
private boolean current=true;




public boolean getCurrent()
{
    return current;
}
public long getId()
{
    return this.id;
}

public String getTitle() {

return title;

}

public void setTitle(String title) {

this.title=title;

}


public String  getCompany(){

return company;
}


public void setCompany(String company){

this.company=company;

}


public String getStartDate(){

return startDate;
}


public void setStartDate(String startDate){

this.startDate=startDate;

}


public String getEndDate(){

return endDate;
}


public void setEndDate(String endDate){
this.endDate=endDate;
}

public void setDescription(String description)
{
    this.description = description;
}
public String getDescription(){
return description;
}

public boolean current(){
return current;

}

public Users getUsers(){
return users;
}

public void setUsers(Users users){
this.users=users;
}



public void setCurrent(boolean current){

this.current=current;
}

}