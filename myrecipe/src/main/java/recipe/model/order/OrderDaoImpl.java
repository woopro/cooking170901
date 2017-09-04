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
	
	RowMapper<Order> mapper = (rs, index) -> {
		return new Order(rs);
	};
	
	@Override
	public boolean order(Order order) {
		String sql = "insert into menu_order values (?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?,?)";
		
		int result = jdbcTemplate.update(sql, mapper);
		return result>0;
	}

	@Override
	public Order orderList(int no_member) {
		String sql = "select * from order where no_member=?";
		List<Order> list = jdbcTemplate.query(sql, new Object[] {no_member}, mapper);
		return list.get(0);
	}

}
