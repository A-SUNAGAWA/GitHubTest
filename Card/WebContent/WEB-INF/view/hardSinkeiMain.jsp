<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Cards,java.util.*"%>
<%
List<Cards> cardsList = (ArrayList<Cards>)session.getAttribute("cardsListd");
Integer done = (Integer)session.getAttribute("doned");
Integer dode = (Integer)session.getAttribute("doded");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>神経衰弱	(難しい))</title>
<link rel="stylesheet" href="css/pages.css"/>
</head>
<body>

<%for(int i=0;i<cardsList.size();i++){ %>
	<%if(!cardsList.get(i).isCardLive()){ %>
		<img alt="揃ったカード" src="img/<%=cardsList.get(i) %>.png" class="img_sized">
	<%}else if(cardsList.get(i).isCardAct()){ %>
		<img alt="表のカード" src="img/<%=cardsList.get(i) %>.png" class="img_sized">
	<%}else{ %>
		<form action="HardSinkeiMain?cardOpen=<%=i %>" method="post">
		<input type="image" alt="裏のカード" src="img/default.png" class="img_sized">
		</form>
	<%} %>
<%} %>
<%if(done.equals(cardsList.size()/2)){ %>
	<%if(dode < cardsList.size()/2){ %>
	<p class="clear">チーター発見！！</p>
	<%}else if(dode <= cardsList.size()){ %>
	<p class="clear">すごい</p>
	<% }else{%>
	<p class="clear">クリア！</p>
	<%} %>
	<p class="botom">かかった回数:<%=dode %>回</p>
<%}else{ %>
	<p class="botom">揃えた回数:<%=done %>回</p>
<%} %>
<div class="botom">
<form action="HardSinkeiMain">
<button type="submit" name="reset">リセット</button>
</form>
<form action="ModeChose">
<button type="submit" name="mode" value="0" class="back">戻る</button>
</form>
</div>
<form action="DebugHardSnkei">
<input type="image" title="デバッグ用のじゃがいも。押すな" src="img/じゃがいも.png" class="potato">
</form>
</body>
</html>