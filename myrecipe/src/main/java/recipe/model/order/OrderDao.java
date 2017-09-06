package recipe.model.order;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	boolean order(Order order);
	//	List<Order> orderList(int no_member);
	Order orderList(int no_member);
	List<Order> olist();
	boolean complete(int orderNo);
}
