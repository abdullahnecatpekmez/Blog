package tr.com.obss.jss2014.blog.bean;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tr.com.obss.jss2014.blog.model.User;
import tr.com.obss.jss2014.blog.model.UserDetail;

@Stateless
public class UserBean {
	
	@PersistenceContext(unitName="pUnit")
	private EntityManager em;
	
	public User createUser(){
		UserDetail detail = new UserDetail();
		detail.setBio("biography");
		detail.setDateOfBirth(new Date());
		detail.setFirstName("Yasa");
		detail.setLastName("Akbulut");
		User user = new User();
		user.setDetail(detail);
		user.setPassword("1234");
		user.setUsername("yasa");
		em.persist(user);
		return user;
	}

}
