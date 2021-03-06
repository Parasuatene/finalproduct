package jp.co.benesse.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.benesse.dataaccess.value.User;
import jp.co.benesse.service.UserService;
import myapi.HashGenerator;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/jsp/adminLogin.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// パラメータの取得
		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");

		// ログインID・パスワードの組み合わせがDBに存在するかを確認する
		UserService userService = new UserService();
		// パスワードをハッシュ値に変換
		password = HashGenerator.getHash(password);
		User user = userService.getUserByIdAndPassword(loginId, password);

		// userがnull、または権限が一般ユーザのとき
		if (user == null || user.getAuthority() == 0) {
			String errorMessage = "ログインID、またはパスワードに誤りがあります";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("WEB-INF/jsp/adminLogin.jsp").forward(request, response);
			return;
		}

		// ログインに成功した場合は、ログインIDと権限をセッションに保持し、書籍一覧画面に移動する
		HttpSession session = request.getSession(true);
		session.setAttribute("id", user.getId());
		session.setAttribute("authority", user.getAuthority());

		response.sendRedirect("adminHome");
		return;
	}

}
