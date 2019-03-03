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
@Table(name="users")

public class Users {



@Id
@Column(name="id")
private Long id;


@Column(name="first_name", nullable=false)
private String firstName;


@Column(name="last_name", nullable=false)
private String lastName;


@Column(name="email", nullable=false)
private String email;



@Column(name="address", nullable=false)
private String address;



@Column(name="city", nullable=false)
private String city;


@Column(name="state", nullable=false, length=2)
private String state;



@Column(name="zipcode", nullable=false)
private int zipcode;




@Column(name="phone_number", nullable=false)
private String phone_number;



@Column(name="objective", nullable=false, length=1000)
private String objective;



@Column(name="resume", nullable=false)
private String resume;



@Column(name="profile_image", nullable=true)
private String profile_image;





@Column(name="linkedin", nullable=true)
private String  linkedin;



@Column(name="twitter", nullable=true)
private String  twitter;



@Column(name="facebook", nullable=true)
private String  facebook;




@Column(name="github", nullable=true)
private String  github;




@Column(name="personal_website", nullable=true)
private String  personal_website;





public String getFirstName(){

return firstName;

}


public void setFirstName(String firstName){

firstName=firstName;


}

public String getLastName() {

return lastName;

}

public void setLastName( String lastName) {

lastName=lastName;

}


public String getEmail() {

return email;

}


public void setEmail(String email) {

email=email;

}

public String getAddress() {

return address;

}

public void setAddress(String address) {

address=address;

}

public String getCity() {

return city;

}


public void setCity(String city) {

city=city;

}


public int getZipCode() {

return zipcode;

}

public void setZipCode(int zipcode) {

zipcode=zipcode;

}


public String getState() {


return state;

}


public void setState(String state) {

state=state;

}


public String getNumber() {

return phone_number;

}


public void setNumber(String phone_number){

phone_number=phone_number;


}


public String getObjective() {

return objective;

}


public void setObjective(String objective) {

objective=objective;

}


public String getResume(){

return resume;

}


public void setResume(String resume) {

resume=resume;

}


public String getImage() {

return profile_image;

}


public void setImage(String profile_image){

profile_image=profile_image;

}


public String getLinkedin(){

return linkedin;
}

public void setLinkedin(String linkedin) {

linkedin=linkedin;
}


public String getTwitter() {

return twitter;

}

public void setTwitter(String twitter) {

twitter=twitter;

}


public String getFacebook() {

return facebook;

}


public void setFacebook(String facebook){

facebook=facebook;


}


public String getGithub() {

return github;

}


public void setGithub(String github){

github=github;


}


public String getWebsite() {

return personal_website;

}


public void setWebsite(String personal_website){

personal_website=personal_website;


}

}


