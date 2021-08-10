package model;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private int membercode;
	private int tel;
	private String address;
	
	
	
	public MemberDTO(String id) {
		super();
		this.id = id;
	}
	public MemberDTO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	public MemberDTO(String id, String pw, String name, int membercode, int tel, String address) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.membercode = membercode;
		this.tel = tel;
		this.address = address;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMembercode() {
		return membercode;
	}
	public void setMembercode(int membercode) {
		this.membercode = membercode;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
