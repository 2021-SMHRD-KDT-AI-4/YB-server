package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
	
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
	   
	   public int insert(BoardDTO dto) {
		   
		   int cnt = 0;

		   conn();
		      
		   try {
			   
			   String sql = "INSERT INTO board(board_num, id, board_type, status, picture, gender, age, "
			   		+ "color, kind, weight, notice, shelter, city, place, tel, content) "
			   		+ "SELECT BOARD_NUM_SEQ.nextval, 'root', 3, 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? "
			   		+ "FROM DUAL "
			   		+ "WHERE NOT EXISTS (SELECT picture FROM board WHERE picture = ?)";
			  
		       psmt = conn.prepareStatement(sql);
		         
		       psmt.setString(1, dto.getPicture());
		       psmt.setString(2, dto.getGender());
		       psmt.setInt(3, dto.getAge());
		       psmt.setString(4, dto.getColor());
		       psmt.setString(5, dto.getKind());
		       psmt.setFloat(6, dto.getWeight());
		       psmt.setString(7, dto.getNotice());
		       psmt.setString(8, dto.getShelter());
		       psmt.setInt(9, dto.getCity());
		       psmt.setString(10, dto.getPlace());
		       psmt.setString(11, dto.getTel());
		       psmt.setString(12, dto.getContent());
		       psmt.setString(13, dto.getPicture());
		       
		       
		       cnt = psmt.executeUpdate();
		       
		   } catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return cnt;
		      
	   }
	   public int Finsert(BoardDTO dto) {
		   
		   int cnt = 0;

		   conn();
		      
		   try {
			   
			   String sql = "INSERT INTO board(board_num, id, board_type, status, picture, gender, age, "
			   		+ "color, kind, weight,MISSING_DATE,MISSING_TIME, city, place, tel, content) "
					   + "VALUES(BOARD_NUM_SEQ.nextval,?,?,0,?,?,?,?,?,?,?,?,?,?,?,?)";
			   		
			  
		       psmt = conn.prepareStatement(sql);
		         
		       psmt.setString(1, dto.getId());
		       psmt.setInt(2, dto.getBoard_type());
		       psmt.setString(3, dto.getPicture());
		       psmt.setString(4, dto.getGender());
		       psmt.setInt(5, dto.getAge());
		       psmt.setString(6, dto.getColor());
		       psmt.setString(7, dto.getKind());
		       psmt.setFloat(8, dto.getWeight());
		       psmt.setString(9, dto.getMissing_date());
		       psmt.setString(10, dto.getMissing_time());
		       psmt.setInt(11, dto.getCity());
		       psmt.setString(12, dto.getPlace());
		       psmt.setString(13, dto.getTel());
		       psmt.setString(14, dto.getContent());
		       
		       
		       
		       cnt = psmt.executeUpdate();
		       
		   } catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return cnt;
		      
	   }
	   
	   public ArrayList<F_BoardDTO> Fselect(BoardDTO dto) {
		   ArrayList<F_BoardDTO> pictureList = new ArrayList<F_BoardDTO>();
		   F_BoardDTO fdto;
		   conn();
		   
		   try {
			   String sql = "SELECT picture FROM board where city=? and kind =? ";
			   
			   psmt = conn.prepareStatement(sql);
			   psmt.setInt(1, dto.getCity());
		       psmt.setString(2, dto.getKind());

			   rs = psmt.executeQuery();
			   
			   while(rs.next()) {
				
					String picture = rs.getString("picture");
					fdto = new F_BoardDTO(picture);
					pictureList.add(fdto);
				}
				
		   }catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return pictureList;
		   
	}
	   
	   
	   public ArrayList<BoardDTO> select(){
		   
		   ArrayList<BoardDTO> board = new ArrayList<BoardDTO>();
		   
		   conn();
		   
		   try {
			   String sql = "SELECT board_num, id, board_type, status, picture, gender, NVL(age,0) AS age, "
					   	+ "color, kind, NVL(missing_date,'NULL') AS missing_date, NVL(missing_time,'NULL') AS missing_time,"
					   	+ "NVL(weight,0) AS weight, NVL(notice,'NULL') AS notice, NVL(shelter,'NULL') AS shelter, city, place, tel, content "
					   	+ "FROM board";
			   
			   psmt = conn.prepareStatement(sql);

			   rs = psmt.executeQuery();
			   
			   while(rs.next()) {
				
				    int board_num = rs.getInt("board_num");
				    String id = rs.getString("id");
				    int board_type = rs.getInt("board_type");
				    int status = rs.getInt("status");
					String picture = rs.getString("picture");
					String gender = rs.getString("gender");
					int age = rs.getInt("age");
					String color = rs.getString("color");
					String kind = rs.getString("kind");
					float weight = rs.getFloat("weight");
					String missing_date = rs.getString("missing_date");
					String missing_time = rs.getString("missing_time");
					String notice = rs.getString("notice");
					String shelter = rs.getString("shelter");
					int city = rs.getInt("city");
					String place = rs.getString("place");
					String tel = rs.getString("tel");
					String content = rs.getString("content");
					
					BoardDTO dto = new BoardDTO(board_num, id, board_type, status, picture, gender, age, color, kind, weight, missing_date, missing_time, notice, shelter, city, place, tel, content);
					
					board.add(dto);
				}
				
		   }catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
		       close();
		   }
		      
		   return board;
	   }

	public ArrayList<BoardDTO> pictureresult(String id) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		conn();
		String sql = "SELECT picture, gender, kind" + 
				"FROM BOARD" + 
				"WHERE board_num IN (SELECT board_num2 FROM matching_result WHERE id = ? )";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,id);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				String picture = rs.getString(1);
				String gender = rs.getString(2);
				String kind = rs.getString(3);
				BoardDTO dto = new BoardDTO(picture, gender, kind);
				
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
