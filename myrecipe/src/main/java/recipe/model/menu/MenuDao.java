package recipe.model.menu;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao {
	int add(MenuDto mdto);
	MenuDto info(int no);
	List<MenuDto> list(String type, String key);
	
}
