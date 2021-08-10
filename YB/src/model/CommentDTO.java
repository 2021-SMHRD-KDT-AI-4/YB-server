package model;

public class CommentDTO {
	private int comments_num;
	private int board_num;
	private String id;
	private String comments;
	
	
	
	public CommentDTO(int board_num, String id, String comments) {
		super();
		this.board_num = board_num;
		this.id = id;
		this.comments = comments;
		
	}
	public CommentDTO(int comments_num, int board_num, String id, String comments) {
		super();
		this.comments_num = comments_num;
		this.board_num = board_num;
		this.id = id;
		this.comments = comments;
	}
	public int getComments_num() {
		return comments_num;
	}
	public void setComments_num(int comments_num) {
		this.comments_num = comments_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
