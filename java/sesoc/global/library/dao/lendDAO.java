package sesoc.global.library.dao;

import java.util.List;

import sesoc.global.library.vo.lend;

public interface lendDAO {
	public int insertlend(lend lend);
	public String statusinfo(int bnum);
	public List<lend> history(String unum);
	
	
}
