package tr.com.obss.jss2014.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tr.com.obss.jss2014.blog.bean.DummyContentGenerator;
import tr.com.obss.jss2014.blog.bean.UserBean;
import tr.com.obss.jss2014.blog.dao.BlogPostDao;
import tr.com.obss.jss2014.blog.dao.UserDao;
import tr.com.obss.jss2014.blog.model.BlogPost;
import tr.com.obss.jss2014.blog.model.User;
import tr.com.obss.jss2014.blog.model.UserDetail;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final int BLOG_POST_COUNT = 25;

	private static final String TEST_USERNAME = "yasa";

	private static final String TEST_PASSWORD = "1234";

	private static final long serialVersionUID = 1L;

	@Inject
	private UserDao userDao;
	@Inject
	private BlogPostDao blogPostDao;
	@Inject
	private DummyContentGenerator dummyContentGenerator;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		if (createAndFindUserById()) {
			writer.write("createAndFindUserById passed.\n");
		} else {
			writer.write("createAndFindUserById failed.\n");
			return;
		}
		if (findByUsernameAndPassword()) {
			writer.write("findByUsernameAndPassword passed.\n");
		} else {
			writer.write("findByUsernameAndPassword failed.\n");
			return;
		}
		for (int i = 0; i < BLOG_POST_COUNT; i++) {
			if (createAndFindBlogPostById()) {
				writer.write("createAndFindBlogPostById passed.\n");
			} else {
				writer.write("createAndFindBlogPostById failed.\n");
				return;
			}
		}
	}

	private boolean createAndFindUserById() {
		UserDetail detail = new UserDetail();
		detail.setBio("biography");
		detail.setDateOfBirth(new Date());
		detail.setFirstName("Yasa");
		detail.setLastName("Akbulut");
		User user = new User();
		user.setDetail(detail);
		user.setPassword(TEST_PASSWORD);
		user.setUsername(TEST_USERNAME);
		userDao.persist(user);

		User loadedUser = userDao.findById(user.getId());

		return user.equals(loadedUser);
	}

	private boolean findByUsernameAndPassword() {
		User user = userDao.findByUsernameAndPassword(TEST_USERNAME, TEST_PASSWORD);
		return user != null;
	}
	
	private boolean createAndFindBlogPostById(){
		User user = userDao.findByUsernameAndPassword(TEST_USERNAME, TEST_PASSWORD);
		BlogPost post = new BlogPost();
		post.setAuthor(user);
		post.setContent(dummyContentGenerator.getDummyContent());
		post.setTitle(dummyContentGenerator.getDummyTitle());
		blogPostDao.persist(post);
		
		BlogPost loadedPost = blogPostDao.findById(post.getId());
		return post.equals(loadedPost);
	}
	
	
	
	
	
	
	
	
	
	

}
