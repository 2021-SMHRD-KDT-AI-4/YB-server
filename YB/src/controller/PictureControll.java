package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.BoardDAO;
import model.BoardDAO;
import model.BoardDTO;
import model.CheckDTO;

/**
 * Servlet implementation class PictureControll
 */
@WebServlet("/PictureControll")
public class PictureControll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CheckDTO check = null;
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String filename = request.getParameter("filename");
		String id = request.getParameter("id");
		
		BoardDAO dao = new BoardDAO();
		int status = dao.statusupdate(id);
		int delete = dao.resultdelete(id);
		System.out.println("status : "+status + "delete : "+ delete);
		if (status > 0 && delete > 0) {
			System.out.println("status 1로 변경완료 & 매칭결과 삭제 완료");
			check = new CheckDTO(true);		
		}else {
			System.out.println("변경 실패..");
			check = new CheckDTO(false);
		}
		out.print(gson.toJson(check));
		//
		
		
	}

}
