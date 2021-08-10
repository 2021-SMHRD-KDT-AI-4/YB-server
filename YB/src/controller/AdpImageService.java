package controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdpImageService")
public class AdpImageService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("image/jpg");
		String filename = request.getParameter("filename");
		
		// 1024바이트만큼 이미지를 불러들어와서 저장해 놓을 배열 생성
		byte[] by = new byte[512];
		
		// 웹페이지에 이미지를 출력 해줄 OutputStream 객체
		ServletOutputStream out = response.getOutputStream();
		
		// 이미지의 경로 가져오기
		String imgPath = getServletContext().getRealPath("")+"AdpPic\\"+filename; // 현재 서블릿의 위치
		
		
		// inputStream을 이용하여 이미지를 읽어오기
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(imgPath));
		// 읽어들일 데이터가 없으면 -1을 반환
		// 있으면 1을 반환
		while(in.read(by) != -1){
			out.write(by);
		}
		out.close();
		in.close();
		
	}

}
