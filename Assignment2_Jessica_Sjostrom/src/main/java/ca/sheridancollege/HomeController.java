package ca.sheridancollege;

import java.text.DecimalFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.bean.Vote;
import ca.sheridancollege.bean.Voter;
import ca.sheridancollege.dao.Dao;

@Controller
public class HomeController {
	
	Dao dao = new Dao();
	
	@RequestMapping("/")
	public String goHome(Model model, @ModelAttribute Voter voter) {
		return "Index";
	}
	
	@RequestMapping("/loadDummy")
	public String goVoter(Model model) {
		dao.addDummyRecords();
		model.addAttribute("loadResult", "Dummy voters added");
		return "Index";
	}
	
	@RequestMapping("loadDummyVotes")
	public String goVoterVotes(Model model) {
		dao.addDummyVotes();
		model.addAttribute("loadResult", "Dummy votes added");
		return "Index";
	}
	
	@RequestMapping("/Register") 
		public String goRegister(Model model) {
		model.addAttribute("voter", new Voter());
		return "Register";
	}
	
	@RequestMapping("/addRegister") 
		public String goAddRegister(Model model, @ModelAttribute Voter voter, @RequestParam String sin, @RequestParam String fname, 
				@RequestParam String lname, @RequestParam String birthyear, @RequestParam String birthmon, 
				@RequestParam String birthdate, @RequestParam String address) {
		int sin1 = Integer.parseInt(sin);
		String sin2 = sin1 + "";
		String firstname = fname; 
		String lastname = lname;
		String byear = birthyear;
		String bmonth = birthmon;
		if (bmonth.length() == 1) { 
			bmonth = "0" + bmonth;
		}
		String bdate = birthdate;
		if (bdate.length() == 1) {
			bdate = "0" + bdate;
		}
		String birth = byear + bmonth + bdate;
		int bday = Integer.parseInt(birth);
		String address1 = address;
		Voter v = new Voter(sin1, firstname, lastname, bday, address1, "NO");
		if (!dao.uniqueSIN(sin1)) {
			String s = "This SIN is already registered";
			model.addAttribute("registerResult", s);
			model.addAttribute("registerSin", sin);
		} else if (sin2.length() != 9) {
			String s = "SIN must be 9 digits long";
			model.addAttribute("registerResult", s);
		} else if (!dao.validateAge(birth)) {
			String s = "You must be 18+ years old to vote";
			model.addAttribute("registerResult", s);
		} else {
			String s = "Registration successful";
			model.addAttribute("registerResult", s);
			dao.getVoterList().add(v);
			dao.addVoterObject(v);
		}
		return "Register";
	}
	
	@RequestMapping("/Vote")
	public String goVote(Model model) {
		model.addAttribute("voter", new Voter());
		return "Vote";
	}
	
	@RequestMapping("/checkVoter")
	public String goVote2(Model model, @ModelAttribute Voter voter, @RequestParam String sin) {
		int sin1 = Integer.parseInt(sin);
		if (dao.uniqueSIN(sin1)) {
			String s = "The sin " + sin1 + " has not been registered";
			model.addAttribute("voteResult", s);
		} else if (dao.hasVoted(sin1)) {
			String s = "The SIN " + sin1 + " has already voted";
			model.addAttribute("voteResult", s);
		} else {
			String s = "<a href='AddVote/"+sin1+"'>Go Vote</a>";
			model.addAttribute("voteResult", s);
		}
		return "Vote";
	}
	
	@RequestMapping("/AddVote/{sin}")
	public String addVote(Model model, @ModelAttribute Vote vote, @PathVariable String sin) {
		model.addAttribute("sin", sin);
		model.addAttribute("vote", vote);
		return "AddVote";
	}
	
	@RequestMapping("/addVoteForm/{sin}")
	public String goAddVote2(Model model, @ModelAttribute Vote vote, @PathVariable String sin, @RequestParam String voteChoice) {
		int sin1 = Integer.parseInt(sin);
		String voteParty = voteChoice;	
		dao.addVote(sin1, voteParty);
		String s = "Vote successful";
		model.addAttribute("voteResult", s);
		return "AddVote";
	}

	@RequestMapping("/ViewVoters")
	public String goView(Model model) {
		model.addAttribute("votersRegistered", dao.getVoterList());
		return "ViewVoters";
	}
	
	@RequestMapping("/ViewStats") 
	public String goStats(Model model) {
		DecimalFormat df = new DecimalFormat("###.#");
		double total = dao.getVoteList().size();
		double libs = dao.countL();
		double libsPer = (libs/total) * 100;
		model.addAttribute("libs", df.format(libsPer));
		double cons = dao.countC();
		double consPer = (cons/total) * 100;
		model.addAttribute("cons", df.format(consPer));
		double ndps = dao.countN();
		double ndpsPer = (ndps/total) * 100;
		model.addAttribute("ndps", df.format(ndpsPer));
		double greens = dao.countG();
		double greensPer = (greens/total) * 100;
		model.addAttribute("greens", df.format(greensPer));
		double blocs = dao.countB();
		double blocsPer = (blocs/total) * 100;
		model.addAttribute("blocs", df.format(blocsPer));
		
		double percentVoted = dao.percentVoted();
		model.addAttribute("percentVoted", df.format(percentVoted));
		
		int age18 = dao.getAge18();
		double age18Per = age18/total *100;
		int age30 = dao.getAge30();
		double age30Per = age30/total *100;
		int age45 = dao.getAge45();
		double age45Per = age45/total *100;
		int age60 = dao.getAge60();
		double age60Per = age60/total *100;
		model.addAttribute("yrs18", df.format(age18Per));
		model.addAttribute("yrs30", df.format(age30Per));
		model.addAttribute("yrs45", df.format(age45Per));
		model.addAttribute("yrs60", df.format(age60Per));
		
		return "ViewStats";
	}
	
	@RequestMapping("/Edit/{sin}")
	public String editContact(Model model, @PathVariable int sin) {
		Voter voter = dao.searchVoterBySin(sin);
		model.addAttribute("editSin", sin);
		model.addAttribute("voter", voter);
		return "EditVoter";
	}
	
	@RequestMapping("/EditForm") 
	public String editContact2(Model model, @ModelAttribute Voter voter, @RequestParam int sin) {
		dao.editVoter(voter, sin);
		model.addAttribute("votersRegistered", dao.getVoterList());
		return "ViewVoters";
	}
	
	@RequestMapping("/Delete/{sin}")
	public String deleteVoter(Model model, @PathVariable int sin) {
		Voter v = dao.searchVoterBySin(sin);
		if (v.getSin() != 0) {
			dao.deleteVoterBySin(sin);
		}
		model.addAttribute("votersRegistered", dao.getVoterList());
		return "ViewVoters";
	}
}