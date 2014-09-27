<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog - Login</title>
</head>
<body>
<c:if test="${ not empty loginFailed }">
	<div class="error">
		Login failed. Check your credentials and try again.
	</div>
</c:if>
  <div>
    <form action="./login" method="POST">
      <label for="username_input">Username:</label>
      <input type="text" name="username" id="username_input">
      <br>
      <label for="password_input">Password:</label>
      <input type="password" name="password" id="password_input">
      <br>
      <button type="submit">Login</button>
    </form>
  </div>
</body>
</html>