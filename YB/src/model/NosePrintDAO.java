package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	   
}
