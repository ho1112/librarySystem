package sesoc.global.library.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sesoc.global.library.vo.users;

@Repository
public class usersRepository {
	
	@Autowired
	SqlSession sqlSession;
	
	public int join(users user){
		usersDAO dao = sqlSession.getMapper(usersDAO.class);
		int result = dao.join(user);
		return result;
	}
	
	public users check(String id){
		usersDAO dao = sqlSession.getMapper(usersDAO.class);
		users user = dao.check(id);
		System.out.println(user);
		return user;
	}
	
	public users login(String id,String pw){
		usersDAO dao = sqlSession.getMapper(usersDAO.class);
		Map<String,String> map = new HashMap<String, String>();
		map.put("id", id); map.put("pw", pw);
		users user = dao.login(map);
		return user;
	}
	
	public users selectOne(String id){
		usersDAO dao = sqlSession.getMapper(usersDAO.class);
		users user = dao.selectOne(id);
		return user;
	}

	public int update(users user){
		usersDAO dao = sqlSession.getMapper(usersDAO.class);
		System.out.println("레파");
		int result= dao.update(user);
		System.out.println("레파나옴");
		return result;
	}

	
		
}
