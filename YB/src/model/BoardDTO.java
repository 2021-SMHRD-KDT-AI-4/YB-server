package model;


public class BoardDTO {
	
	private int board_num;
	private String id;
	private int board_type;
	private int status;
	private String picture;
	private String gender;
	private int age;
	private String color;
	private String kind;
	private float weight;
	private String missing_date;
	private String missing_time;
	private String notice;
	private String shelter;
	private int city;	
	private String place;
	private String tel;
	private String content;
	
	
	
	public BoardDTO(String picture, String gender, String kind) {
		super();
		this.picture = picture;
		this.gender = gender;
		this.kind = kind;
	}



	public BoardDTO(String id, int board_type, String picture, String gender, int age, String color, String kind,
			float weight, String missing_date, String missing_time, int city, String place, String tel,
			String content) {
		super();
		this.id = id;
		this.board_type = board_type;
		this.picture = picture;
		this.gender = gender;
		this.age = age;
		this.color = color;
		this.kind = kind;
		this.weight = weight;
		this.missing_date = missing_date;
		this.missing_time = missing_time;
		this.city = city;
		this.place = place;
		this.tel = tel;
		this.content = content;
	}
	


	public BoardDTO(int board_num, String id, int board_type, int status, String picture, String gender, int age,
			String color, String kind, float weight, String missing_date, String missing_time, String notice,
			String shelter, int city, String place, String tel, String content) {
		super();
		this.board_num = board_num;
		this.id = id;
		this.board_type = board_type;
		this.status = status;
		this.picture = picture;
		this.gender = gender;
		this.age = age;
		this.color = color;
		this.kind = kind;
		this.weight = weight;
		this.missing_date = missing_date;
		this.missing_time = missing_time;
		this.notice = notice;
		this.shelter = shelter;
		this.city = city;
		this.place = place;
		this.tel = tel;
		this.content = content;
	}
	
	public BoardDTO(String picture, String gender, int age, String color, String kind, float weight, String notice,
			String shelter, int city, String place, String tel, String content) {
		super();
		this.picture = picture;
		this.gender = gender;
		this.age = age;
		this.color = color;
		this.kind = kind;
		this.weight = weight;
		this.notice = notice;
		this.shelter = shelter;
		this.city = city;
		this.place = place;
		this.tel = tel;
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getShelter() {
		return shelter;
	}
	public void setShelter(String shelter) {
		this.shelter = shelter;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBoard_type() {
		return board_type;
	}

	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}

	public String getMissing_date() {
		return missing_date;
	}

	public void setMissing_date(String missing_date) {
		this.missing_date = missing_date;
	}

	public String getMissing_time() {
		return missing_time;
	}

	public void setMissing_time(String missing_time) {
		this.missing_time = missing_time;
	}
	
	


}
