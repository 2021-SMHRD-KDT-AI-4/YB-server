package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.BoardDAO;
import model.BoardDTO;
import model.F_BoardDAO;
import model.F_BoardDTO;

@WebServlet("/F_Dog_Upload")
public class F_Dog_Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		String data = request.getParameter("dog_json");
		String test = request.getParameter("testdata");
		System.out.println(test);
		System.out.println(data);
		String picture = "";
		String city ="";
		String kind = "";
		BoardDAO dao = new BoardDAO();
		F_BoardDAO fdao = new F_BoardDAO();
		int picListsize = 0;
		ArrayList<F_BoardDTO> picList = new ArrayList<F_BoardDTO>();
		
		if(data != null) {
			JsonParser jsonParser = new JsonParser();
			Object object = jsonParser.parse(data);
			JsonObject jsonobj = (JsonObject)object;
			String id = jsonobj.get("id").getAsString();
			String board_type = jsonobj.get("type").getAsString();
			String age = jsonobj.get("age").getAsString();
			city = jsonobj.get("city").getAsString();
			String color = jsonobj.get("color").getAsString();
			String content = jsonobj.get("content").getAsString();
			String gender = jsonobj.get("gender").getAsString();
			kind = jsonobj.get("kind").getAsString();
			picture = jsonobj.get("picture").getAsString();
			String day = jsonobj.get("date").getAsString();
			String place = jsonobj.get("place").getAsString();
			String tel = jsonobj.get("tel").getAsString();
			String time = jsonobj.get("time").getAsString();
			String weight = jsonobj.get("weight").getAsString();
			
			System.out.println(id+"/"+board_type+"/"+age+"/"+city+"/"+color+"/"+content+"/"+gender+"/"+kind+"/"+picture+"/"+day+"/"+place+"/"+tel+"/"+time+"/"+weight);
			
			BoardDTO dto = new BoardDTO(id, Integer.parseInt(board_type), picture, gender,
					Integer.parseInt(age), color, kind,(float)Integer.parseInt(weight), day, time, Integer.parseInt(city), 
					place, tel, content);
			
			
			int cnt = dao.Finsert(dto);
			
			
			if (cnt >0) {
				System.out.println("등록성공");
				
			}else {
				System.out.println("등록실패");
			}
			
			if (board_type.equals("1")) {
				picList = fdao.num1select(dto);
				
			}else if(board_type.equals("2")) {
				picList = fdao.num2select(dto);
			}
			
		}
		System.out.println("@@@@@@@");
		System.out.println(gson.toJson(picList));
		
		PrintWriter out = response.getWriter();
		
		out.print(gson.toJson(picList));
		
	}

}

