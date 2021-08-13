package model;

public class F_BoardDTO {
	private String picture;
	private int board_num;
	
	public F_BoardDTO(int board_num, String picture) {
		super();
		this.board_num = board_num;
		this.picture = picture;
		
	}


	public F_BoardDTO(String picture) {
		this.picture = picture;
	}
	

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	
	
	
}
