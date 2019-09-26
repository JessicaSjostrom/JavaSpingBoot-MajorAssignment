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
public class Vote implements Serializable {

	@Id
	@GeneratedValue
	private int voteId;
	private String voteChoice;
	
	@Transient
	private String[] parties = {"Liberal","Conservative","NDP","Green","Bloc"};
	
	@Transient
	private int sin;

	@OneToOne(cascade=CascadeType.ALL, targetEntity=Voter.class, fetch=FetchType.EAGER)
	private Voter voter;
	
	public Vote(String voteChoice) {
		super();
		this.voteChoice = voteChoice;
	}
	
}
