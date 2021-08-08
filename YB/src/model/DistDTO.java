package model;

public class DistDTO {

	private String shelter_name;
	private String shelter_addr;
	private double shelter_dist;
	
	
	public DistDTO(String shelter_name, String shelter_addr, double shelter_dist) {
		super();
		this.shelter_name = shelter_name;
		this.shelter_addr = shelter_addr;
		this.shelter_dist = shelter_dist;
	}
	
	
	public String getShelter_name() {
		return shelter_name;
	}
	public void setShelter_name(String shelter_name) {
		this.shelter_name = shelter_name;
	}
	public String getShelter_addr() {
		return shelter_addr;
	}
	public void setShelter_addr(String shelter_addr) {
		this.shelter_addr = shelter_addr;
	}
	public double getShelter_dist() {
		return shelter_dist;
	}
	public void setShelter_dist(double shelter_dist) {
		this.shelter_dist = shelter_dist;
	}
	
	
	
}
