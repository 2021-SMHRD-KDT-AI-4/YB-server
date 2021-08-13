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
import model.BoardDTO;

@WebServlet("/PictureDetailService")
public class PictureDetailService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("사진결과디테일보기접속");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
	    PrintWriter out = response.getWriter();
		String filename = request.getParameter("filename");
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.picturedetail(filename);
		System.out.println(dto.getContent());
		out.print(gson.toJson(dto));
		
	}

}
