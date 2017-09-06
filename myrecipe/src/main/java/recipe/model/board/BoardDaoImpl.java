package recipe.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository(value="bdao")
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<BoardDto> mapper = (rs, idx)->{
		return new BoardDto(rs);
	};
	
	private ResultSetExtractor<BoardDto> extractor = (rs)->{
		return rs.next()?new BoardDto(rs):null;
	};
	
	@Override
	public int write(BoardDto bdto) {
		String sql = "select board_seq.nextval from dual";
		int no = jdbcTemplate.queryForObject(sql, Integer.class);
		sql = "insert into board values(?,?,?,?,?,?,?,0,sysdate,?,?,?)";
		Object[] obj = {
				no,  bdto.getName(), bdto.getEmail(), bdto.getCategory(), bdto.getTitle(),
				bdto.getDetail(), bdto.getPw(), bdto.getFilename(), bdto.getFilesize(), bdto.getFiletype()};
		jdbcTemplate.update(sql, obj);
		return no;
	}

	@Override
	public BoardDto info(int no) {
		String sql = "select * from board where board_no = ?";
		return jdbcTemplate.query(sql, new Object[] {no}, extractor);
	}

//	@Override
//	public List<BoardDto> list(String type, String key) {
//		String sql = "select * from board where "+type+" like '%'||?||'%' order by reg desc";
//		return jdbcTemplate.query(sql, new Object[] {key} ,mapper);
//	}
	
	@Override
	public List<BoardDto> list(int start, int end) {
//		System.out.println("맨 처음 게시판 들어가면 실행되는 메소드");
		String sql = "select * from "
						+ "(select rownum rn, A.* from "
						+ "(select * from board order by reg desc)A)"
						+ "where rn between ? and ?";
		return jdbcTemplate.query(sql, new Object[] {start, end}, mapper);
	}
	
	public static final String TITLE = "title";
	public static final String NAME = "name";
	public static final String CATEGORY = "category";
	
	@Override
	public List<BoardDto> list(String type, String key, int start, int end) {
		if(type==null || key==null) {
			return list(start, end);
		}
//		System.out.println("검색하면 실행되는 메소드");
		switch(type) {
		case TITLE:
		case NAME:
		case CATEGORY:
			return oneSearch(type, key,start, end);
		}
		return null;
	}

	@Override
	public List<BoardDto> oneSearch(String type, String key, int start, int end) {
		switch(type) {
		case TITLE: type="title"; break;
		case NAME: type="name"; break;
		case CATEGORY: type="category"; break;
		}
		String sql = "select * from "
				+ "(select rownum rn, TMP.* from "
				+ "(select * from board "
				+ "where "+type+" like '%'||?||'%' "
						+ "order by reg desc)TMP) "
						+ "where rn between ? and ?";
		return jdbcTemplate.query(sql, new Object[] {key, start, end},mapper);
	}
	
	@Override
	public List<BoardDto> myQnA(String name) {
		String sql = "select* from board where email= ? order by reg desc";
		return jdbcTemplate.query(sql, new Object[] {name},mapper);
	}

	@Override
	public boolean checkpw(int no, String pw) {
		String sql = "select * from board where board_no =? and pw =?";
		return jdbcTemplate.update(sql, no, pw) > 0;
	}

	@Override
	public int edit(BoardDto bdto) {
		String sql = "update board set name=?, email=?, category=?, title=?, detail=?, pw=? where board_no = ?";
		Object[] obj = {
				bdto.getName(), bdto.getEmail(), bdto.getCategory(), bdto.getTitle(), bdto.getDetail(),
				bdto.getPw(), bdto.getNo()
		};
		jdbcTemplate.update(sql, obj);
		return bdto.getNo();
	}

	@Override
	public void read(int no) {
		String sql = "update board set read=read+1 where board_no = ?";
		jdbcTemplate.update(sql, no);
	}

	@Override
	public int count(String type, String key) {
		if(type==null || key==null) {
			return listCount();
		}
//		System.out.println("검색 후 출력될 게시글의 개수");
		switch(type) {
		case TITLE:
		case NAME:
		case CATEGORY:
			return oneSearchCount(type, key);
		}
		return 0;
	}
	
	@Override
	public int listCount() {
//		System.out.println("게시판 처음에 출력될 게시글의 개수");
		String sql = "select count(*) from board";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int oneSearchCount(String type, String key) {
		switch(type) {
			case TITLE: type="title"; break;
			case NAME: type="name"; break;
			case CATEGORY: type="category"; break;
		}
		String sql = "select count(*) from board where "+type+" like '%'||?||'%' ";
		return jdbcTemplate.queryForObject(sql, new Object[] {key}, Integer.class);
	}

	@Override
	public void delete(int no) {
		String sql = "delete board where board_no = ?";
		jdbcTemplate.update(sql, no);
	}
}
