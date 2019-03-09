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
@Table(name="experiences")
public class Experience {



@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long educationId;

private long userId;

@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name="userId",referencedColumnName="id",insertable=false, updatable=false)
 private Users user;


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




public String getTitle() {

return title;

}

public void setTitle(String title) {

title=title;

}


public String  getCompany(){

return company;
}


public void setCompany(String company){

company=company;

}


public String getStartDate(){

return startDate;
}


public void setStartDate(String startDate){

startDate=startDate;

}


public String getEndDate(){

return endDate;
}


public void setEndDate(String endDate){
endDate=endDate;
}

public String getDescription(){
return description;
}

public boolean current(){
return current;

}

public Users getUser(){
return user;
}

public void setUser(Users user){
user=user;
}



public void setCurrent(boolean current){

current=current;
}
}