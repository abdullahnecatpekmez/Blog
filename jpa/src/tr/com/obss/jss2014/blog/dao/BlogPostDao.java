package tr.com.obss.jss2014.blog.dao;

import java.util.List;

import tr.com.obss.jss2014.blog.model.BlogPost;

public interface BlogPostDao extends DAO<Long, BlogPost> {
	
	public List<BlogPost> getLatestBlogPosts(int maxNumberOfPosts);
	public BlogPost update(BlogPost old);

}
