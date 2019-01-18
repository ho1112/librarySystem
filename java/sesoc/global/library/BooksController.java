package sesoc.global.library;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sesoc.global.library.dao.booksRepository;
import sesoc.global.library.dao.lendRepository;
import sesoc.global.library.vo.books;
import sesoc.global.library.vo.users;

@Controller
public class BooksController {

	@Autowired
	booksRepository repo;
	
	@Autowired
	lendRepository lrepo;
	
	@ResponseBody
	@RequestMapping(value="search" , method=RequestMethod.POST)
	public List<books> search(String searchtype, String searchword){
		List<books> list = repo.search(searchtype,searchword);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="allList")
	public List<books> allList(){
		List<books> list = repo.allList();
		return list;
	}
	
	@RequestMapping(value="bookinfo")
	public String bookinfo(String num, Model model,HttpSession session){
		int n = Integer.parseInt(num);
		books book = repo.bookinfo(n);
		String st = lrepo.statusinfo(n);
		String m = "";
		if(st !=null){
			if(st.equals("rejected") || st.equals("delayed") || st.equals("lent") || st.equals("reserved")){
				m = "N";
			}
		}else{
			m = "Y";
		}
		users user = (users) session.getAttribute("loginUser");
		model.addAttribute("book",book);
		model.addAttribute("users", user);
		model.addAttribute("status",m);
		return "bookinfo";
	}
	
	@ResponseBody
	@RequestMapping(value="lendbook")
	public books lendbook(int bnum){
		books book = repo.bookinfo(bnum);
		return book;
	}
	
	
	
}
