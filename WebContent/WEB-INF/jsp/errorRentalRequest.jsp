<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="css/style.css" rel="stylesheet">
		<meta charset="UTF-8">
		<title>貸出申請エラー</title>
	</head>
	<body>
		<%@ include file="header.jsp" %>

		<div class="error_page">
			<h2>指定した書籍は既に他の利用者に貸出済みです</h2>
			<p><a href="home">書籍一覧</a>に戻る</p>
		</div>
	</body>
</html>