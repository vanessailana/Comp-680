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
@Table(name="education")

public class Education {


@Id
@Column(name="ed_id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long ed_id;


@Column(name="school_name", nullable=false)
private String schoolName;


@Column(name="degree", nullable=false)
private String degree;



@Column(name="user_id", nullable=false)
private long user_id;
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "id", nullable = false)
private Users user;


@Column(name="startDate", nullable=false)
private String startDate;



@Column(name="endDate", nullable=false)
private String endDate;




@Column(name="description", nullable=true,length=1000)
private String description;



@Column(name="in_progress", nullable=false)
private boolean in_progress;




public String getSchool() {

return schoolName;

}


public void setSchool(String schoolName){
schoolName=schoolName;

}


public String getDegree() {

return degree;

}


public void setDegree(String degree) {

degree=degree;
}


public String getStart() {

return startDate;

}


public void setStart(String startDate) {

startDate=startDate;
}


public String getEnd() {

return endDate;

}



public void setEnd(String endDate) {

endDate=endDate;

}


public String getDescription(){
return description;

}


public void setDescription(String description) {

description=description;

}

public Users getUser(){
return user;
}

public void setUser(Users user){
user=user;
}


public boolean InProgress() {

return in_progress;
}


public void setInProgress() {

in_progress=in_progress;
}

}


