package controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.MemberDAO;
import model.MemberDTO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String login_id = request.getParameter("login_id");
		String login_pw = request.getParameter("login_pw");
		
		CheckDTO check = null;
		
		MemberDTO dto = new MemberDTO(login_id, login_pw);
		MemberDAO dao = new MemberDAO();
		MemberDTO info  = dao.login(dto);

		if (info != null) {
			System.out.println(info.getId()+""+info.getPw()+""+info.getTel()+""+info.getAddress());
			check = new CheckDTO(true);
		}else {
			System.out.println("로그인 정보없다");
			check = new CheckDTO(false);
		}
		Gson gson = new Gson();
		
		out.print(gson.toJson(info));
			

	}
}
