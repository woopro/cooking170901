package recipe.model.order;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	boolean order(Order order);
	Order orderList(int no_member);
}
