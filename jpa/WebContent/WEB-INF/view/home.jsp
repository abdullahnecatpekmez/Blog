<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>JSP MVC DAO JPA EJB BLOG</h1>
  <c:if test="${ not empty latestPost}">
    <div class="latest-post">
      <h2>${ latestPost.title }</h2>
      <h3>a post by ${ latestPost.author.detail.fullName }</h3>
      <p>${ latestPost.content }</p>
    </div>
  </c:if>
  <c:if test="${ not empty recentPosts }">
    <h3>Recent Posts</h3>
    <ul>
      <c:forEach items="${ recentPosts }" var="post">
        <li>
          <a href="./post?id=${ post.id }">${ post.title }</a>
        </li>
      </c:forEach>
    </ul>
  </c:if>

</body>
</html>