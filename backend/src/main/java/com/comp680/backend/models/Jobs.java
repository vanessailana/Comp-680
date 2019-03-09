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
import javax.persistence.ManyToOne;

@Entity
@Table(name="job")
public class Jobs {

@Id
@Column(name="job_id")
private Long job_id;


@Column(name="status", nullable=false)
private boolean status=true; 

 private Date created;
  private Date updated;

@Column(name="title", nullable=false)
private String title;



@Column(name="description", nullable=false,length=100000)
private String description;



@Column(name="startCompensation", nullable=false)
private double startCompensation;



@Column(name="endCompensation", nullable=true)
private double endCompensation;



@Column(name="employment_type", nullable=false)
private String employment_type;



@Column(name="location", nullable=true)
private String location;

public boolean  Status() {
return status;

}

public void setStatus(boolean status) {

this.status=status;

}



  @PrePersist
  protected void onCreate() {
    created = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updated = new Date();
  }

public String description()  {

return description;

}


public void setDescription(String description) {

this.description=this.description;
return;

}



public double startCompensation()  {

return startCompensation;

}

public double setstartCompensation( double startCompensation) {


return startCompensation;

}


public double endCompensation() {

return endCompensation;


}

public double  setendCompensation(double endCompensation) {

return endCompensation;


}




public String employee() {
return employment_type;
}

public String employee(String employment_type) {

return employment_type;


}


public String location() {
return location;

}

public String setLocation(String location) {
 return location;

}
}





