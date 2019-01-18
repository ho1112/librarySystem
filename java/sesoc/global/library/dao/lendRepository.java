package sesoc.global.library.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sesoc.global.library.vo.lend;

@Repository
public class lendRepository {

	@Autowired
	SqlSession sqlSession;
	
	public int insertlend(lend lend){
		lendDAO dao = sqlSession.getMapper(lendDAO.class);
		int result = dao.insertlend(lend);
		return result;
	}
	
	public String statusinfo(int bnum){
		lendDAO dao = sqlSession.getMapper(lendDAO.class);
		String status = dao.statusinfo(bnum);
		return status;
	}
	
	public List<lend> history(String unum){
		List<lend> list = new ArrayList<>();
		lendDAO dao = sqlSession.getMapper(lendDAO.class);
		list = dao.history(unum);
		return list;
	}


	
}
