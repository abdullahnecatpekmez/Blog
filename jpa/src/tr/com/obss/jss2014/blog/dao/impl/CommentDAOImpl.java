package tr.com.obss.jss2014.blog.dao.impl;

import javax.ejb.Stateless;

import tr.com.obss.jss2014.blog.dao.CommentDao;
import tr.com.obss.jss2014.blog.dao.JpaDAO;
import tr.com.obss.jss2014.blog.model.BlogPost;
import tr.com.obss.jss2014.blog.model.Comment;

@Stateless
public class CommentDAOImpl extends JpaDAO<Long, Comment> implements CommentDao{

	@Override
	public void persist(Comment comment) {
		super.persist(comment);
		if(comment.getBlogPost()!=null){
			BlogPost post = em.find(BlogPost.class, comment.getBlogPost().getId());
			em.refresh(post);			
		}else{
			Comment parent = findById(comment.getParent().getId());
			em.refresh(parent);
		}
	}
}
