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

import model.BoardDAO;
import model.BoardDTO;

/**
 * Servlet implementation class PictureResultShowService
 */
@WebServlet("/PictureResultShowService")
public class PictureResultShowService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("사진결과보기접속");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
	    PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list =dao.pictureresult(id);
		
	}

}
