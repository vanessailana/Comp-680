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
@Table(name="social")

public class Social {


@Id
@Column(name="social_id")
private Long id;
@OneToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "id", nullable = false)
private Users user;
 


private String linkedin;

private String twitter;

private String facebook;

private String github;

private String website;


public String getLinkedin(){

return linkiden;

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


public void setFacebook(String facebook) {

facebook=facebook;

}

public String getGithub() {

return github;

}


public void setGithub(String github) {

github=github;

}


public String getWebsite() {

return website;

}


public void setWebsite(String website) {

website;

}


public Users getUser(){

return user;


}


public void setUser(Users user) {

user=user;

}


}
