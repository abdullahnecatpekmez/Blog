package tr.com.obss.jss2014.blog.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tr.com.obss.jss2014.blog.dao.UserDao;
import tr.com.obss.jss2014.blog.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns={"/login", "/logout"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private UserDao userDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getServletPath().equals("/logout")){
			session.invalidate();
			response.sendRedirect("/blog/login");
			return;
		}
		if(session.getAttribute("user")!=null){
			response.sendRedirect("/blog/home");
			return;
		}
		if(session.getAttribute("login_failed")!=null){
			request.setAttribute("loginFailed", true);
			session.removeAttribute("login_failed");
		}
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.findByUsernameAndPassword(username, password);
		HttpSession session = request.getSession();
		if(user!=null){
			session.setAttribute("user", user);
			response.sendRedirect("/blog/home");
		}else{
			session.setAttribute("login_failed", true);
			response.sendRedirect("/blog/login");
		}
	}

}
