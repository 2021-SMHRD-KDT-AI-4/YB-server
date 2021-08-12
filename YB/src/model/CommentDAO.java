package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO {

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

	public int insert(CommentDTO dto) {
		int cnt = 0;
		conn();
		String sql = "insert into COMMENTS values (COMMENTS_COMMENTS_NUM_SEQ.nextval,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getBoard_num());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getComments());;
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}

	public ArrayList<CommentDTO> show(int board_num) {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		conn();
		String sql = "select ID, COMMENTS from COMMENTS WHERE BOARD_NUM = ? ORDER BY COMMENTS_NUM DESC ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, board_num);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String comments = rs.getString(2);
				
				CommentDTO dto = new CommentDTO(id, comments);
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
