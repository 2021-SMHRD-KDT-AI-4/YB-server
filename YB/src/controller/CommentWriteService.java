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
import model.CommentDAO;
import model.CommentDTO;

/**
 * Servlet implementation class CommentService
 */
@WebServlet("/CommentWriteService")
public class CommentWriteService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		CheckDTO check = null;
		String comments = request.getParameter("edt_write_review");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String id = request.getParameter("id");
		
		
		
		CommentDTO dto = new CommentDTO(board_num, id, comments);
		CommentDAO dao = new CommentDAO();
		
		int cnt = dao.insert(dto);
		
		if(cnt > 0 ) {
			System.out.println("댓글 등록 성공");
			check = new CheckDTO(true);
		}else {
			System.out.println("댓글 등록 실패");
			check = new CheckDTO(false);
		}
		out.print(gson.toJson(check));
		
		
	}

}
