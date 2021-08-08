package model;

public class ShelterDTO {
	
	private String shelter_name;
	private String shelter_addr;
	private float shelter_lat;
	private float shelter_lng;
	
	public ShelterDTO(String shelter_name, String shelter_addr, float shelter_lat, float shelter_lng) {
		super();
		this.shelter_name = shelter_name;
		this.shelter_addr = shelter_addr;
		this.shelter_lat = shelter_lat;
		this.shelter_lng = shelter_lng;
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
	public float getShelter_lat() {
		return shelter_lat;
	}
	public void setShelter_lat(float shelter_lat) {
		this.shelter_lat = shelter_lat;
	}
	public float getShelter_lng() {
		return shelter_lng;
	}
	public void setShelter_lng(float shelter_lng) {
		this.shelter_lng = shelter_lng;
	}
	
	

}
