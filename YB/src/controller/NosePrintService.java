package controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.prism.Image;
import com.sun.xml.internal.fastinfoset.Decoder;

import model.NosePrintDAO;
import model.NosePrintDTO;

@WebServlet("/NosePrintService")
public class NosePrintService extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		CheckDTO check = null;
		
		
		String dog_name = request.getParameter("dog_name");
		String gender = request.getParameter("dog_gender");
		String kind = request.getParameter("dog_kind");
		String id = request.getParameter("id");
		String picture = request.getParameter("dog_picture");
		
		// 안스에서 보내온 데이터 디코딩
		byte[] b_picture = Base64.decode(picture);
		// 이미지 저장경로
		String savePath = "D:/HTMLCSS/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/YB/Nose_Print/";
	    String filePath = savePath+id+dog_name+".jpg";
	    // DB에 저장될 파일 이름 id + dog_name
	    String filename = id+dog_name+".jpg";
	    if (id != null) {
	       BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath));
	       writer.write(b_picture);
	       writer.flush();
	       writer.close();
	    } 
		
		System.out.println(b_picture);
		System.out.println(dog_name);
		System.out.println(gender);
		System.out.println(kind);
		System.out.println(id);
//		System.out.println(picture);
		NosePrintDTO dto = new NosePrintDTO(id, dog_name, gender, kind, filename);
		NosePrintDAO dao = new NosePrintDAO();
		int cnt = dao.insert(dto);
		if (cnt > 0) {
			System.out.println("비문등록 성공");
			check = new CheckDTO(true);
			
		}else {
			System.out.println("비문등록 실패");
			check = new CheckDTO(false);
		}
		out.print(gson.toJson(check));
		
	
	}
	
	

}
