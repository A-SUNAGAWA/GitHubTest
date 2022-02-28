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
 * Servlet implementation class HardSinkeiMain
 */
@WebServlet("/HardSinkeiMain")
public class HardSinkeiMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		SinkeiLogic sl = new SinkeiLogic();
		Integer done = (Integer)session.getAttribute("doned");
		Integer dode = (Integer)session.getAttribute("doded");
		String reset = request.getParameter("reset");
		if(done == null) {
			done = 0;
		}
		if(dode == null) {
			dode = 0;
		}
		if(reset != null) {
			session.removeAttribute("cardsListd");
			done = 0;
			dode = 0;
		}
		List<Cards> cardsList = (List<Cards>)session.getAttribute("cardsListd");
		if(cardsList == null) {
			cardsList = sl.cardsSet(4);
			sl.cardMix(cardsList);
		}

		session.setAttribute("doned", done);
		session.setAttribute("doded", dode);
		session.setAttribute("cardsListd", cardsList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/hardSinkeiMain.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		List<Cards> cardsList = (ArrayList<Cards>)session.getAttribute("cardsListd");
		String cardNum = request.getParameter("cardOpen");
		Integer box = (Integer)session.getAttribute("boxd");
		Integer newbox = (Integer)session.getAttribute("newboxd");
		Integer count = (Integer)session.getAttribute("countd");
		Integer done = (Integer)session.getAttribute("doned");
		Integer dode = (Integer)session.getAttribute("doded");
		if(count == null) {
			count = 0;
			session.setAttribute("countd", count);
		}
		if(box == null) {
			box = Integer.parseInt(cardNum);
			cardsList.get(box).setCardAct(true);
			session.setAttribute("boxd", box);
		}else if(newbox == null){
			newbox = Integer.parseInt(cardNum);
			cardsList.get(newbox).setCardAct(true);
			session.setAttribute("newboxd", newbox);
			count += 1;
		}else {
			count += 1;
		}
		session.setAttribute("countd", count);
		if(newbox != null && cardsList.get(box).getCardNum() == cardsList.get(newbox).getCardNum()) {
			cardsList.get(box).setCardLive(false);
			cardsList.get(newbox).setCardLive(false);
			session.removeAttribute("boxd");
			session.removeAttribute("newboxd");
			session.removeAttribute("countd");
			done ++;
			dode ++;
		}else if(count >= 2) {
			cardsList.get(box).setCardAct(false);
			cardsList.get(newbox).setCardAct(false);
			session.removeAttribute("boxd");
			session.removeAttribute("newboxd");
			session.removeAttribute("countd");
			dode ++;
		}
		session.setAttribute("doned", done);
		session.setAttribute("doded", dode);
		session.setAttribute("cardsListd", cardsList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/hardSinkeiMain.jsp");
		rd.forward(request, response);
	}

}
