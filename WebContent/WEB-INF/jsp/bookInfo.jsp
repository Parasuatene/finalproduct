<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="css/style.css" rel="stylesheet">
		<meta charset="UTF-8">
		<title>書籍情報</title>
	</head>
	<body>
		<!-- ヘッダーの読み込み -->
		<%@ include file="header.jsp" %>

		<c:set var="book" value="${book}"/>

		<c:if test="${empty book}">
			<!-- ページが見つからなかった時の処理 -->
		 	<h1>お探しのページは見つかりませんでした</h1>
		 	<a href="home">書籍一覧ページに戻る</a>
		</c:if>

		<c:if test="${not empty book}">
			<!-- ページが見つかった時の処理 -->
			<div class="book_info">
				<h3>${book.title}</h3>
				<p>${book.author}</p>
				<p>${book.publisher}</p>
				<p>${book.imgPath}</p>
				<p>${book.discription}</p>
				<button onclick="location.href='rentalRequest?id=${book.id}'">貸出申請を行う</button>
			</div>
		</c:if>
	</body>
</html>