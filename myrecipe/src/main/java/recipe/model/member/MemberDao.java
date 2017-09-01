package recipe.model.member;

import org.springframework.stereotype.Repository;

@Repository(value="memberDao") 
public interface MemberDao {
	void sign();
	boolean login();
	MemberDto info(int no);
	MemberDto edit(int no);
	void sign(MemberDto dto);
	String login(String email, String password);
}
