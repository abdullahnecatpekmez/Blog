package tr.com.obss.jss2014.blog.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tr.com.obss.jss2014.blog.dao.BlogPostDao;
import tr.com.obss.jss2014.blog.model.BlogPost;

/**
 * Servlet implementation class BlogDetailServlet
 */
@WebServlet("/post")
public class BlogDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BlogPostDao blogPostDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String postId = request.getParameter("id");
		if(postId==null){
			response.sendRedirect("/blog/home");
			return;
		}
		BlogPost post = blogPostDao.findById(Long.valueOf(postId));
		if(post==null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "The specified post does not exist.");
			return;
		}
		if(post.getAuthor().equals(session.getAttribute("user"))){
			request.setAttribute("canEdit", true);		
		}
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/view/postDetail.jsp").forward(request, response);
	}

	
	
	
	
	
	
	
	

}
