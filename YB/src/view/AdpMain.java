package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.AdpDAO;
import model.AdpDTO;

public class AdpMain {

	public static void main(String[] args) {
		
        SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();

        Date dateObj = calendar.getTime();
        
        String today = dtf.format(dateObj);
		
		String key = "Rl1g9693Oz75UWdCKpSsKZzfz0wqfrARsAic0d1kDdbDGEMVxnOXhs%2BLTXV4m5bp4Lh%2Bzv9FWU3I6dQJMcXCpw%3D%3D"; 
		String urlstr = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?serviceKey=" + key 
				     + "&bgnde="+"20210501"+"&endde="+today+"&upkind=417000&state=protect&pageNo=1&numOfRows=50";


		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(urlstr);
			doc.getDocumentElement().normalize();
			
			NodeList itemList = doc.getElementsByTagName("item");
			
			for (int i = 0; i < itemList.getLength(); i++) {
				
				Node nNode =  itemList.item(i);
				
				String adp_picture_raw = "";
				String adp_picture = "";
				
				String adp_gender = "";
				String adp_age_raw = "";
				int adp_age = 0;
				String adp_color = "";
				String adp_kind_raw = "";
				String adp_kind = "";
				String adp_weight_raw = "";
				float adp_weight = 0;
				String adp_shelter = "";
				String adp_addr = "";
				String adp_neuter = "";
				String adp_tel = "";
				String adp_content = "";
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element element = (Element) nNode;
					
					adp_picture_raw = getTagValue("popfile",element);
					adp_picture = getFileName(adp_picture_raw);
					
					adp_gender = getTagValue("sexCd", element);
					
					if(adp_gender.equals("M")) {
						adp_gender = "수컷";
					}else if(adp_gender.equals("F")) {
						adp_gender = "암컷";
					}else {
						adp_gender = "알수없음";
					}
					
					adp_color = getTagValue("colorCd", element);
					
					adp_kind_raw = getTagValue("kindCd", element);
					adp_kind = getSplitValue(adp_kind_raw);
					
					adp_age_raw = getTagValue("age", element);
					adp_age = getSplitInt(adp_age_raw);
					
					adp_shelter = getTagValue("careNm", element);
					adp_addr = getTagValue("careAddr", element);
					adp_neuter = getTagValue("neuterYn", element);
					if(adp_neuter.equals("Y")) {
						adp_neuter = "예";
					}else if(adp_neuter.equals("N")) {
						adp_neuter = "아니오";
					}else {
						adp_neuter = "알수없음";
					}
					
					adp_tel = getTagValue("careTel", element);
					adp_weight_raw = getTagValue("weight", element);
					adp_weight = getSplitFloat(adp_weight_raw);
					adp_content = getTagValue("specialMark", element);
				
				}
				
				AdpDTO dto = new AdpDTO(adp_picture, adp_gender, adp_age, adp_color, adp_kind, adp_weight, adp_shelter, adp_addr, adp_neuter, adp_tel, adp_content);
				AdpDAO dao = new AdpDAO();
				
				int cnt = dao.insert(dto);
				
				if(cnt>0) {
					System.out.println("새로운 데이터 insert === "+ adp_picture+" "+adp_gender+" "+adp_age+" "+adp_color+" "+adp_kind+" "+adp_weight+" "+adp_shelter+" "+adp_addr+" "+adp_neuter+" "+adp_tel+" "+adp_content);
				}else {
					System.out.println("중복 데이터 발생!");
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	private static String getFileName(String str) {
		
		String file_name = "";
		String file_path = "D:\\HTMLCSS\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\YB\\AdpPic\\";
				
		try {
			
			// 확장자
			String extension = "jpg";
			
			URL url = new URL(str);
			
			String[] list = str.split("\\/");
			file_name = list[list.length-1];
			
			int nameLen = file_name.length();
			if(file_name.substring(nameLen-7,nameLen-4).equals("[1]")) {
				file_name = file_name.substring(0,nameLen-7)+".jpg";
			}
		    file_path += file_name;
		    
		    BufferedImage image = ImageIO.read(url);

		    ImageIO.write(image, extension, new File(file_path));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
		
	}
	
	
	private static int getSplitInt(String str) {
		try {
			String[] array = str.split("\\(");
			
			int age = Integer.parseInt(array[0]);
			return age;
			
		} catch (Exception e) { // str에  띄어쓰기가 없을 경우		
			return 0;
		}
	}
	
	
	private static float getSplitFloat(String str) {
		
		try {
			String[] array = str.split("\\(");
			
			float weight = Float.parseFloat(array[0]);
			return weight;
			
		} catch (Exception e) { // str에  띄어쓰기가 없을 경우		
			return 0;
		}
		
	}
	
	
	private static String getSplitValue(String str) {
		try {
			String[] kindArray = str.split(" ");
			
			return kindArray[1];
			
		} catch (Exception e) { // str에  띄어쓰기가 없을 경우		
			return str;
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

}
