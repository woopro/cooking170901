package recipe.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository(value="memberDao") 
public class MemberDaoImpl implements MemberDao {
	 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void sign(MemberDto dto) {
		String sql = "insert into member values("
				+ "member_seq.nextval,?,?,?,?,?,?,?,0,'일반',sysdate)";
		Object[] args = {dto.getEmail(),dto.getPassword(),dto.getName(),dto.getPhone(),
				dto.getPost(),dto.getAddr1(),dto.getAddr2()};
	jdbcTemplate.update(sql,args);
	}
	private RowMapper<MemberDto> mapper = (rs, index)->{
		return new MemberDto(rs);
	};

	@Override
	public boolean login(String email,String password) {
		String origin = jdbcTemplate.queryForObject("select password from member where email=?",String.class, email);
		return origin.matches(password);
	}

	@Override
	public List<MemberDto> info(String email) {
		RowMapper<MemberDto> mapper = (rs,index)->{
			MemberDto dto = new MemberDto(rs);
			return dto;
		};
		return jdbcTemplate.query("select * from member where email=?",new Object[] {email},mapper);
	}

	@Override
	public boolean edit(MemberDto dto) {
		String sql = "update member set password=?, name=?, phone=?, post=?, addr1=?, addr2=? where email=?";
		int res = jdbcTemplate.update(sql,dto.getPassword(),dto.getName(),dto.getPhone(),
				dto.getPost(),dto.getAddr1(),dto.getAddr2(),dto.getEmail());
		return res>0;
	}

}
