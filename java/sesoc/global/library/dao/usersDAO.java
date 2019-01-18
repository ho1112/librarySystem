package sesoc.global.library.dao;

import java.util.Map;

import sesoc.global.library.vo.users;

public interface usersDAO {
	public int join(users user);
	public users login(Map<String,String> map);
	public users selectOne(String id);
	public int update(users user);
	public users check(String id);
}
