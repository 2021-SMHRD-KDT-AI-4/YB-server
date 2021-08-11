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

import model.CommentDAO;
import model.CommentDTO;

/**
 * Servlet implementation class CommentViewService
 */
@WebServlet("/CommentViewService")
public class CommentViewService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println("게시글 번호 : " +" "+ board_num);
		
		CommentDAO dao = new CommentDAO();
		ArrayList<CommentDTO> commentinfo = dao.show(board_num);
		System.out.println("제발 나와라 : "+ gson.toJson(commentinfo));
		out.print(gson.toJson(commentinfo));
		
	}

}
