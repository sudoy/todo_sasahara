<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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

	<p>更新フォーム</p><hr>

	<form class="form-horizontal" action="update.html" method="post">
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">題名</label>
			<div class="col-sm-offset-2">
				<input type="text" class="form-control" id="title" placeholder="題名" value="テスト">
			</div>
		</div>

		<div class="form-group">
			<label for="detail" class="col-sm-2 control-label">詳細</label>
			<div class="col-sm-offset-2">
				<textarea class="form-control" rows="3" id="detail" placeholder="詳細">SQLの確認テスト</textarea>
			</div>
		</div>

		<div class="form-group">
			<label for="imp" class="col-sm-2 control-label">重要度</label>
			<div class="radio">
				<div class="col-sm-offset-2">
					<label>
						<input type="radio" name="impRadios" id="imp" value="imp3" checked>
						★★★
					</label>
				</div>
				<div class="col-sm-offset-2">
					<label>
						<input type="radio" name="impRadios" id="imp" value="imp2">
						★★
					</label>
				</div>
				<div class="col-sm-offset-2">
					<label>
						<input type="radio" name="impRadios" id="imp" value="imp1">
						★
					</label>
				</div>
			</div>
		</div>


		<div class="form-group">
			<label for="limit" class="col-sm-2 control-label">期限</label>
			<div class="col-sm-offset-2">
				<input type="text" class="form-control" id="limit" placeholder="期限" value="2015/06/20">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2">
				<a href="index.html" class="btn btn-default"> キャンセル</a>
				<a href="index.html" class="btn btn-primary">更新</a>
				<a href="index.html" class="btn btn-danger pull-right">削除</a>
			</div>
		</div>

	</form>

<jsp:include page="footer.jsp" />