<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="css/style.css" rel="stylesheet">
		<meta charset="UTF-8">
		<title>借りている書籍一覧</title>
	</head>
	<body>
		<%@ include file="header.jsp" %>

		<c:if test="${empty requestScope.rentalBookList}">
			<div class="not_found">
				<h2>現在、借りている書籍はございません</h2>
				<p><a href="home">書籍一覧に戻る</a></p>
			</div>
		</c:if>

		<c:if test="${not empty requestScope.rentalBookList}">
			<c:forEach var="book" items="${rentalBookList}">
				<div class="book_list">
					<figure class="image">
						<a href="returnRequest?id=${book.rentalControl.id}">
							<img src="img/<c:out value="${book.imgPath}" />" alt="">
						</a>
					</figure>
					<div class="book_info">
						<c:set var="startDate" value="${book.rentalControl.startDate}"/>
						<c:set var="scheduleDate" value="${book.rentalControl.scheduleDate}"/>
						<c:set var="endDate" value="${book.rentalControl.endDate}"/>
						<c:if test="${not empty startDate and empty endDate}">
							<p class="rental_status">貸出中（返却予定日:${scheduleDate}）</p>
						</c:if>
						<h3><a href="returnRequest?id=${book.rentalControl.id}">${book.title}</a></h3>
						<label for="author">${book.author}</label>
						<label for="publisher">${book.publisher}</label>
						<p class="discription">${book.discription}</p>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</body>
</html>