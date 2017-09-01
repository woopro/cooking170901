package recipe.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository(value="memberDao") 
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private static JdbcTemplate jdbcTemplate;
	
	@Override
	public void sign(MemberDto dto) {
		Object[] args = {dto.getEmail(),dto.getPassword(),dto.getPhone(),
				dto.getPost(),dto.getAddr1(),dto.getAddr2()};
	
	String sql = "insert into member values("
			+ "member_seq.nextval,?,?,?,?,?,?,?,0,'일반',sysdate)";
	jdbcTemplate.update(sql,args);
	}

	@Override
	public String login(String email,String password) {
		Object[] logininfo = {email,password};
		String sql ="select * from member where email=? and password=?";
		String result = jdbcTemplate.queryForObject(sql,String.class,logininfo);
		return result;
	}

	@Override
	public MemberDto info(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDto edit(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sign() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}

}
