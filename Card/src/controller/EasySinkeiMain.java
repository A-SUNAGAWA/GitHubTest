package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cards;
import model.SinkeiLogic;


@WebServlet("/EasySinkeiMain")
public class EasySinkeiMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SinkeiLogic sl = new SinkeiLogic();
		Integer done = (Integer)session.getAttribute("done");
		Integer dode = (Integer)session.getAttribute("dode");
		//doneは数字がそろった回数、dodeはカードをめくった回数
		String reset = request.getParameter("reset");
		//リセットボタンが押されたかの判定用変数reset
		if(done == null) {
			done = 0;
		}
		if(dode == null) {
			dode = 0;
		}
		if(reset != null) {
			//resetがnullでない=ボタンが押されている場合、保存されているcardsListを捨ててdoneとdodeをリセット
			session.removeAttribute("cardsList");
			done = 0;
			dode = 0;
		}
		List<Cards> cardsList = (List<Cards>)session.getAttribute("cardsList");
		String mix = request.getParameter("mix");

		if(cardsList == null) {
			//もしcardsListがnullなら新たに生成。易しいモードなので2*13=26枚。
			cardsList = sl.cardsSet(2);
			sl.cardMix(cardsList);
		}
		session.setAttribute("done", done);
		session.setAttribute("dode", dode);
		session.setAttribute("cardsList", cardsList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/easySinkeiMain.jsp");
		//easySinkeiMain.jspへ
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		List<Cards> cardsList = (ArrayList<Cards>)session.getAttribute("cardsList");
		String cardNum = request.getParameter("cardOpen");
		//easySinkeiMain.jspから送られてきた値(その時めくられたcardsListのindex)を変数cardNumとしてStringで受け取る。
		Integer box = (Integer)session.getAttribute("box");
		//1回目にめくられたcardsListの場所(index)を保存する変数box
		Integer newbox = (Integer)session.getAttribute("newbox");
		//2回目にめくられたcardsListの場所(index)を保存する変数newbox
		Integer count = (Integer)session.getAttribute("count");
		Integer done = (Integer)session.getAttribute("done");
		Integer dode = (Integer)session.getAttribute("dode");
		if(count == null) {
			count = 0;
			session.setAttribute("count", count);
		}
		if(box == null) {
			box = Integer.parseInt(cardNum);
			cardsList.get(box).setCardAct(true);
			session.setAttribute("box", box);
		}else if(newbox == null){
			newbox = Integer.parseInt(cardNum);
			cardsList.get(newbox).setCardAct(true);
			session.setAttribute("newbox", newbox);
			count += 1;
		}else {
			count += 1;
		}
		session.setAttribute("count", count);
		if(newbox != null && cardsList.get(box).getCardNum() == cardsList.get(newbox).getCardNum()) {
			/* 非常に悩んだ部分。ざっくりいうと成功判定。
			 * もしcardsListのboxの位置にあるCardsのcardNumがnewboxの位置にあるCardsのcardNumと等値なら、
			 * それぞれの位置にあるCardsのcardLiveをfalseにする。
			 * こうすることで、そのカードは疑似的に場から取り除かれたことになる。
			 * つまりめくった2枚の数字が一緒だったらそのペアが場から消える。
			 * ちなみにnewboxのnull判定をここでも行っているのは、上のif文でのnull判定をelseで行っているから*/
			cardsList.get(box).setCardLive(false);
			cardsList.get(newbox).setCardLive(false);
			session.removeAttribute("box");
			session.removeAttribute("newbox");
			session.removeAttribute("count");
			//めくりが2回目以降の場合box,newbox,countは邪魔になるので捨てる
			done ++;
			dode ++;
		}else if(count >= 2) {
			/* もしcountが2以上=3回目のめくりをしようとした場合、表になった2枚が裏に戻る。
			 * つまり2枚表となっている場合にのみ成功/失敗判定が行われる。
			 * そのためcountには3回目のめくり判定も用意する必要があった。
			 * こうしないと数字をそろえるのに失敗した際、2枚目に何のカードをめくったのかがわからない*/
			cardsList.get(box).setCardAct(false);
			cardsList.get(newbox).setCardAct(false);
			session.removeAttribute("box");
			session.removeAttribute("newbox");
			session.removeAttribute("count");
			dode ++;
		}
		/* 上の二つに引っかからない=１回目のめくりなので、
		 * box,newbox,countをif文の外で捨てようとするとえらい目に合う*/
		session.setAttribute("done", done);
		session.setAttribute("dode", dode);
		session.setAttribute("cardsList", cardsList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/easySinkeiMain.jsp");
		rd.forward(request, response);

	}

}
