package com.comp680.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.annotation.Generated;
import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="jobs")
public class Jobs {

@Id
@Column(name="job_id",unique = true, nullable = false)
@GeneratedValue(strategy=GenerationType.AUTO)
private Long job_id;


@Column(name="status",nullable=false,columnDefinition = "boolean default 1")
private boolean status=true; 


@Column(name="title",nullable=false)
private String title;



@Column(name="description",nullable=true)
public String description;



@Column(name="startCompensation",nullable=false)
private double start_compensation;




@Column(name="endCompensation",nullable=true)
private double end_compensation;



@Column(name="employmentType",nullable=false)
public String employment_type;



@Column(name="location",nullable=false)
private String location;

@CreationTimestamp
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_date")
private Date createDate;

@UpdateTimestamp
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "modify_date")
private Date modifyDate;


public boolean  getStatus() {
return status;

}


public void setStatus(boolean status) {

this.status=status;

}


public long getId(){
  return job_id;
}

public void setId() {
  this.job_id=job_id;
}





public String getDescription()  {

return description;

}


public void setDescription(String description) {

this.description=description;


}



public String getTitle() {
  return title;
}
public void setTitle(String title) {
  this.title=title;
}


public String getEmploymentType() {
return employment_type;

}

public void  setEmploymentType(String employment_type) {
this.employment_type=employment_type;

}
public String getLocation() {
return location;

}

public void  setLocation(String location) {
this.location=location;

}

public  double getCompensationStart() {
return start_compensation;

}

public void  setCompensationStart  (Double  start_compensation) {
this.start_compensation=start_compensation;

}
public  double getCompensationEnd() {
return end_compensation;

}

public void  setCompensationEnd (Double  end_compensation) {
this.end_compensation=end_compensation;

}
}





