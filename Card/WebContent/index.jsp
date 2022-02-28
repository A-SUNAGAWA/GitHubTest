<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トランプ</title>
<link rel="stylesheet" href="css/pages.css"/>
</head>
<body>
<h1>トランプ</h1>
<h2>モード選択</h2>
<div class="center">
<form action="/Card/ModeChose">
<%--ボタン選択式。「神経衰弱」を選んだ場合、ModeChoseに値0が入ったmodeが飛んでいく --%>
<button type="submit" name="mode" value="0">神経衰弱</button>
</form>
<button type="submit" name="mode" value="1">ポーカー(未実装)</button><br>
<button type="submit" name="mode" value="2">七並べ(未実装)</button>
</div>
<input type="image" title="特に意味のないじゃがいも" src="img/じゃがいも.png" class="potato">
</body>
</html>