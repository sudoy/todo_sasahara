<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- エラー時表示 --%>
<c:if test="${errors.size() > 0 }">

	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong>エラーが発生しました!</strong>

		<c:forEach var="error" items="${errors}">
			<li>${error}</li>
		</c:forEach>

	</div>
	<% session.setAttribute("errors", null); %>
</c:if>