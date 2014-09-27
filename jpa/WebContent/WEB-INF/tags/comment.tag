<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="comment" type="tr.com.obss.jss2014.blog.model.Comment" required="true" %>
<%@attribute name="post" type="tr.com.obss.jss2014.blog.model.BlogPost" required="true" %>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="comment" data-id="${ comment.id }">
<p><img alt="${ comment.email }" src="${ comment.avatar }"><span class="author">${ comment.email }</span> wrote on ${ comment.createDate }</p>
<p>${ comment.comment }</p>
<a href="./addComment?postId=${post.id}&parentId=${ comment.id }">Reply to this comment</a>
	<c:if test="${fn:length(comment.children) > 0}">
	<p>Replies:</p>
	<c:forEach items="${ comment.children }" var="child">
		<custom:comment comment="${ child }" post="${ post }"/>
	</c:forEach>
	</c:if>
</div>
