package tr.com.obss.jss2014.blog.servlet;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tr.com.obss.jss2014.blog.dao.BlogPostDao;
import tr.com.obss.jss2014.blog.model.BlogPost;
import tr.com.obss.jss2014.blog.model.User;

/**
 * Servlet implementation class BlogPostServlet
 */
@WebServlet("/addPost")
public class BlogPostManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	private BlogPostDao blogPostDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			response.sendRedirect("/blog/login");
			return;
		}
		String postId = request.getParameter("postId");
		if(postId!=null){
			BlogPost oldPost = blogPostDao.findById(Long.valueOf(postId));
			request.setAttribute("oldTitle", oldPost.getTitle());
			request.setAttribute("oldContent", oldPost.getContent());
			request.setAttribute("oldId", oldPost.getId());
		}
		request.getRequestDispatcher("/WEB-INF/view/addPost.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			response.sendRedirect("/blog/login");
			return;
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BlogPost blogPost = new BlogPost();
		blogPost.setAuthor((User)session.getAttribute("user"));
		blogPost.setContent(content);
		blogPost.setTitle(title);
		String postId = request.getParameter("postId");
		if(postId==null){
			blogPostDao.persist(blogPost);			
		}else{
			BlogPost oldPost = blogPostDao.findById(Long.valueOf(postId));
			oldPost.setContent(content);
			oldPost.setTitle(title);
			blogPostDao.update(oldPost);
			response.sendRedirect("/blog/post?id="+postId);
			return;
		}
		response.sendRedirect("/blog/home");
	}

}










