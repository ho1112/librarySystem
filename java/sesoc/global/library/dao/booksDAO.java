package sesoc.global.library.dao;

import java.util.List;
import java.util.Map;

import sesoc.global.library.vo.books;

public interface booksDAO {
	
	public List<books> list(Map<String,String> map);
	public List<books> allList();
	public books bookinfo(int num);
}
