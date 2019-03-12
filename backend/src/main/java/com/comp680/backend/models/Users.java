package com.comp680.backend.models;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinTable;


@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Users {

public Users()
{

}



@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

@Column(name="first_name", nullable=true)
private String firstName;

@OneToOne(fetch = FetchType.LAZY,
cascade =  CascadeType.ALL,
mappedBy = "users")
private Social social;




@Column(name="last_name", nullable=true)
private String lastName;


@Column(name="email", nullable=false)
private String email;



@Column(name="address", nullable=true)
private String address;



@Column(name="city", nullable=true)
private String city;


@Column(name="state", nullable=true, length=2)
private String state;



@Column(name="zipcode", nullable=true)
private int zipcode;




@Column(name="phone_number", nullable=true)
private String phone_number;



@Column(name="objective",nullable=true, length=1000)
private String objective;



@Column(name="resume", nullable=true)
private String resume;



@Column(name="profile_image", nullable=true)
private String profile_image;






public Long getId(){

    return id;
    
}



public String getFirstName(){

return firstName;

}


public void setFirstName(String firstName){

this.firstName=firstName;


}

public String getLastName() {

return lastName;

}

public void setLastName( String lastName) {

this.lastName=lastName;

}


public String getEmail() {

return email;

}


public void setEmail(String email) {

this.email=email;

}

public String getAddress() {

return address;

}

public void setAddress(String address) {

this.address=address;

}

public String getCity() {

return city;

}


public void setCity(String city) {

this.city=city;

}


public int getZipCode() {

return zipcode;

}

public void setZipCode(int zipcode) {

this.zipcode=zipcode;

}


public String getState() {


return state;

}


public void setState(String state) {

this.state=state;

}


public String getNumber() {

return phone_number;

}


public void setNumber(String phone_number){

this.phone_number=phone_number;


}


public String getObjective() {

return objective;

}


public void setObjective(String objective) {

this.objective=objective;

}


public String getResume(){

return resume;

}


public void setResume(String resume) {

this.resume=resume;

}


public String getImage() {

return profile_image;

}


public void setImage(String profile_image){

this.profile_image=profile_image;

}

/*
public Social getSocial(){
return social;
}

public void  setSocial(Social social) {
this.social=social;
}

*/

}




