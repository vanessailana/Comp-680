package com.comp680.backend;

import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
@Table(name="job")
public class Jobs {

@Id
private Long job_id;



private boolean status=true; 

private String title;


private String description;

private double startCompensation;

private double endCompensation;


private String employment_type;



public boolean  Status() {
return status;

}

public void setStatus(boolean status) {

this.status=status;

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

}






