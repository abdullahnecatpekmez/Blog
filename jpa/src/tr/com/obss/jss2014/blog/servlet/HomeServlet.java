package tr.com.obss.jss2014.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tr.com.obss.jss2014.blog.dao.BlogPostDao;
import tr.com.obss.jss2014.blog.model.BlogPost;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BlogPostDao blogPostDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BlogPost> latestBlogPosts = blogPostDao.getLatestBlogPosts(10);
		if(!latestBlogPosts.isEmpty()){
			request.setAttribute("recentPosts", latestBlogPosts);
			request.setAttribute("latestPost", latestBlogPosts.get(0));
		}
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}


}
