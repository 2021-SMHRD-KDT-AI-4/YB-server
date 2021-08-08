package view;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.ShelterDAO;
import model.ShelterDTO;

public class ShelterMain {

	
	public static void main(String[] args) {
		
		String key = "Rl1g9693Oz75UWdCKpSsKZzfz0wqfrARsAic0d1kDdbDGEMVxnOXhs%2BLTXV4m5bp4Lh%2Bzv9FWU3I6dQJMcXCpw%3D%3D";
		String urlstr = "http://openapi.animal.go.kr/openapi/service/rest/animalShelterSrvc/shelterInfo?serviceKey=" + key +
				"&numOfRows=";
		
		// 보호소 개수 꺼내기
		String total_cnt = "1"; 
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr+total_cnt);
			doc.getDocumentElement().normalize();
			
			NodeList itemList = doc.getElementsByTagName("body");
			
			Node nNode =  itemList.item(0);
				
			Element element = (Element) nNode;
			total_cnt = getTagValue("totalCount",element);
			System.out.println(total_cnt);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// 보호소 정보 저장하기
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr+total_cnt);
//			Document doc = dBuilder.parse(urlstr+total_cnt);
			doc.getDocumentElement().normalize();
			
			NodeList itemList = doc.getElementsByTagName("item");
			
			for (int i = 0; i < itemList.getLength(); i++) {
				
				Node nNode =  itemList.item(i);
				
				String shelter_name = "";
				String shelter_addr = "";
				float shelter_lat = 0f;
				float shelter_lng = 0f;

				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element element = (Element) nNode;
					
					shelter_name = getTagValue("careNm",element);
					shelter_addr = getTagValue("careAddr",element);
					
					
					// api를 통해 lat,lng 가져오기

					float[] result = getLatLngValue(shelter_addr);
					shelter_lat = result[0];
					shelter_lng = result[1];
					
					if(shelter_lat == 0f && shelter_lng == 0f) {
//					 	int adrlen = shelter_addr.split(" ").length;
		            	
		            	String newAddr = "";
		            	
		            	for (int j = 0; j < 4; j++) {
		            		newAddr += shelter_addr.split(" ")[j];
		            		newAddr += " ";
						}
		            	
		            	System.out.println(newAddr);
		            	
		            	float[] newResult = getLatLngValue(newAddr);
		            	
		            	shelter_lat = newResult[0];
		            	shelter_lng = newResult[1];
		            	
						System.out.println(shelter_lat+" "+shelter_lng);
		            	
					}
				
				}
				
				ShelterDTO dto = new ShelterDTO(shelter_name, shelter_addr, shelter_lat, shelter_lng);
				ShelterDAO dao = new ShelterDAO();
				
				int cnt = dao.insert(dto);
				
				if(cnt>0) {
					System.out.println("성공");
				}else {
					System.out.println("중복 데이터 발생!");
				}
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	private static String getTagValue(String tag, Element element) {
		NodeList nList = element.getElementsByTagName(tag).item(0).getChildNodes();
		
		Node nValue = (Node) nList.item(0);
		if(nValue == null) {
			return null;
		}
		
		return nValue.getNodeValue();
	}
	
	private static float[] getLatLngValue(String addr) {
		
		float[] geo = {0f,0f};
		
        String apiURL = "http://api.vworld.kr/req/address";
        
        try{
              int responseCode = 0;
              URL url = new URL(apiURL);
              HttpURLConnection con = (HttpURLConnection)url.openConnection();
              con.setRequestMethod("POST");

              String text_content =  URLEncoder.encode(addr.toString(), "utf-8");
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
               
              if(responseCode==200) { // 정상 호출
                  br = new BufferedReader(new InputStreamReader(con.getInputStream()));
              }else{  // 에러 발생
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
            	  
            	  geo[0] = Float.parseFloat(x);
            	  geo[1] = Float.parseFloat(y);
            	  
              }catch (Exception e) {
            	  
            	  
				System.out.println("주소 검색결과 없음");
              }finally {            	  
            	  br.close();
            	  con.disconnect();
            	
              }
          }catch(Exception e){          
              e.printStackTrace();
          }
		
        return geo;
		
	}
	
	
	

}
