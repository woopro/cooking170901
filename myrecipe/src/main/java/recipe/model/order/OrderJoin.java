package recipe.model.order;

import java.sql.ResultSet;

import recipe.model.menu.MenuDto;

public class OrderJoin {
	private Order order;
	private MenuDto menu;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public MenuDto getMenu() {
		return menu;
	}
	public void setMenu(MenuDto menu) {
		this.menu = menu;
	}
	
	public OrderJoin(ResultSet rs) {
		setOrder(order);
		setMenu(menu);
	}
	
}
