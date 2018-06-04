<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${successes.size() > 0 }">
	<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong>完了しました!</strong>
		<ul>
			<c:forEach var="success" items="${successes}">
				<li>${success}</li>
			</c:forEach>
		</ul>
	</div>
	<% session.setAttribute("successes", null); %>
</c:if>