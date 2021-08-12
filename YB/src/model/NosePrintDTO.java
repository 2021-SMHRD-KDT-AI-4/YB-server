package model;

public class NosePrintDTO {
	private int nose_print_num;
	private String id;
	private String dog_name;
	private String dog_nose_print;
	private String dog_breeds;
	private String dog_gender;
	
	public NosePrintDTO(String dog_nose_print) {
		super();
		this.dog_nose_print = dog_nose_print;
	}

	public NosePrintDTO(int nose_print_num, String id, String dog_name, String dog_nose_print, String dog_breeds,
			String dog_gender) {
		super();
		this.nose_print_num = nose_print_num;
		this.id = id;
		this.dog_name = dog_name;
		this.dog_nose_print = dog_nose_print;
		this.dog_breeds = dog_breeds;
		this.dog_gender = dog_gender;
	}
	
	
	
	public NosePrintDTO(String id, String dog_name, String dog_nose_print, String dog_breeds, String dog_gender) {
		super();
		this.id = id;
		this.dog_name = dog_name;
		this.dog_nose_print = dog_nose_print;
		this.dog_breeds = dog_breeds;
		this.dog_gender = dog_gender;
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
	public String getDog_nose_print() {
		return dog_nose_print;
	}
	public void setDog_nose_print(String dog_nose_print) {
		this.dog_nose_print = dog_nose_print;
	}
	public String getDog_breeds() {
		return dog_breeds;
	}
	public void setDog_breeds(String dog_breeds) {
		this.dog_breeds = dog_breeds;
	}
	public String getDog_gender() {
		return dog_gender;
	}
	public void setDog_gender(String dog_gender) {
		this.dog_gender = dog_gender;
	}

	
	
	
}
