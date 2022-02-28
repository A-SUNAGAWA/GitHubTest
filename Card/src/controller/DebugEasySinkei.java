package controller;

import java.io.IOException;
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
 * Servlet implementation class DebugEasySinkei
 */
@WebServlet("/DebugEasySinkei")
public class DebugEasySinkei extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		SinkeiLogic sl = new SinkeiLogic();
		Integer done = (Integer)session.getAttribute("done");
		Integer dode = (Integer)session.getAttribute("dode");
		Integer potato = (Integer)session.getAttribute("potato");
		String reset = request.getParameter("reset");
		if(done == null) {
			done = 0;
		}
		if(dode == null) {
			dode = 0;
		}
		if(reset != null) {
			session.removeAttribute("cardsList");
			done = 0;
			dode = 0;
		}
		List<Cards> cardsList = (List<Cards>)session.getAttribute("cardsList");
		String mix = request.getParameter("mix");
		if(mix != null) {
			sl.cardMix(cardsList);
		}
		if(cardsList == null) {
			cardsList = sl.cardsSet(2);
			sl.cardMix(cardsList);
		}
		if(potato == null) {
			potato = 1;
			session.setAttribute("potato", potato);
		}else if(potato > 3) {
			for(int i=0;i<cardsList.size();i++) {
				cardsList.get(i).setCardLive(false);
			}
			done = cardsList.size()/2;
			session.removeAttribute("potato");
		}else {
			potato ++;
			session.setAttribute("potato", potato);
		}
		System.out.println(potato);
		session.setAttribute("done", done);
		session.setAttribute("dode", dode);
		session.setAttribute("cardsList", cardsList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/easySinkeiMain.jsp");
		rd.forward(request, response);

	}

}
