<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Cards,java.util.*"%>
<%
List<Cards> cardsList = (ArrayList<Cards>)session.getAttribute("cardsList");
Integer done = (Integer)session.getAttribute("done");
Integer dode = (Integer)session.getAttribute("dode");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>神経衰弱(易しい)</title>
<link rel="stylesheet" href="css/pages.css"/>
</head>
<body>
<%for(int i=0;i<cardsList.size();i++){ %>
<%--cardsListのサイズ分cradsListからCardsを配置。Cardsのプロパティの状態により配置方法に変化をつける --%>
	<%if(!cardsList.get(i).isCardLive()){ %>
		<img alt="揃ったカード" src="img/<%=cardsList.get(i) %>.png" class="img_size">
	<%}else if(cardsList.get(i).isCardAct()){ %>
		<img alt="表のカード" src="img/<%=cardsList.get(i) %>.png" class="img_size">
	<%}else{ %>
		<%--もし上記二つの条件に当てはまらないなら、カードの裏面の画像(共通)ボタンが表示される --%>
		<form action="EasySinkeiMain?cardOpen=<%=i %>" method="post">
		<%--画像をクリックするとEasySinkeiMainのpostメソッドへ --%>
		<input type="image" alt="裏のカード" src="img/default.png" class="img_size">
		</form>
	<%} %>
<%} %>
<%if(done.equals(cardsList.size()/2)){ %>
	<%if(dode >= cardsList.size()/2){ %>
		<%--もしdoneがcradsList.size()/2=全ペアが揃ったら、クリアの文字を表示 --%>
		<p class="clear">クリア！！</p>
	<%}else{ %>
		<%--後述のじゃがいもを使ってクリアすると怒られる --%>
		<p class="clear">チーター発見！！</p>
	<%} %>
	<%--ついでにここでかかった回数を表示 --%>
	<p class="botom">かかった回数:<%=dode %>回</p>
<%}else{ %>
	<%--もしまだクリアしていないなら、今までに何ペア揃えたかを表示 --%>
	<p class="botom">揃えた回数:<%=done %>回</p>
<%} %>
<div class="botom">
<form action="EasySinkeiMain">
<%--リセットボタン。cardsList,done,dodeが全てリセットされる --%>
<button type="submit" name="reset" value="yes">リセット</button>
</form>
<form action="ModeChose">
<%--戻るボタン。ModeChoseで神経衰弱を選んだ状態のページ=sinkeiDifChose.jspへ --%>
<button type="submit" name="mode" value="0" class="back">戻る</button>
</form>
</div>
<form action="DebugEasySinkei">
<input type="image" title="デバッグ用のじゃがいも。押すな" src="img/じゃがいも.png" class="potato">
</form>
</body>
</html>