<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="css/style.css" rel="stylesheet">
		<link href="css/adminHomeStyle.css" rel="stylesheet">
		<meta charset="UTF_8">
		<title>書籍登録</title>
	</head>
    <body>
		<%@ include file="adminHeader.jsp" %>

        <div class="contact">
            <h1 class="contact_ttl">新規書籍の登録</h1>

            <form name="upload" action="registrationComplete" enctype="multipart/form-data" method="post">
                <table class="contact_table">
                    <tr>
                        <th class="contact_item regist_required">
                            書籍名
                        </th>
                        <td class="contact_body">
                            <input type="text" name="title" class="form_text" required="required"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="contact_item">
                            書籍画像
                        </th>
                        <td class="contact_body">
                            <input type="file" name="uploadfile" id="fileInput"/>
                            <figure id="figure" style="display: none">
                                <figcaption>プレビュー</figcaption>
                                <img src="" alt="" id="figureImage" style="max-width: 100%">
                            </figure>
                        </td>
                    </tr>
                    <tr>
                        <th class="contact_item">
                            著者名
                        </th>
                        <td class="contact_body">
                            <input type="text" name="author" class="form_text" />
                        </td>
                    </tr>
                    <tr>
                        <th class="contact_item">
                            出版社名
                        </th>
                        <td class="contact_body">
                            <input type="text" name="publisher" class="form_text" />
                        </td>
                    </tr>
                    <tr>
                        <th class="contact_item">書籍の説明</th>
                        <td class="contact_body">
                            <textarea name="discription" class="form_textarea" placeholder="（1,000文字以内で入力してください）"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th class="contact_item">カテゴリ</th>
                        <td class="contact_body">
                            <select name="category" id="category" required="required">
                                <option disabled selected>カテゴリを選択してください</option>
								<c:forEach var="category" items="${categoryList}">
                                	<option value="${category.id}">${category.name}</option>
								</c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <input class="contact_submit" type="submit" value="登録" />
                <script src="js/preview.js"></script>
            </form>
        </div>
    </body>
</html>
