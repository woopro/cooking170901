package recipe.model.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MenuDto {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private int menu_no;
	private String type;
	private String name;
	private int price;
	private String op1_name;
	private int op1_price;
	private String op2_name;
	private int op2_price;
	private String op3_name;
	private int op3_price;
	private String stat;
	private String stat_grade;
	private String image_num;
	private String reg;
	
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public MenuDto(HttpServletRequest req) throws SQLException {
		Map<String, String[]> a = req.getParameterMap();
		for(Map.Entry<String, String[]> entry: a.entrySet()) {
			log.debug("Key: " + entry.getKey() + "	// Value: " + entry.getValue()[0]);
		};
		
		setName(req.getParameter("name"));
		setType(req.getParameter("type"));
		String price = req.getParameter("price");
		
		setPrice(price==null?0:Integer.parseInt(price));
		setOp1_name(req.getParameter("op1"));
		String op1_price = (req.getParameter("op1_price"));
		setOp1_price(op1_price==null?0:Integer.parseInt(op1_price));
		setOp2_name(req.getParameter("op2"));
		String op2_price = (req.getParameter("op2_price"));
		setOp2_price(op2_price==null?0:Integer.parseInt(op2_price));
		setOp3_name(req.getParameter("op3"));
		String op3_price = (req.getParameter("op3_price"));
		setOp3_price(op3_price==null?0:Integer.parseInt(op3_price));
		
		setStat(req.getParameter("stat"));
		setStat_grade(req.getParameter("stat_grade"));
//		setImage_num(req.getParameter("image_num"));
		log.debug("req 처리 완료");
	}
	public MenuDto(ResultSet rs) throws SQLException {
		log.debug("rs 방문");
		
		setMenu_no(rs.getInt("menu_no"));
		setType(rs.getString("type"));
		setName(rs.getString("name"));
		setPrice(rs.getInt("price"));
		setOp1_name(rs.getString("op1_name"));
		setOp1_price(rs.getInt("op1_price"));
		setOp1_name(rs.getString("op2_name"));
		setOp1_price(rs.getInt("op2_price"));
		setOp1_name(rs.getString("op3_name"));
		setOp1_price(rs.getInt("op3_price"));
		setStat(rs.getString("stat"));
		setStat_grade(rs.getString("stat_grade"));
//		setImage_num(rs.getString("image_num"));
		setReg(rs.getString("reg"));
	}
	public int getMenu_no() {
		return menu_no;
	}
	public void setMenu_no(int menu_no) {
		this.menu_no = menu_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getOp1_name() {
		return op1_name;
	}
	public void setOp1_name(String op1_name) {
		this.op1_name = op1_name;
	}
	public int getOp1_price() {
		return op1_price;
	}
	public void setOp1_price(int op1_price) {
		this.op1_price = op1_price;
	}
	public String getOp2_name() {
		return op2_name;
	}
	public void setOp2_name(String op2_name) {
		this.op2_name = op2_name;
	}
	public int getOp2_price() {
		return op2_price;
	}
	public void setOp2_price(int op2_price) {
		this.op2_price = op2_price;
	}
	public String getOp3_name() {
		return op3_name;
	}
	public void setOp3_name(String op3_name) {
		this.op3_name = op3_name;
	}
	public int getOp3_price() {
		return op3_price;
	}
	public void setOp3_price(int op3_price) {
		this.op3_price = op3_price;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getStat_grade() {
		return stat_grade;
	}
	public void setStat_grade(String stat_grade) {
		this.stat_grade = stat_grade;
	}
	public String getImage_num() {
		return image_num;
	}
	public void setImage_num(String image_num) {
		this.image_num = image_num;
	}
	@Override
	public String toString() {
		return "menuDto [menu_no=" + menu_no + ", type=" + type + ", name=" + name + ", price=" + price + ", op1_name="
				+ op1_name + ", op1_price=" + op1_price + ", op2_name=" + op2_name + ", op2_price=" + op2_price
				+ ", op3_name=" + op3_name + ", op3_price=" + op3_price + ", stat=" + stat + ", stat_grade="
				+ stat_grade + ", image_num=" + image_num + "]";
	}
	
}
