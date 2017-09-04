package recipe.model.board;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardDao {
	int write(BoardDto bdto);
	BoardDto info(int no);
	List<BoardDto> list(String type, String key);
	List<BoardDto> myQnA(String name);
	boolean checkpw(int no ,String pw);
//	int count(String key, String type);
}
