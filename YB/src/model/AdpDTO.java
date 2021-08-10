package model;

public class AdpDTO {

	private int adp_num;
	private String adp_status;
	private String adp_picture;
	private String adp_gender;
	private int adp_age;	
	private String adp_color;
	private String adp_kind;
	private float adp_weight;
	private String adp_shelter;
	private String adp_addr;
	private String adp_neuter;
	private String adp_tel;
	private String adp_content;
	
	public AdpDTO(int adp_num, String adp_status, String adp_picture, String adp_gender, int adp_age, String adp_color,
			String adp_kind, float adp_weight, String adp_shelter, String adp_addr, String adp_neuter, String adp_tel,
			String adp_content) {
		super();
		this.adp_num = adp_num;
		this.adp_status = adp_status;
		this.adp_picture = adp_picture;
		this.adp_gender = adp_gender;
		this.adp_age = adp_age;
		this.adp_color = adp_color;
		this.adp_kind = adp_kind;
		this.adp_weight = adp_weight;
		this.adp_shelter = adp_shelter;
		this.adp_addr = adp_addr;
		this.adp_neuter = adp_neuter;
		this.adp_tel = adp_tel;
		this.adp_content = adp_content;
	}

	public int getAdp_num() {
		return adp_num;
	}

	public void setAdp_num(int adp_num) {
		this.adp_num = adp_num;
	}

	public String getAdp_status() {
		return adp_status;
	}

	public void setAdp_status(String adp_status) {
		this.adp_status = adp_status;
	}

	public AdpDTO(String adp_picture, String adp_gender, int adp_age, String adp_color, String adp_kind,
			float adp_weight, String adp_shelter, String adp_addr, String adp_neuter, String adp_tel,
			String adp_content) {
		super();
		this.adp_picture = adp_picture;
		this.adp_gender = adp_gender;
		this.adp_age = adp_age;
		this.adp_color = adp_color;
		this.adp_kind = adp_kind;
		this.adp_weight = adp_weight;
		this.adp_shelter = adp_shelter;
		this.adp_addr = adp_addr;
		this.adp_neuter = adp_neuter;
		this.adp_tel = adp_tel;
		this.adp_content = adp_content;
	}
	
	public String getAdp_picture() {
		return adp_picture;
	}
	public void setAdp_picture(String adp_picture) {
		this.adp_picture = adp_picture;
	}
	public String getAdp_gender() {
		return adp_gender;
	}
	public void setAdp_gender(String adp_gender) {
		this.adp_gender = adp_gender;
	}
	public int getAdp_age() {
		return adp_age;
	}
	public void setAdp_age(int adp_age) {
		this.adp_age = adp_age;
	}
	public String getAdp_color() {
		return adp_color;
	}
	public void setAdp_color(String adp_color) {
		this.adp_color = adp_color;
	}
	public String getAdp_kind() {
		return adp_kind;
	}
	public void setAdp_kind(String adp_kind) {
		this.adp_kind = adp_kind;
	}
	public float getAdp_weight() {
		return adp_weight;
	}
	public void setAdp_weight(float adp_weight) {
		this.adp_weight = adp_weight;
	}
	public String getAdp_shelter() {
		return adp_shelter;
	}
	public void setAdp_shelter(String adp_shelter) {
		this.adp_shelter = adp_shelter;
	}
	public String getAdp_addr() {
		return adp_addr;
	}
	public void setAdp_addr(String adp_addr) {
		this.adp_addr = adp_addr;
	}
	public String getAdp_neuter() {
		return adp_neuter;
	}
	public void setAdp_neuter(String adp_neuter) {
		this.adp_neuter = adp_neuter;
	}
	public String getAdp_tel() {
		return adp_tel;
	}
	public void setAdp_tel(String adp_tel) {
		this.adp_tel = adp_tel;
	}
	public String getAdp_content() {
		return adp_content;
	}
	public void setAdp_content(String adp_content) {
		this.adp_content = adp_content;
	}
	
	
		
	
	
}
