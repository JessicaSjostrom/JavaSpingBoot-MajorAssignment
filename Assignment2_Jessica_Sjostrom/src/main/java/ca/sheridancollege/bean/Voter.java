package ca.sheridancollege.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Voter implements Serializable {

	@Id
	private int sin;
	
	private String fname;
	private String lname;
	private int birthday;
	private String address;
	private String voted;
	
	@Transient
	private int birthyear;
	@Transient
	private int birthmon;
	@Transient
	private int birthdate;

	@OneToOne(cascade=CascadeType.ALL)
	private Vote vote;
	
	public Voter(int sin, String fname, String lname, int birthday, String address, String voted) {
		super();
		this.sin = sin;
		this.fname = fname;
		this.lname = lname;
		this.birthday = birthday;
		this.address = address;
		this.voted = voted;
	}





	
}
