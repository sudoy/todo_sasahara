<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="todo.utils.HTMLUtils"%>

<jsp:include page="header.jsp" />

	<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong>完了しました!</strong>
		<ul>
			<li>No.27のTodoを更新しました。</li>
		</ul>
	</div>

	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong>エラーが発生しました!</strong>
		<ul>
			<li>題名は必須入力です。</li>
			<li>題名は100文字以内にして下さい。</li>
			<li>期限は「YYYY/MM/DD」形式で入力して下さい。</li>
		</ul>
	</div>

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
				<td><a href="update.html">${todo.title}</a></td>
				<td>${todo.imp}</td>
				<td>
					${HTMLUtils.dateFormat(todo.limit_date)}
				</td>
			</tr>
		</c:forEach>


	</table>
	<a href="entry.html" class="btn btn-primary">追 加</a>

<jsp:include page="footer.jsp" />
