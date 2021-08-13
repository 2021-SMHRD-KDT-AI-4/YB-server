package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class F_BoardDAO {
	
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
	   
	   public ArrayList<F_BoardDTO> myselect(BoardDTO dto) {
		   ArrayList<F_BoardDTO> pictureList = new ArrayList<F_BoardDTO>();
		   F_BoardDTO fdto;
		   conn();
		   
		   try {
			   String sql = "SELECT board_num FROM board where city=? and kind =? and board_type= 1";
			   
			   psmt = conn.prepareStatement(sql);
			   psmt.setInt(1, dto.getCity());
		       psmt.setString(2, dto.getKind());

			   rs = psmt.executeQuery();
			   
			   while(rs.next()) {
				
				   int board_num = rs.getInt("board_num");
					String picture = rs.getString("picture");
					
					fdto = new F_BoardDTO(board_num,picture);
					pictureList.add(fdto);
				}
				
		   }catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return pictureList;
		   
	}
	   public ArrayList<F_BoardDTO> num2select(BoardDTO dto) {
		   ArrayList<F_BoardDTO> pictureList = new ArrayList<F_BoardDTO>();
		   F_BoardDTO fdto;
		   conn();
		   
		   try {
			   String sql = "SELECT board_num,picture FROM board where board_num IN " + 
			   		"(SELECT board_num FROM board WHERE city=? and kind =? and board_type= 1 " + 
			   		"UNION " + 
			   		"SELECT board_num FROM board WHERE picture = ?) ";
			   
			   psmt = conn.prepareStatement(sql);
			   psmt.setInt(1, dto.getCity());
		       psmt.setString(2, dto.getKind());
		       psmt.setString(3, dto.getPicture());
		       
			   rs = psmt.executeQuery();
			   
			   while(rs.next()) {
				
				   int board_num = rs.getInt("board_num");
					String picture = rs.getString("picture");
					
					fdto = new F_BoardDTO(board_num,picture);
					pictureList.add(fdto);
				}
				
		   }catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return pictureList;
		   
	}
	   public ArrayList<F_BoardDTO> num1select(BoardDTO dto) {
		   ArrayList<F_BoardDTO> pictureList = new ArrayList<F_BoardDTO>();
		   F_BoardDTO fdto;
		   conn();
		   
		   try {
			   String sql = "SELECT board_num,picture FROM board where board_num IN " + 
				   		"(SELECT board_num FROM board WHERE city = ? and kind = ? and board_type != ? " + 
				   		"UNION " + 
				   		"SELECT board_num FROM board WHERE picture = ?) ";
			   
			   psmt = conn.prepareStatement(sql);
			   psmt.setInt(1, dto.getCity());
		       psmt.setString(2, dto.getKind());
		       psmt.setInt(3, dto.getBoard_type());
		       psmt.setString(4, dto.getPicture());

			   rs = psmt.executeQuery();
			   
			   while(rs.next()) {
				
				   int board_num = rs.getInt("board_num");
					String picture = rs.getString("picture");
					
					fdto = new F_BoardDTO(board_num,picture);
					pictureList.add(fdto);
				}
				
		   }catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return pictureList;
		   
	}
}
