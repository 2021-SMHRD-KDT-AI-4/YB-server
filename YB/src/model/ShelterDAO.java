package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShelterDAO {
	
	   private Connection conn;
	   private PreparedStatement psmt;
	   private ResultSet rs;
	   
	   private void conn() {
		   
		   try {
			   
			   Class.forName("oracle.jdbc.driver.OracleDriver");
		         
		       String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		       String db_id = "hr";
		       String db_pw = "hr";
		         
		       conn = DriverManager.getConnection(db_url, db_id, db_pw); 
		       
		   } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		   } catch (SQLException e) {
		         e.printStackTrace();
		   }
		      
	   }
	   
	   private void close() {
		   
		   try {
			   if(rs!=null){
				   rs.close();
			   }
		       if(psmt!=null){
		    	   psmt.close();
		       }
		       if(conn!=null){
		           conn.close();
		       }
		   } catch (SQLException e) {
		         e.printStackTrace();
		   }
		   
	   }
	   
	   public int insert(ShelterDTO dto) {
		   
		   int cnt = 0;

		   conn();
		   
		   try {
			   String sql = "INSERT INTO shelter_info VALUES(?,?,?,?)";
			   
			   psmt = conn.prepareStatement(sql);
			   psmt.setString(1, dto.getShelter_name());
			   psmt.setString(2, dto.getShelter_addr());
			   psmt.setFloat(3, dto.getShelter_lat());
			   psmt.setFloat(4, dto.getShelter_lng());
			   
			   cnt = psmt.executeUpdate();
			   
		   }catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return cnt;
	   
	   }
	   
	   public ArrayList<ShelterDTO> select() {
		   
 		   ArrayList<ShelterDTO> list = new ArrayList<ShelterDTO>();

		   conn();
		   
		   try {
			   String sql = "SELECT * FROM shelter_info";
			   
			   psmt = conn.prepareStatement(sql);

			   rs = psmt.executeQuery();
			   
			   while(rs.next()) {
					
					String shelter_name = rs.getString("shelter_name");
					String shelter_addr = rs.getString("shelter_addr");
					float shelter_lat = rs.getFloat("shelter_lat");
					float shelter_lng = rs.getFloat("shelter_lng");
					
					
					ShelterDTO dto = new ShelterDTO(shelter_name, shelter_addr, shelter_lat, shelter_lng);
					list.add(dto);
				}
				
		   }catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return list;
		   
	   }

	public ArrayList<ShelterDTO> selectinfo(String sido, String gungu) {
		ArrayList<ShelterDTO> list = new ArrayList<ShelterDTO>();
		conn();
		String sql = "select shelter_name, shelter_addr from shelter_info where shelter_addr like '%' ||?||'%'||?||'%'";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sido);
			psmt.setString(2, gungu);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				String shelter_name = rs.getString(1);
				String shelter_addr = rs.getString(2);
				ShelterDTO dto = new ShelterDTO(shelter_name, shelter_addr);
				list.add(dto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
}
