package tr.com.obss.jss2014.blog.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tr.com.obss.jss2014.blog.dao.BlogPostDao;
import tr.com.obss.jss2014.blog.dao.CommentDao;
import tr.com.obss.jss2014.blog.model.BlogPost;
import tr.com.obss.jss2014.blog.model.Comment;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/addComment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private BlogPostDao blogPostDao;
	@Inject
	private CommentDao commentDao;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		String postId = request.getParameter("postId");
		String parentId = request.getParameter("parentId");
		if(postId==null&&parentId==null){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Post and comment was unspecified.");
			return;
		}
		request.setAttribute("postId", postId);
		request.setAttribute("parentId", parentId);
		request.getRequestDispatcher("/WEB-INF/view/addComment.jsp").forward(request, response);
	};

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("postId");
		String parentId = request.getParameter("parentId");
		if(postId==null){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "postId is a required parameter.");
			return;
		}
		String content = request.getParameter("comment");
		String email = request.getParameter("email"); //TODO: validation
		String title = request.getParameter("title");
		if(parentId==null){
			BlogPost post = blogPostDao.findById(Long.valueOf(postId));
			if(post==null){
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "the specified post is not found.");
				return;				
			}
			Comment comment = new Comment();
			comment.setComment(content);
			comment.setEmail(email);
			comment.setTitle(title);
			comment.setBlogPost(post);
			commentDao.persist(comment);
			
		}else{
			Comment parentComment = commentDao.findById(Long.valueOf(parentId));
			if(parentComment==null){
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "the specified parent comment is not found.");
				return;	
			}
			Comment comment = new Comment();
			comment.setComment(content);
			comment.setEmail(email);
			comment.setTitle(title);
			comment.setParent(parentComment);
			commentDao.persist(comment);
		}
		response.sendRedirect("/blog/post?id="+postId);
	}
	
	
	
	
	
	
	
	
	
	

}
