package sesoc.global.library.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sesoc.global.library.vo.books;

@Repository
public class booksRepository {

	@Autowired
	SqlSession sqlSession;
	
	public List<books> search(String searchtype, String searchword){
		System.out.println("검색진입");
		booksDAO dao = sqlSession.getMapper(booksDAO.class);
		System.out.println("세션생성");
		Map<String,String> map = new HashMap<>();
		map.put("searchtype", searchtype); map.put("searchword", searchword);
		List<books> list = dao.list(map);
		return list;
	}

	public List<books> allList(){
		booksDAO dao = sqlSession.getMapper(booksDAO.class);
		List<books> list = dao.allList();
		System.out.println(list.get(0));
		return list;
	}

	public books bookinfo(int num){
		booksDAO dao = sqlSession.getMapper(booksDAO.class);
		books book = dao.bookinfo(num);
		return book;
	}

	
	
}
