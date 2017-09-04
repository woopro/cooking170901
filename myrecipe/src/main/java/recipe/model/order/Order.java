package recipe.model.order;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Order {
	private int no_member;
	private int no_menu;
	private String name;//받는 사람
	private String tel;//연락처
	private int menu_cnt;
	private int op1_cnt;
	private int op2_cnt;
	private int op3_cnt;
	private String post;
	private String addr1;
	private String addr2;
	private String order_date;
	private String want_date;
	private String real_date;//실제 배송일자
	private String stat;
	private String comments;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getNo_member() {
		return no_member;
	}
	public void setNo_member(int no_member) {
		this.no_member = no_member;
	}
	public int getNo_menu() {
		return no_menu;
	}
	public void setNo_menu(int no_menu) {
		this.no_menu = no_menu;
	}
	public int getMenu_cnt() {
		return menu_cnt;
	}
	public void setMenu_cnt(int menu_cnt) {
		this.menu_cnt = menu_cnt;
	}
	public int getOp1_cnt() {
		return op1_cnt;
	}
	public void setOp1_cnt(int op1_cnt) {
		this.op1_cnt = op1_cnt;
	}
	public int getOp2_cnt() {
		return op2_cnt;
	}
	public void setOp2_cnt(int op2_cnt) {
		this.op2_cnt = op2_cnt;
	}
	public int getOp3_cnt() {
		return op3_cnt;
	}
	public void setOp3_cnt(int op3_cnt) {
		this.op3_cnt = op3_cnt;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
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
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getWant_date() {
		return want_date;
	}
	public void setWant_date(String want_date) {
		this.want_date = want_date;
	}
	public String getReal_date() {
		return real_date;
	}
	public void setReal_date(String real_date) {
		this.real_date = real_date;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public Order() {
		super();
	}
	public Order(ResultSet rs) throws SQLException {
		setNo_member(rs.getInt("no_member"));
		setNo_menu(rs.getInt("no_menu"));
		setName(rs.getString("name"));
		setTel(rs.getString("tel"));
		setMenu_cnt(rs.getInt("menu_cnt"));
		setOp1_cnt(rs.getInt("op1_cnt"));
		setOp2_cnt(rs.getInt("op2_cnt"));
		setOp3_cnt(rs.getInt("op3_cnt"));
		setPost(rs.getString("post"));
		setAddr1(rs.getString("addr1"));
		setAddr2(rs.getString("addr2"));
		setOrder_date(rs.getString("order_date"));
		setWant_date(rs.getString("want_date"));
		setReal_date(rs.getString("real_date"));
		setStat(rs.getString("stat"));
		setComments(rs.getString("comments"));
	}
	@Override
	public String toString() {
		return "Order [no_member=" + no_member + ", no_menu=" + no_menu + ", name=" + name + ", tel=" + tel
				+ ", menu_cnt=" + menu_cnt + ", op1_cnt=" + op1_cnt + ", op2_cnt=" + op2_cnt + ", op3_cnt=" + op3_cnt
				+ ", post=" + post + ", addr1=" + addr1 + ", addr2=" + addr2 + ", order_date=" + order_date
				+ ", want_date=" + want_date + ", real_date=" + real_date + ", stat=" + stat + ", comments=" + comments
				+ "]";
	}
	
	
}
