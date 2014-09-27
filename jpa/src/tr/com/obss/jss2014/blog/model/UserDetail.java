package tr.com.obss.jss2014.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserDetail extends BaseModel {

	@Column(nullable=false)
	private String firstName;
	@Column(nullable=false)
	private String lastName;
	private String bio;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getFullName(){
		return firstName+" "+lastName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDetail [firstName=").append(firstName)
				.append(", lastName=").append(lastName).append(", bio=")
				.append(bio).append(", dateOfBirth=").append(dateOfBirth)
				.append(", id=").append(id).append(", version=")
				.append(version).append(", createDate=").append(createDate)
				.append(", updateDate=").append(updateDate).append("]");
		return builder.toString();
	}

}
