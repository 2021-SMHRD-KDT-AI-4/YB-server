package controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.prism.Image;
import com.sun.xml.internal.fastinfoset.Decoder;

import model.CheckDTO;
import model.NosePrintDAO;
import model.NosePrintDTO;

@WebServlet("/NosePrintService")
public class NosePrintService extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random random = new Random();
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		CheckDTO check = null;
		NosePrintDTO printDTO = null;
		
		String id = request.getParameter("id");
		String dog_name = request.getParameter("dog_name");
		String dog_nose_print = request.getParameter("dog_picture");
		String dog_breeds = request.getParameter("dog_kind");
		String dog_gender = request.getParameter("dog_gender");
		
		System.out.println(id+" "+dog_name+" "+dog_nose_print+" "+dog_breeds+" "+dog_gender);
		
		
		
		// 안스에서 보내온 데이터 디코딩
		byte[] b_picture = Base64.decode(dog_nose_print);
		// 이미지 저장경로
		String savePath = "D:/HTMLCSS/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/YB/Nose_Print/";
		dog_nose_print = id+"_"+random.nextInt(1000)+1+".jpg";
	    String filePath = savePath+dog_nose_print;
	    // DB에 저장될 파일 이름 id + dog_name
	    if (id != null) {
	       BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath));
	       writer.write(b_picture);
	       writer.flush();
	       writer.close();
	    } 

		NosePrintDTO dto = new NosePrintDTO(id, dog_name, dog_nose_print, dog_breeds, dog_gender);
		NosePrintDAO dao = new NosePrintDAO();
		int cnt = dao.insert(dto);
		if (cnt > 0 ) {
			System.out.println("비문등록 성공");
			printDTO = new NosePrintDTO(dog_nose_print);
			
		}else {
			System.out.println("비문등록 실패");
			printDTO = new NosePrintDTO("없음");
		}
		out.print(gson.toJson(printDTO));
		
		
	}
	
}
