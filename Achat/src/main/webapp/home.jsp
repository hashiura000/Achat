<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>
</head>
<body>
<h2>ログインできました</h2></h2><br>
<form action="/Achat/ChatRoom" method="get" accept-charset="UTF-8">
	<label for="textbox">チャットルーム名：</label>
	<input type="text" name="name">
	<input type="submit" class="button" value="送信">
</form><br><br>
<form action="/Achat/Login" method="post" accept-charset="UTF-8">
	<label for="textbox">友達の名前：</label>
	<input type="text" name="registerName">
	<input type="submit" class="button" value="AChatを友達に紹介する">
</form><br>
<a href="index.html" class="button">ログイン画面に戻る</a>
<style>
    .button {
        display: inline-block;
        padding: 10px 20px;
        font-size: 16px;
        font-weight: bold;
        text-decoration: none;
        color: white;
        background-color: #007bff;
        border: 1px solid #007bff;
        border-radius: 5px;
        cursor: pointer;
    }
</style>
</body>
</html>