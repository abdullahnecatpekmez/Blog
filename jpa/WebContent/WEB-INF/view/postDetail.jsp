<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog - ${ post.title } </title>
</head>
<body>
  <h1>${ post.title }</h1>
  <h2>Date: ${ post.createDate }</h2>
  <h2>A post by ${ post.author.detail.fullName }</h2>
  <c:if test="${ not empty canEdit && canEdit }">
  	<a href="./addPost?postId=${ post.id }">Edit Post</a><br />
  </c:if>
  <p>${ post.content }</p>
  <hr />
  <a href="./addComment?postId=${ post.id }">Add comment</a>
  <h3>Comments</h3>
  <c:forEach items="${ post.comments }" var="comment">
	<custom:comment comment="${ comment }" post="${ post }"/>
	<hr />
  </c:forEach>

</body>
</html>