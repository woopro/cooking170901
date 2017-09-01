package recipe.model.board;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardDao {
	int write(BoardDto bdto);
	BoardDto info(int no);
	List<BoardDto> list();
//	List<BoardDto> list(String type, String keyword);
//	int count(String key, String type);
}
