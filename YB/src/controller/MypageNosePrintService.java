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
 * Servlet implementation class MypageNosePrintService
 */
@WebServlet("/MypageNosePrintService")
public class MypageNosePrintService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("마이페이지접속");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
	    PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		NosePrintDAO dao = new NosePrintDAO();
		ArrayList<NosePrintDTO> list = dao.select(id);
		if (list != null) {
			System.out.println(gson.toJson(list));
			out.print(gson.toJson(list));
		}else {
			System.out.println("읎다..");
		}
	}

}
