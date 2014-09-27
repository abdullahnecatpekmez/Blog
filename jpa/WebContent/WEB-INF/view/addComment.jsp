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
  <h1>Add Comment</h1>
  <form action="./addComment" method="POST">
    <label for="title_input">Title (optional)</label>
    <input type="text" name="title" id="title_input">
    <br>
    <label for="email_input"></label>
    <input type="text" name="email" id="email_input">
    <br>
    <label for="comment_input"></label>
    <textarea name="comment" id="comment_input" cols="30" rows="10"></textarea>
    <br>
    <input type="hidden" name="postId" value="${ postId }">
    <c:if test="${ not empty parentId }">
	    <input type="hidden" name="parentId" value="${ parentId }">
    </c:if>
    <button type="submit">Add comment</button>
  </form>

</body>
</html>