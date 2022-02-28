<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>神経衰弱</title>
<link rel="stylesheet" href="css/pages.css"/>
</head>
<body>
<h1>神経衰弱</h1>
<h2>難易度選択</h2>
<div class="center">
<form action="EasySinkeiMain">
<%--易しい.pngをクリックした場合、EasySinkeiMainへ --%>
<input type="image" title="カード数:26枚" src="img/易しい.png" class="img">
</form>
<form action="HardSinkeiMain">
<input type="image" title="カード数:52枚" src="img/難しい.png" class="img">
</form>
<form action="HellSinkeiMain">
<input type="image" title="カード数:52枚 高難度" src="img/地獄.png" class="img">
</form>
<form action="ModeChose">
<button type="submit" class="back">戻る</button>
</form>
</div>
<input type="image" title="特に意味のないじゃがいも" src="img/じゃがいも.png" class="potato">
</body>
</html>