package controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.CheckDTO;
import model.MemberDAO;
import model.MemberDTO;

/**
 * Servlet implementation class Join
 */
@WebServlet("/Join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String join_id = request.getParameter("join_id");
		String join_pw = request.getParameter("join_pw");
		String join_name = request.getParameter("join_name");
		int join_tel = Integer.parseInt(request.getParameter("join_tel"));
		String join_addr = request.getParameter("join_addr");
		int membercode = Integer.parseInt(request.getParameter("join_check"));
		
		System.out.println(join_id);
		System.out.println(join_pw);
		System.out.println(join_name);
		System.out.println(join_tel);
		System.out.println(join_addr);
		System.out.println(membercode);
		
		MemberDTO dto = new MemberDTO(join_id, join_pw, join_name, membercode, join_tel, join_addr);
		MemberDAO dao = new MemberDAO();
		int cnt = dao.join(dto);
		PrintWriter out = response.getWriter();
		CheckDTO check = null;
		
		
		if (cnt > 0) {
			System.out.println("회원가입성공");
			check = new CheckDTO(true);
		}else{
			System.out.println("회원가입실패");
			check = new CheckDTO(false);
		} 
		Gson gson = new Gson();
		System.out.println(gson.toJson(check));
		out.print(gson.toJson(check));
	}

}
