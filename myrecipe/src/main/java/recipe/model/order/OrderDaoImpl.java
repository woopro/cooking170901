package recipe.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public boolean order(Order order) {
		String sql = "insert into menu_order values (order_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,'배송중',?,?)";
		Object[] obj = {
				order.getNo_member(), order.getNo_menu(), order.getOrder_name(),
				order.getTel(), order.getMenu_cnt(), order.getOp1_cnt(), order.getOp2_cnt(),
				order.getOp3_cnt(),order.getPost(), order.getAddr1(), order.getAddr2(), 
				order.getWant_date(),order.getReal_date(),order.getComments()
				,order.getTotalprice()
		};
		int result = jdbcTemplate.update(sql, obj);
		return result>0;
	}

	@Override
	public Order orderList(int no_member) {
		String sql = "select * from menu_order where no_member=? order by no_order desc";
		RowMapper<Order> mapper = (rs, index) -> {
			return new Order(rs);
		};
		List<Order> orderList = jdbcTemplate.query(sql, new Object[] {no_member}, mapper);
		return orderList.get(0);
	}

	@Override
	public List<Order> olist() {
		RowMapper<Order> mapper = (rs,index)->{
			Order order = new Order(rs);
			return order;
		};
		return jdbcTemplate.query("select * from menu_order", mapper);
	}

	@Override
	public boolean complete(int orderNo) {
		int res = jdbcTemplate.update("update menu_order set stat='배송완료' where no_order=?",orderNo);
		return res>0;
	}
}
