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
 * Servlet implementation class Join_id_check
 */
@WebServlet("/Join_id_check")
public class Join_id_check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String join_id = request.getParameter("join_id");
		CheckDTO check = null;
		PrintWriter out = response.getWriter();
		MemberDTO dto = new MemberDTO(join_id);
		MemberDAO dao = new MemberDAO();
		 
		boolean id_check = dao.idcheck(dto);
		
		if (id_check) {
			check = new CheckDTO(true);
		}else {
			check = new CheckDTO(false);
		}
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(check));
		out.print(gson.toJson(check));
		
				
	}

}
