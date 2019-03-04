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
@Table(name="skills")

public class Skills {



@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

private long user_id;

private String skill;

private boolean novice;

private boolean intermediate;

private boolean expert;


@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name="user_id",referencedColumnName="id",insertable=false, updatable=false)
private Users user ;


public  String getSkill(){

return skill;

}

public void setSkill(String skill){

skill=skill;

}


public boolean novice(){

return novice;

}

public void setBooleanNov(boolean novice){

novice=novice;

}

public boolean intermediate(){

return intermediate;

}

public void setBooleanInt(boolean intermediate){

intermediate=intermediate;

}


public boolean expert(){

return expert;
}

public void setBooleanEx(boolean intermediate){

intermediate=intermediate;

}

public Users getUser(){

return user;

}

public void setUser(Users user){

user=user;
}
}


