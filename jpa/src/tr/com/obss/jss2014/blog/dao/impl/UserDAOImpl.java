package tr.com.obss.jss2014.blog.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import tr.com.obss.jss2014.blog.dao.JpaDAO;
import tr.com.obss.jss2014.blog.dao.UserDao;
import tr.com.obss.jss2014.blog.model.User;

@Stateless
public class UserDAOImpl extends JpaDAO<Long, User> implements UserDao {

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		String queryString = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
		TypedQuery<User> query = em.createQuery(queryString, entityClass);
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
			User user = query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
