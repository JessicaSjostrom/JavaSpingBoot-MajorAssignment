package ca.sheridancollege.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.sheridancollege.bean.Vote;
import ca.sheridancollege.bean.Voter;

public class Dao {

	SessionFactory sessionFactory = new Configuration()
			.configure("ca/sheridancollege/config/hibernate.cfg.xml")
			.buildSessionFactory();
	
	public void addDummyRecords() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// Registered but not yet voted
		for (int i=0; i<200; i++) {
			Random r = new Random();
			int low = 18;
			int high = 75;
			int age = r.nextInt(high-low) + low;
			int age2 = 1925 + age;
			String age3 = Integer.toString(age2) + "0615";
			int age4 = Integer.parseInt(age3);
		Voter v = new Voter(100000000+i, "First"+i, "Last"+i, age4, "" + i + " Trafalgar Rd", "NO");
		session.save(v);
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void addDummyVotes() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<String> partyList = new ArrayList<String>();
		partyList.add("Liberal");
		partyList.add("Conservative");
		partyList.add("NDP");
		partyList.add("Green");
		partyList.add("Bloc");
		
		for (int i = 0; i < 150; i++) {
			Random random = new Random();
			int index = random.nextInt(partyList.size());
			String s = partyList.get(index);
			
			Vote v = new Vote(s);
			addVote(100000000+i, s);
			getVoteList().add(v);			
		}
		
		session.getTransaction().commit();
		session.close();
	}	
	
	public void addVoterObject(Voter v) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(v);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void addVote(int vsin, String voteChoice) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Voter where sin=:voterSin");
		query.setParameter("voterSin", vsin);
		
		Voter v = (Voter)query.getSingleResult();
		
		Vote vote = new Vote(voteChoice);
		
		v.setVote(vote);
		v.setSin(vsin);
		v.setVoted("YES");	

		session.save(v);	
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Voter> getVoterList() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Voter");
		List<Voter> voterList = (List<Voter>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return voterList;
	}
	
	public List<Vote> getVoteList() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Vote");
		List<Vote> voteList = (List<Vote>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return voteList;
	}

	public boolean validateAge(String bday) {
		String s1 = bday.substring(0,4);
		String s2 = bday.substring(4,6);
		String s3 = bday.substring(6);
		
		int i = 2019 - Integer.parseInt(s1) ;
		int i2 = 4 - Integer.parseInt(s2);
		int i3 = 4 - Integer.parseInt(s3);
		
		if (i < 18) {
			return false;
		} else if (i == 18 && i2 < 0){
			return false;
		} else if (i == 18 && i2 <= 0 && i3 < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean uniqueSIN(int vsin) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Voter where sin=:voterSin");
		query.setParameter("voterSin", vsin);
		List<Voter> voterList = (List<Voter>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();	
		
		if (voterList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasVoted(int vsin) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Voter where sin=:voterSin");
		query.setParameter("voterSin", vsin);
		
		Voter v = (Voter)query.getSingleResult();
		
		session.getTransaction().commit();
		session.close();	
		
		if (v.getVoted().equals("YES")) {
			return true;
		} else {
			return false;
		}

	}

	public int countL() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Vote where voteChoice=:choice");
		query.setParameter("choice", "Liberal");
		List<Vote> voteList = (List<Vote>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();	
		
		return voteList.size();
	}
	
	public int countC() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Vote where voteChoice=:choice");
		query.setParameter("choice", "Conservative");
		List<Vote> voteList = (List<Vote>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();	
		
		return voteList.size();
	}

	public int countN() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Vote where voteChoice=:choice");
		query.setParameter("choice", "NDP");
		List<Vote> voteList = (List<Vote>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();	
		
		return voteList.size();
	}
	
	public int countG() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Vote where voteChoice=:choice");
		query.setParameter("choice", "Green");
		List<Vote> voteList = (List<Vote>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();	
		
		return voteList.size();
	}

	public int countB() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Vote where voteChoice=:choice");
		query.setParameter("choice", "Bloc");
		List<Vote> voteList = (List<Vote>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();	
		
		return voteList.size();
	}
	
	public double percentVoted() {
		double a = getVoteList().size();
		double b = getVoterList().size();
		double d = a/b*100;
		return d;
	}
	
	public int getAge18() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Voter where birthday>=19850404 and birthday<=20010404 and voted=:vote");
		query.setParameter("vote", "YES");
		List<Voter> voterList = (List<Voter>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return voterList.size();
	} 
	
	public int getAge30() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Voter where birthday>=19740404 and birthday<=19850405 and voted=:vote");
		query.setParameter("vote", "YES");
		List<Voter> voterList = (List<Voter>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return voterList.size();
	} 
	
	public int getAge45() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Voter where birthday>=19590404 and birthday <=19740405 and voted=:vote");
		query.setParameter("vote", "YES");
		List<Voter> voterList = (List<Voter>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return voterList.size();
	} 
	
	public int getAge60() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Voter where birthday<=19590404 and voted=:vote");
		query.setParameter("vote", "YES");
		List<Voter> voterList = (List<Voter>)query.getResultList();
		
		session.getTransaction().commit();
		session.close();
		
		return voterList.size();
	} 
	
	public Voter searchVoterBySin(int vsin) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Voter where sin=:voterSin");
		query.setParameter("voterSin", vsin);
		
		List<Voter> voterList = (List<Voter>) query.getResultList();
		
		session.getTransaction().commit();
		session.close();
	
		if(voterList.isEmpty()) {
			return null;
		} else {
			return voterList.get(0);
		}
	}
	
	public void editVoter(Voter voter, int sin) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Voter v = (Voter)session.get(Voter.class, sin);
		v.setFname(voter.getFname());
		v.setLname(voter.getLname());
		String byear = voter.getBirthyear() + "";
		String bmonth = voter.getBirthmon() + "";
		if (bmonth.length() == 1) { 
			bmonth = "0" + bmonth;
		}
		String bdate = voter.getBirthdate() + "";
		if (bdate.length() == 1) {
			bdate = "0" + bdate;
		}
		String birth = byear + bmonth + bdate;
		int bday = Integer.parseInt(birth);

		v.setBirthdate(bday);
		v.setAddress(voter.getAddress());
		
		session.getTransaction().commit();
		session.close();
	}

	public void deleteVoterBySin(int sin) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Voter v = (Voter)session.get(Voter.class, sin);
		getVoteList().remove(v);
		getVoterList().remove(v);

		v.setVoted("NO");

		session.delete(v);
		
		session.getTransaction().commit();
		session.close();		
	}
}
