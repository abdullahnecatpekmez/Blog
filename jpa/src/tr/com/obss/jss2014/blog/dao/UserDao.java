package tr.com.obss.jss2014.blog.dao;

import tr.com.obss.jss2014.blog.model.User;

public interface UserDao extends DAO<Long, User> {
	
	public User findByUsernameAndPassword(String username, String password);

}
