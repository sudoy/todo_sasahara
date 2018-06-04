<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="todo.utils.HTMLUtils"%>

<jsp:include page="header.jsp" />

	<table class="table">
		<tr>
			<th class="col-sm-1">#</th>
			<th>題名</th>
			<th>重要度</th>
			<th>期限</th>
		</tr>

		<c:forEach var="todo" items="${list}">
			<tr>
				<td>${todo.id}</td>
				<td><a href="update.html?id=${todo.id }">${todo.title}</a></td>
				<td>${todo.imp}</td>
				<td>
					${HTMLUtils.dateFormat(todo.limitDate)}
				</td>
			</tr>
		</c:forEach>


	</table>
	<a href="entry.html" class="btn btn-primary">追 加</a>

<jsp:include page="footer.jsp" />
