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

import model.DistDTO;
import model.ShelterDAO;
import model.ShelterDTO;


@WebServlet("/ShelterCloseService")
public class ShelterCloseService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double lat = Double.parseDouble(request.getParameter("lattitude"));
		double lng = Double.parseDouble(request.getParameter("longtitude"));

		System.out.println("ÇöÀ§Ä¡ : "+lat +" "+lng);
		ShelterDAO dao = new ShelterDAO();
		ArrayList<ShelterDTO> list = dao.select();
		
		double[] distLsit = new double[list.size()];
		
		
		for (int i = 0; i < list.size(); i++) {
			
			float sLat = list.get(i).getShelter_lat();
			float sLng = list.get(i).getShelter_lng();
			
			double dist = distance(lat, lng, sLat, sLng);
			
			distLsit[i] = dist;
			
			
		}
		
		double min = distLsit[0];
		int min_cnt = 0;
		
		for(int j=0; j<distLsit.length; j++) {
					
		    if(min>distLsit[j]) {
		    	min = distLsit[j];
		    	min_cnt = j;
		    }
		}
		
		System.out.println(list.get(min_cnt).getShelter_name()+" "+ list.get(min_cnt).getShelter_addr()+" "+ min);
		DistDTO dto = new DistDTO(list.get(min_cnt).getShelter_name(), list.get(min_cnt).getShelter_addr(), min);
		
		ArrayList<DistDTO> result = new ArrayList<DistDTO>();
		result.add(dto);
		
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(result));

		
	
		
	}
	
	private static double distance(double lat1, double lon1, float sLat, float sLng) {

		
		double lat2 = (double) sLat;
		double lon2 = (double) sLng;
		
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
          
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
          
        dist = dist * 1.609344;
        
        return (dist);
    }
      

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
	

}
