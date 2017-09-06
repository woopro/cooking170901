package recipe.model.board;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardDao {
	int write(BoardDto bdto);
	BoardDto info(int no);
	
	/* 문의유형별 검색 소스 추가 전 */ 
//	List<BoardDto> list(int start, int end);
//	List<BoardDto> list(String type, String key, int start, int end);
//	List<BoardDto> oneSearch(String type, String key, int start, int end);

	/* 문의유형별 검색 소스 추가 후 */
	List<BoardDto> list(int start, int end, String cg);
	List<BoardDto> list(String type, String key, String cg, int start, int end);
	List<BoardDto> oneSearch(String type, String key, String cg, int start, int end);
	int count(String type, String key);
	
	List<BoardDto> myQnA(String name);
	boolean checkpw(int no ,String pw);
	int edit(BoardDto bdto);
	void read(int no);
	int listCount();
	int oneSearchCount(String type, String key);
	void delete(int no);
}

