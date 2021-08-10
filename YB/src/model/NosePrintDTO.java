package model;

public class NosePrintDTO {
	private int nose_print_num;
	private String id;
	private String dog_name;
	private String gender;
	private String kind;
	private String picture;
	
	
	
	
	public NosePrintDTO(String id, String dog_name, String gender, String kind, String picture) {
		super();
		this.id = id;
		this.dog_name = dog_name;
		this.gender = gender;
		this.kind = kind;
		this.picture = picture;
	}
	public NosePrintDTO(int nose_print_num, String id, String dog_name, String gender, String kind, String picture) {
		super();
		this.nose_print_num = nose_print_num;
		this.id = id;
		this.dog_name = dog_name;
		this.gender = gender;
		this.kind = kind;
		this.picture = picture;
	}
	public int getNose_print_num() {
		return nose_print_num;
	}
	public void setNose_print_num(int nose_print_num) {
		this.nose_print_num = nose_print_num;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDog_name() {
		return dog_name;
	}
	public void setDog_name(String dog_name) {
		this.dog_name = dog_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
