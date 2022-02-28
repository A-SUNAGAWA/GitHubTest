package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SinkeiLogic {

	public List<Cards> cardsSet(int mark){
		//引数markの数*13枚のCardsを生成し、それをArrayList<Cards>のcardsListにぶち込んで返すメソッド
		List<Cards> cardsList = new ArrayList<>();
		for(int i=1;i<=mark;i++) {
			for(int t=1;t<=13;t++) {
				cardsList.add(new Cards(i,t));
			}
		}
		System.out.println(mark*13+"枚生成");
		//テスト用にコンソールに何枚生成されたか書いておく。
		return cardsList;
	}

	public List<Cards> cardMix(List<Cards> cardsList){
		//渡されたcardsListをシャッフルして返すだけのメソッド
		List<Cards> mixedCardsList = cardsList;
		Collections.shuffle(mixedCardsList);
		return mixedCardsList;
	}
}
