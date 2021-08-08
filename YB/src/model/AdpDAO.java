package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdpDAO {


	
	
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
	   
	   public int insert(AdpDTO dto) {
		   
		   int cnt = 0;

		   conn();
		   
		   try {
			   String sql = "INSERT INTO adoption "
			   		+ "SELECT ADOPTION_ADP_NUM_SEQ.nextval, 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? "
			   		+ "FROM DUAL "
			   		+ "WHERE NOT EXISTS (SELECT adp_picture FROM adoption WHERE adp_picture = ?)";
			   
			   psmt = conn.prepareStatement(sql);
			   psmt.setString(1, dto.getAdp_picture());
			   psmt.setString(2, dto.getAdp_gender());
			   psmt.setInt(3, dto.getAdp_age());
			   psmt.setString(4, dto.getAdp_color());
			   psmt.setString(5, dto.getAdp_kind());
			   psmt.setFloat(6, dto.getAdp_weight());
			   psmt.setString(7, dto.getAdp_shelter());
			   psmt.setString(8, dto.getAdp_addr());
			   psmt.setString(9, dto.getAdp_neuter());
			   psmt.setString(10, dto.getAdp_tel());
			   psmt.setString(11, dto.getAdp_content());
			   psmt.setString(12, dto.getAdp_picture());
			   
			   cnt = psmt.executeUpdate();
			   
		   }catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return cnt;
	   }
}
