package recipe.model.menu;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository(value="mdao")
public class MenuDaoImpl implements MenuDao {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<MenuDto> mapper = (rs, idx)->{
		return new MenuDto(rs);
	};
	
	@Override
	public int add(MenuDto mdto) {
		String sql = "select menu_seq.nextval from dual";
		int no = jdbcTemplate.queryForObject(sql, Integer.class);
		sql = "insert into menu values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		Object[] obj = {
				no, mdto.getType(), mdto.getName(), mdto.getPrice(), 
				mdto.getOp1_name(), mdto.getOp1_price(), 
				mdto.getOp2_name(), mdto.getOp2_price(), 
				mdto.getOp3_name(), mdto.getOp3_price(), 
				mdto.getStat(), mdto.getStat_grade()
//				mdto.getImage_num()
				};
		jdbcTemplate.update(sql, obj);
		return no;
	}

	@Override
	public MenuDto info(int no) {
		ResultSetExtractor<MenuDto> extractor = (rs)->{
			return rs.next()?new MenuDto(rs):null;
		};
		String sql = "select * from menu where menu_no = ?";
		return jdbcTemplate.query(sql,new Object[] {no}, extractor);
	}

	@Override
	public List<MenuDto> list(String type, String key) {
		String sql = "select * from menu where "+type+" like '%'||?||'%' order by reg desc";
		return jdbcTemplate.query(sql, new Object[] {key} ,mapper);
	}
	@Override
	public boolean update(MenuDto dto) {
		String sql = "update menu set name=?,price=?,type=?"
				+ ",op1_name=?,op1_price=?,op2_name=?,op2_price=?,op3_name=?,op3_price=?"
				+ ",stat=?,stat_grade=? where menu_no=?";
		int res = jdbcTemplate.update(sql,
				dto.getName(),dto.getPrice(),dto.getType(),
				dto.getOp1_name(),dto.getOp1_price(),
				dto.getOp2_name(),dto.getOp2_price(),
				dto.getOp3_name(),dto.getOp3_price(),
				dto.getStat(),dto.getStat_grade(),
				dto.getMenu_no());
		log.debug(dto.getMenu_no()+"번 메뉴 / 수정 완료 ");
		log.debug("수정 내용 :  "+dto.toString());
		return res>0;
	}
	@Override
	public boolean delete(int no) {
		String sql = "delete menu where menu_no=?";
		int res = jdbcTemplate.update(sql, no);
		log.debug(no+"번 메뉴 / 삭제 완료 ");
		return res>0;
	}

}
