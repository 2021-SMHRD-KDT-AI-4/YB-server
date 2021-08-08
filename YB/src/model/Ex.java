package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Ex {

	public static void main(String args[]){
        String apiURL = "http://api.vworld.kr/req/address";
                 
        try{
              int responseCode = 0;
              URL url = new URL(apiURL);
              HttpURLConnection con = (HttpURLConnection)url.openConnection();
              con.setRequestMethod("POST");
 
              String keyword = "����Ư���� ������ �ź���� 31 (��ϵ�) �����÷������� ����1��";
              String newAddr = "";
              for (int i = 0; i < 4; i++) {
          		newAddr += keyword.split(" ")[i];
          		newAddr += " ";
			  }
              
              String text_content =  URLEncoder.encode(newAddr.toString(), "utf-8");
              //String text_content =  URLEncoder.encode(keyword.toString());
               
              // post request
              String postParams = "service=address";
                     postParams += "&request=getcoord";                     
                     postParams += "&version=2.0";
                     postParams += "&crs=EPSG:4326";
                     postParams += "&address="+text_content;                                    
                     postParams += "&arefine=true";
                     postParams += "&simple=false";                  
                     postParams += "&format=json";
                     postParams += "&type=road";    
                     postParams += "&errorFormat=json";
                     postParams += "&key=FC9D3ABD-B77A-3BC6-931F-6E47A95B6C30";                    
 
              con.setDoOutput(true);
              DataOutputStream wr = new DataOutputStream(con.getOutputStream());
              wr.writeBytes(postParams);
              wr.flush();
              wr.close();
              responseCode = con.getResponseCode();
              BufferedReader br;
               
              if(responseCode==200) { // ���� ȣ��
                  br = new BufferedReader(new InputStreamReader(con.getInputStream()));
              }else{  // ���� �߻�
                  br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
              }
 
              String inputLine;
              StringBuffer response = new StringBuffer();
 
              while ((inputLine = br.readLine()) != null) {
         
                  response.append(inputLine);
              }
             
              String result = response.toString();
              
              try{
            	  
            	  String[] resultList = result.split("\"x\" : \"")[1].split("\", \"y\" : \"");
            	  String x = resultList[0];
            	  String y = resultList[1].split("\"}}}}")[0];
            	  
            	  
            	  System.out.println(x);
            	  System.out.println(y);
            	  
            	  
              }catch (Exception e) {
//            	int adrlen = keyword.split(" ").length;
            	
//            	String newAddr = "";
//            	
//            	for (int i = 0; i < 4; i++) {
//            		newAddr += keyword.split(" ")[i];
//            		newAddr += " ";
//				}
              }finally {            	  
            	  br.close();
            	  con.disconnect();
            	  
              }
          }catch(Exception e){          
              e.printStackTrace();
          }
}

	
}
    
	
//	public static Float[] geoCoding(String location) {
//
//		if (location == null) {  
//			return null;
//		}
//				      
//		Geocoder geocoder = new Geocoder();
//
//		// setAddress : ��ȯ�Ϸ��� �ּ� (��⵵ ������ �д籸 ��)
//		// setLanguate : ���ڵ� ����
//
//		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();
//		GeocodeResponse geocoderResponse;
//
//		try {
//
//			geocoderResponse = geocoder.geocode(geocoderRequest);
//
//			if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {
//				GeocoderResult geocoderResult=geocoderResponse.getResults().iterator().next();
//				LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();
//				
//				Float[] coords = new Float[2];
//				
//				coords[0] = latitudeLongitude.getLat().floatValue();				
//				coords[1] = latitudeLongitude.getLng().floatValue();
//
//				return coords;		
//		}
//			
//		} catch (IOException ex) {
//			ex.printStackTrace();	
//		}
//			return null;
//			
//	}
			



