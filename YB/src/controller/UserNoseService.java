package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.NosePrintDAO;
import model.NosePrintDTO;

/**
 * Servlet implementation class UserNoseService
 */
@WebServlet("/UserNoseService")
public class UserNoseService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("아파아파");
		String id = request.getParameter("id");
		System.out.println("아이디 : "+id);
		
		NosePrintDAO dao = new NosePrintDAO();
		ArrayList<NosePrintDTO> nose_info =  dao.select(id);
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(nose_info));
		
		
	}

}
