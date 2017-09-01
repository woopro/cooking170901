package recipe.model.member;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDto { 
	private int no;
	private String email;
	private String password;
	private int phone;
	private int post;
	private String addr1;
	private String addr2;
	private int mile;
	private String auth;
	private String reg;
	
	public MemberDto() {
	}
	public MemberDto(ResultSet rs) throws SQLException {
		setNo(rs.getInt("no"));
		setEmail(rs.getString("email"));
		setPassword(rs.getString("password"));
		setPhone(rs.getInt("phone"));
		setPost(rs.getInt("post"));
		setAddr1(rs.getString("addr1"));
		setAddr2(rs.getString("addr2"));
		setMile(rs.getInt("mile"));
		setAuth(rs.getString("auth"));
		setReg(rs.getString("reg"));
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getPost() {
		return post;
	}
	public void setPost(int post) {
		this.post = post;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public int getMile() {
		return mile;
	}
	public void setMile(int mile) {
		this.mile = mile;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	
}
