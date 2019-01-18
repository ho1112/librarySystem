package sesoc.global.library;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sesoc.global.library.dao.lendRepository;
import sesoc.global.library.dao.usersRepository;
import sesoc.global.library.vo.books;
import sesoc.global.library.vo.lend;
import sesoc.global.library.vo.users;

@Controller
public class lendController {

	@Autowired
	lendRepository repo;
	
	@Autowired
	usersRepository urepo;
	
	@ResponseBody
	@RequestMapping(value="lend")
	public String insertlend(int bnum, String unum){
		lend lend = new lend();
		String message = "";
		System.out.println(bnum+"/"+unum);
		lend.setBooknum(bnum);
		lend.setUsernum(unum);
		int result = repo.insertlend(lend);
		if(result == 1) message = "대출신청완료";
		else message = "대출거절";
		System.out.println(message);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value="statusinfo")
	public String statusinfo(int bnum){
		System.out.println(bnum);
		String st =repo.statusinfo(bnum);
		System.out.println("st : "+st);
		String message = "";
		if(st !=null){
			if(st.equals("rejected") || st.equals("delayed") || st.equals("lent") || st.equals("reserved")){
				message = "N";
			}
		}else{
			message = "Y";
		}
		System.out.println(message);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value="lendhistory")
	public List<lend> history(HttpSession session){
		List<lend> list = new ArrayList<>();
		String loginId = (String) session.getAttribute("loginId");
		users user = urepo.check(loginId);
		list =repo.history(user.getNum());
		System.out.println("대출리스트"+list.get(0));
		return list;
	}
	
	
	
}
