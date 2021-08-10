package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NosePrintDAO {

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

	public int insert(NosePrintDTO dto) {
		conn();
		int cnt =0;
		String sql = "insert into NOSE_PRINT values(NOSE_PRINT_NOSE_PRINT_NUM_SEQ.NEXTVAL,?,?,?,?,?)";
		try { 
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2,dto.getDog_name());
			psmt.setString(3, dto.getPicture());
			psmt.setString(4, dto.getGender());
			psmt.setString(5, dto.getKind());
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return cnt;
	}

	public ArrayList<NosePrintDTO> select(String id){
		ArrayList<NosePrintDTO> nose_info = new ArrayList<NosePrintDTO>();
		conn();
		String sql = "select * from NOSE_PRINT where id = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int n_num = rs.getInt(1);
				String n_id  =  rs.getString(2);
				String n_dog_name  =  rs.getString(3);
				String n_dog_nose_print  =  rs.getString(4);
				String n_dog_breeds  =  rs.getString(5);
				String n_dog_gender  =  rs.getString(6);
				
				NosePrintDTO dto = new NosePrintDTO(n_num, n_id, n_dog_name, n_dog_nose_print, n_dog_breeds, n_dog_gender);
				
				nose_info.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return nose_info;
	}


	



	
	   
}
