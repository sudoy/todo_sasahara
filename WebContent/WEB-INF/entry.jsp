<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="header.jsp" />

	<strong>登録フォーム</strong><hr>

	<form class="form-horizontal" action="entry.html" method="POST">

		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">題名</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="title" id="title" placeholder="題名" value="${param.title }">
			</div>
		</div>

		<div class="form-group">
			<label for="detail" class="col-sm-2 control-label">詳細</label>
			<div class="col-sm-10">
				<textarea class="form-control" name="detail" rows="3" id="detail" placeholder="詳細">${param.detail }</textarea>
			</div>
		</div>

		<div class="form-group">
			<label for="impRadio" class="col-sm-2 control-label">重要度</label>
			<div class="radio">
				<div class="col-sm-offset-2">
					<label>
						<input type="radio" name="imp" id="impRadio" value="★★★" ${ param.imp eq '★★★' ? 'checked' : param.imp == null ? 'checked' : ''} >
						★★★
					</label>
				</div>
				<div class="col-sm-offset-2">
					<label>
						<input type="radio" name="imp" id="impRadio" value="★★"  ${param.imp eq '★★' ? 'checked' : ''}>
						★★
					</label>
				</div>
				<div class="col-sm-offset-2">
					<label>
						<input type="radio" name="imp" id="impRadio" value="★" ${param.imp eq '★' ? 'checked' : ''}>
						★
					</label>
				</div>
			</div>
		</div>


		<div class="form-group">
			<label for="limit" class="col-sm-2 control-label">期限</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="limit_date" id="limit" placeholder="期限" value="${param.limit_date }">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<a href="index.html" class="btn btn-default"> キャンセル</a>
				<button class="btn btn-primary" type="submit">追 加</button>
			</div>
		</div>

	</form>

<jsp:include page="footer.jsp" />