package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModeChose
 */
@WebServlet("/ModeChose")
public class ModeChose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int chose;
		//モード選択。誰にも使われないかわいそうなswitch文で書いた
		try {
			//intに変換したmodeの値をぶち込む。"0"なら0が入る
			chose = Integer.parseInt(request.getParameter("mode"));
			//switch文をint以外で書くなら変換の必要なし
		}catch(Exception e) {
			//もし何らかの原因でmodeがnullだった場合、9999を代入して強制的にindex.jspへ飛ぶ
			chose = 9999;
		}
		RequestDispatcher rd;
		switch(chose) {
		case 0:
			//modeが0ならsinkeiDifChose.jspへ
			rd = request.getRequestDispatcher("/WEB-INF/view/sinkeiDifChose.jsp");
			rd.forward(request, response);
		default:
			response.sendRedirect("index.jsp");
		}
	}
}
