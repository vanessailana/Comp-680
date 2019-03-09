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
@Table(name="projects")

public class Projects {



@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

private long user_id;

@Column(name="project_name" ,nullable=false)
private String projectName;


@Column(name="description" ,nullable=false, length=1000)
private String description;




@Column(name="link" ,nullable=false)
private String link;




@Column(name="startDate", nullable=true)
private String startDate;




@Column(name="endDate", nullable=true)
private String endDate;


@Column(name="technologies" ,nullable=false)
private String technologies;



@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name="user_id",referencedColumnName="id",insertable=false, updatable=false)
private Users user ;



public String getProjName(){

return projectName;

}

public void setProjName(String projectName){
projectName=projectName;
}


public String getDescription(){

return description;

}

public void setDescription(String description){
description=description;
}


public String getTechnologies(){

return technologies;

}

public void setTechnologies(String technologies){
technologies=technologies;
}


public String getLink(){

return link;

}

public void setLinks(String link){
link=link;
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

public Users getUser(){
return user;
}

public void setUsers(){
user=user;
}
}
