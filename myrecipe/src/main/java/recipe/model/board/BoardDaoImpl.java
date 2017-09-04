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
		sql = "insert into board values(?,?,?,?,?,?,?,0,sysdate)";
		Object[] obj = {
				no,  bdto.getName(), bdto.getEmail(), bdto.getCategory(), bdto.getTitle(),
				bdto.getDetail(), bdto.getPw()};
		jdbcTemplate.update(sql, obj);
		return no;
	}

	@Override
	public BoardDto info(int no) {
		String sql = "select * from board where board_no = ?";
		return jdbcTemplate.query(sql, new Object[] {no}, extractor);
	}

	@Override
	public List<BoardDto> list(String type, String key) {
		String sql = "select * from board where "+type+" like '%'||?||'%' order by reg desc";
		return jdbcTemplate.query(sql, new Object[] {key} ,mapper);
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
}
