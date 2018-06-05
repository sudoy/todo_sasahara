<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />


	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<title>タイトル</title>
</head>

<body>

<nav class="navbar navbar-default">
	<div class="container">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.html">Todoリスト</a>
			</div>

			<c:if test="${login ne null}">
				<div class="btn-group pull-right login-top">
					<a class="btn btn-primary dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						${login.name }<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="logout.html">ログアウト</a></li>
					</ul>
				</div>
			</c:if>
		</div>
	</div>

</nav>

<div class="container ">

<jsp:include page="_success.jsp" />

<jsp:include page="_error.jsp" />

	<div class="row">