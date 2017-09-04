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
		log.info("add방문");
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
		log.info("info방문 : no = "+no);
		ResultSetExtractor<MenuDto> extractor = (rs)->{
			return rs.next()?new MenuDto(rs):null;
		};
		String sql = "select * from menu where menu_no = ?";
		return jdbcTemplate.query(sql,new Object[] {no}, extractor);
	}

	@Override
	public List<MenuDto> list(String type, String key) {
		log.info("list sql 실행");
		String sql = "select * from menu where "+type+" like '%'||?||'%' order by reg desc";
		return jdbcTemplate.query(sql, new Object[] {key} ,mapper);
	}

}
