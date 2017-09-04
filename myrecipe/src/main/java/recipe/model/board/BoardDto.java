package recipe.model.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class BoardDto {
	private int no;
	private String name;
	private String email;
	private String category;
	private String title;
	private String detail;
	private String pw;
	private int read;
	private String reg;
	
	public BoardDto(HttpServletRequest request) {
		String no = request.getParameter("board_no");
		setNo(no==null?1:Integer.parseInt(no));
		setName(request.getParameter("name"));
		setEmail(request.getParameter("email"));
		setCategory(request.getParameter("category"));
		setTitle(request.getParameter("title"));
		setDetail(request.getParameter("detail"));
		setPw(request.getParameter("pw"));
		String read = request.getParameter("read");
		setRead(read==null?0:Integer.parseInt(read));
		setReg(request.getParameter("reg"));
	}
	public BoardDto(ResultSet rs) throws SQLException {
		setNo(rs.getInt("board_no"));
		setName(rs.getString("name"));
		setEmail(rs.getString("email"));
		setCategory(rs.getString("category"));
		setTitle(rs.getString("title"));
		setDetail(rs.getString("detail"));
		setPw(rs.getString("pw"));
		setRead(rs.getInt("read"));
		setReg(rs.getString("reg"));
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public String getDetail2() {
		return this.detail.replace("\n", "<br>");
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getDate() {
		return reg.substring(0, 10);
	}
	public String getTime() {
		return reg.substring(11, 19);
	}
	public String getAuto() {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(getDate().equals(today))  	// 작성글이 오늘이면 년월일을 제외한 시간을 표시, 그렇지 않으면 년월일만 표시
			return getTime();
		else return getDate();
	}
}
