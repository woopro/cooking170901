package recipe.model.board;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardDao {
	int write(BoardDto bdto);
	BoardDto info(int no);
	List<BoardDto> list(int start, int end);
	List<BoardDto> list(String type, String key, int start, int end);
	List<BoardDto> oneSearch(String type, String key, int start, int end);
	List<BoardDto> myQnA(String name);
	boolean checkpw(int no ,String pw);
	int edit(BoardDto bdto);
	void read(int no);
	int count(String type, String key);
	int listCount();
	int oneSearchCount(String type, String key);
	void delete(int no);
}

