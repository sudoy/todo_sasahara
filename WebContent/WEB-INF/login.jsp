<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="_loginHeader.jsp" />

<div class="main-login main-center">

	<form class="form-horizontal" method="post" action="login.html">
		<div class="login-top">ログイン</div>

		<jsp:include page="_error.jsp" />

		<div class="form-group">
			<label for="mail" class="col-sm-4 control-label">メールアドレス</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" name="mail" id="mail" placeholder="メールアドレス" value="${param.mail }">
			</div>
		</div>

		<div class="form-group">
			<label for="pass" class="col-sm-4 control-label">パスワード</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="pass" id="pass" placeholder="パスワード" value="${param.pass }">
			</div>
		</div>


		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-primary pull-right" type="submit"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>ログイン</button>
			</div>
		</div>

	</form>
</div>

<jsp:include page="footer.jsp" />
