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
		ResultSetExtractor<BoardDto> extractor = (rs)->{
			return rs.next()?new BoardDto(rs):null;
		};
		String sql = "select * from board where board_no = ?";
		return jdbcTemplate.query(sql, new Object[] {no}, extractor);
	}

	@Override
	public List<BoardDto> list() {
		RowMapper<BoardDto> mapper = (rs, idx)->{
			return new BoardDto(rs);
		};
		
		String sql = "select * from board order by board_no desc";
		List<BoardDto> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

//	@Override
//	public List<BoardDto> list(String type, String keyword) {
//		RowMapper<BoardDto> mapper = (rs, idx)->{
//			return new BoardDto(rs);
//		};
//		keyword = "%"+keyword+"%";
//		System.out.println("mapper= "+mapper.toString());
//		System.out.println("keyword = "+keyword);
//
//		String sql = "select * from board where "+type+" like ?";
//		return jdbcTemplate.query(sql, new Object[] {keyword}, mapper);
//	}
}