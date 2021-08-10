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
		
		// 1024����Ʈ��ŭ �̹����� �ҷ����ͼ� ������ ���� �迭 ����
		byte[] by = new byte[512];
		
		// ���������� �̹����� ��� ���� OutputStream ��ü
		ServletOutputStream out = response.getOutputStream();
		
		// �̹����� ��� ��������
		String imgPath = getServletContext().getRealPath("")+"AdpPic\\"+filename; // ���� ������ ��ġ
		
		
		// inputStream�� �̿��Ͽ� �̹����� �о����
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(imgPath));
		// �о���� �����Ͱ� ������ -1�� ��ȯ
		// ������ 1�� ��ȯ
		while(in.read(by) != -1){
			out.write(by);
		}
		out.close();
		in.close();
		
	}

}
