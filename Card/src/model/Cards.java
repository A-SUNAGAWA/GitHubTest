package model;

import java.io.Serializable;

public class Cards implements Serializable{
	private int cardMark;
	//カードのマーク(♤,♧,♡,♢)にそれぞれ数字を割り振ったもの
	private int cardNum;
	//カードの数字
	private boolean cardAct;
	//カードが裏か表か。デフォルトはfalse
	private boolean cardLive = true;
	//カードが場に有るか無いか。デフォルトはtrue

	public Cards() {}

	public Cards(int cm,int cn) {
		//引数にcardMarkとcardNumを指定する場合のコンストラクタ
		this.cardMark=cm;
		this.cardNum=cn;
	}

	public Cards(int cm,int cn,boolean ca) {
		//引数にcardMarkとcardNum、そしてcardActも指定する場合のコンストラクタ
		this(cm,cn);
		this.cardAct=ca;
	}

	public int getCardMark() {return cardMark;}

	public void setCardMark(int cardMark) {this.cardMark = cardMark;}

	public int getCardNum() {return cardNum;}

	public void setCardNum(int cardNum) {this.cardNum = cardNum;}

	public boolean isCardAct() {return cardAct;}

	public void setCardAct(boolean cardAct) {this.cardAct = cardAct;}

	public boolean isCardLive() {return cardLive;}

	public void setCardLive(boolean cardLive) {this.cardLive = cardLive;}

	public String toString() {
		//Cardsインスタンスそのものを文字化する場合に備えたtoString
		return getCardMark()+"-"+getCardNum();
	}


}
