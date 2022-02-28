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

/**
 * Servlet implementation class HellSinkeiMain
 */
@WebServlet("/HellSinkeiMain")
public class HellSinkeiMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		SinkeiLogic sl = new SinkeiLogic();
		Integer done = (Integer)session.getAttribute("doneh");
		Integer dode = (Integer)session.getAttribute("dodeh");
		String reset = request.getParameter("reset");
		if(done == null) {
			done = 0;
		}
		if(dode == null) {
			dode = 0;
		}
		if(reset != null) {
			session.removeAttribute("cardsListh");
			done = 0;
			dode = 0;
		}
		List<Cards> cardsList = (List<Cards>)session.getAttribute("cardsListh");
		if(cardsList == null) {
			cardsList = sl.cardsSet(4);
			sl.cardMix(cardsList);
		}

		session.setAttribute("doneh", done);
		session.setAttribute("dodeh", dode);
		session.setAttribute("cardsListh", cardsList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/hellSinkeiMain.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		List<Cards> cardsList = (ArrayList<Cards>)session.getAttribute("cardsListh");
		String cardNum = request.getParameter("cardOpen");
		Integer box = (Integer)session.getAttribute("boxh");
		Integer newbox = (Integer)session.getAttribute("newboxh");
		Integer count = (Integer)session.getAttribute("counth");
		Integer done = (Integer)session.getAttribute("doneh");
		Integer dode = (Integer)session.getAttribute("dodeh");
		if(count == null) {
			count = 0;
			session.setAttribute("counth", count);
		}
		if(box == null) {
			box = Integer.parseInt(cardNum);
			cardsList.get(box).setCardAct(true);
			session.setAttribute("boxh", box);
		}else if(newbox == null){
			newbox = Integer.parseInt(cardNum);
			cardsList.get(newbox).setCardAct(true);
			session.setAttribute("newboxh", newbox);
			count += 1;
		}else {
			count += 1;
		}
		session.setAttribute("counth", count);
		if(newbox == null) {
		}else if(cardsList.get(box).getCardMark()-cardsList.get(newbox).getCardMark() == 1
				|| cardsList.get(box).getCardMark()-cardsList.get(newbox).getCardMark() == -1) {
			if(cardsList.get(box).getCardMark()+cardsList.get(newbox).getCardMark() != 5) {
				if(cardsList.get(box).getCardNum() == cardsList.get(newbox).getCardNum()) {
					cardsList.get(box).setCardLive(false);
					cardsList.get(newbox).setCardLive(false);
					session.removeAttribute("boxh");
					session.removeAttribute("newboxh");
					session.removeAttribute("counth");
					done ++;
					dode ++;
				}
			}
		}
		if(count >= 2) {
			cardsList.get(box).setCardAct(false);
			cardsList.get(newbox).setCardAct(false);
			session.removeAttribute("boxh");
			session.removeAttribute("newboxh");
			session.removeAttribute("counth");
			dode ++;
		}
		session.setAttribute("doneh", done);
		session.setAttribute("dodeh", dode);
		session.setAttribute("cardsListh", cardsList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/hellSinkeiMain.jsp");
		rd.forward(request, response);
	}

}
