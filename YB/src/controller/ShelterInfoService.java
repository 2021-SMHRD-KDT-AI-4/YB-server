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

import model.ShelterDAO;
import model.ShelterDTO;

/**
 * Servlet implementation class ShelterInfoService
 */
@WebServlet("/ShelterInfoService")
public class ShelterInfoService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("!!보호소정보보기입장!!");
		Gson gson = new Gson();
	    PrintWriter out = response.getWriter();
		String sido = request.getParameter("sido");
		String gungu = request.getParameter("gungu");
		
		System.out.println("시도 : "+ sido +"군구 :" +gungu);
		
		ShelterDAO dao = new ShelterDAO();
		ArrayList<ShelterDTO> list = dao.selectinfo(sido, gungu);
		System.out.println(gson.toJson(list));
		out.print(gson.toJson(list));
		
		
	}

}
