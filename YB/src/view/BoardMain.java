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

import model.BoardDAO;
import model.BoardDTO;

public class BoardMain {

	public static void main(String[] args) {
		
		SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMdd");
	    Calendar calendar = Calendar.getInstance();

	    Date dateObj = calendar.getTime();
	        
	    String today = dtf.format(dateObj);

		String key = "Rl1g9693Oz75UWdCKpSsKZzfz0wqfrARsAic0d1kDdbDGEMVxnOXhs%2BLTXV4m5bp4Lh%2Bzv9FWU3I6dQJMcXCpw%3D%3D"; 
		String urlstr = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?serviceKey=" + key 
						+ "&bgnde="+today+"&endde="+today+"&upkind=417000&state=notice&pageNo=1&numOfRows=100";

		System.out.println();
			
		try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(urlstr);
				doc.getDocumentElement().normalize();
			
				NodeList itemList = doc.getElementsByTagName("item");
				
				for (int i = 0; i < itemList.getLength(); i++) {
						
					Node nNode =  itemList.item(i);
					
					String picture_raw = "";
					String picture = "";
						
					String gender = "";
					String age_raw = "";
					int age = 0;
					String color = "";					
					String kind_raw = "";
					String kind = "";
					String weight_raw = "";
					float weight = 0;
					String notice = "";		
					String shelter = "";
					int city = 0;
					String place = "";
					String tel = "";
					String content = "";
					
					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						
						Element element = (Element) nNode;
						 
						picture_raw = getTagValue("popfile",element);
						picture = getFileName(picture_raw);
						
						gender = getTagValue("sexCd",element);
						
						if(gender.equals("M")) {
							gender = "수컷";
						}else if(gender.equals("F")) {
							gender = "암컷";
						}else {
							gender = "알수없음";
						}
						
						
						age_raw = getTagValue("age",element);
						age = getSplitInt(age_raw);
						color = getTagValue("colorCd",element);
						
						kind_raw = getTagValue("kindCd",element);
						kind = getSplitValue(kind_raw);
						
						weight_raw = getTagValue("weight",element);
						weight = getSplitFloat(weight_raw);
						
						notice = getTagValue("noticeSdt",element)+"~";
						notice += getTagValue("noticeEdt",element);
						
						shelter = getTagValue("careNm",element);
						place = getTagValue("careAddr",element);
						city = getCityVAlue(place.split(" ")[0]);
								
						tel = getTagValue("careTel",element);
						content = getTagValue("specialMark",element);
					
					}
					
					
					BoardDTO dto = new BoardDTO(picture, gender, age, color, kind, weight, notice, shelter, city, place, tel, content);
					BoardDAO dao = new BoardDAO();
					int cnt = dao.insert(dto);
					
					if(cnt>0) {
						System.out.println("새로운 데이터 insert === "+ gender+" "+age+" "+color+" "+kind+" "+weight+" "+notice+" "+shelter+" "+place+" "+tel+" "+content);
					}else {
						System.out.println("중복 데이터 발생!");
					}
					
				}
			}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static String getFileName(String str) {
		
		String file_name = "";
		String file_path = "D:\\HTMLCSS\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\YB\\BoardPic\\";
				
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
			String[] kindArray = str.split("] ");
			
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
	
	private static int getCityVAlue(String str) {
		
		int cnt = 0;
		if(str.equals("서울특별시")) {
			cnt = 1;
		}else if(str.equals("부산광역시")) {
			cnt = 2;
		}else if(str.equals("대구광역시")) {
			cnt = 3;
		}else if(str.equals("인천광역시")) {
			cnt = 4;
		}else if(str.equals("광주광역시")) {
			cnt = 5;
		}else if(str.equals("세종특별자치시")) {
			cnt = 6;
		}else if(str.equals("대전광역시")) {
			cnt = 7;
		}else if(str.equals("울산광역시")) {
			cnt = 8;
		}else if(str.equals("경기도")) {
			cnt = 9;
		}else if(str.equals("강원도")) {
			cnt = 10;
		}else if(str.equals("충청남도")) {
			cnt = 11;
		}else if(str.equals("충청북도")) {
			cnt = 12;
		}else if(str.equals("전라남도")) {
			cnt = 13;
		}else if(str.equals("전라북도")) {
			cnt = 14;
		}else if(str.equals("경상남도")) {
			cnt = 15;
		}else if(str.equals("경상북도")) {
			cnt = 16;
		}else if(str.equals("제주특별자치도")) {
			cnt = 17;
		}
		
		return cnt;
		
	}
}
