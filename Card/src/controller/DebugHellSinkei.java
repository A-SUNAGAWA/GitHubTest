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
 * Servlet implementation class DebugHellSinkei
 */
@WebServlet("/DebugHellSinkei")
public class DebugHellSinkei extends HttpServlet {
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
		Integer potato = (Integer)session.getAttribute("potatoh");
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
		String mix = request.getParameter("mix");
		if(mix != null) {
			sl.cardMix(cardsList);
		}
		if(cardsList == null) {
			cardsList = sl.cardsSet(4);
			sl.cardMix(cardsList);
		}
		if(potato == null) {
			potato = 1;
			session.setAttribute("potatoh", potato);
		}else if(potato > 3) {
			for(int i=0;i<cardsList.size();i++) {
				cardsList.get(i).setCardLive(false);
			}
			done = cardsList.size()/2;
			session.removeAttribute("potatoh");
		}else {
			potato ++;
			session.setAttribute("potatoh", potato);
		}
		session.setAttribute("doneh", done);
		session.setAttribute("dodeh", dode);
		session.setAttribute("cardsListh", cardsList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/hellSinkeiMain.jsp");
		rd.forward(request, response);
	}

}
