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
  <h1>Add new blog post</h1>
  <div>
    <form action="./addPost" method='POST'>
      <label for="title_input">Post Title:</label>
      <input type="text" name="title" id="title_input" value="${ oldTitle }">
      <br>
      <label for="content_input">Content:</label>
      <textarea name="content" id="content_input" cols="30" rows="10">${ oldContent }</textarea>
      <br>
      <c:if test="${ not empty oldId }">
      <input type="hidden" name="postId" value="${ oldId }">
      </c:if>
      <button type="submit">Add Blog Post</button>
    </form>
  </div>
</body>
</html>