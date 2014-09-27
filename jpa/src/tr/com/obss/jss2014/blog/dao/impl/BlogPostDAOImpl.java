package tr.com.obss.jss2014.blog.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import tr.com.obss.jss2014.blog.dao.BlogPostDao;
import tr.com.obss.jss2014.blog.dao.JpaDAO;
import tr.com.obss.jss2014.blog.model.BlogPost;

@Stateless
public class BlogPostDAOImpl extends JpaDAO<Long, BlogPost> implements BlogPostDao{

	@Override
	public List<BlogPost> getLatestBlogPosts(int maxNumberOfPosts) {
		TypedQuery<BlogPost> query = em.createNamedQuery("BlogPost.getLatest", entityClass);
		query.setMaxResults(maxNumberOfPosts);
		List<BlogPost> resultList = query.getResultList();
		return resultList;
	}
	
	@Override
	public BlogPost update(BlogPost old){
		BlogPost updated = em.merge(old);
		return updated;
	}

}
