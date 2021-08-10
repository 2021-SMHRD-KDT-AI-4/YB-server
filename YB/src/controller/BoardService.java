package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/BoardService")
public class BoardService extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
      Date time = new Date();
      String time2 = format2.format(time);
      
      BoardDAO dao = new BoardDAO();
      ArrayList<BoardDTO> board = dao.select();
      
      Gson gson = new Gson();
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      out.print(gson.toJson(board));

      System.out.println(time2+" === 접속");
      
   }

}