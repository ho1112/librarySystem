package sesoc.global.library;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sesoc.global.library.vo.books;
import sesoc.global.library.vo.users;
import sesoc.global.library.dao.usersRepository;
import sesoc.global.library.util.fileService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	final String uploadPath = "/libraryfile";
	
	@Autowired
	usersRepository repo;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		if(session.getAttribute("loginId") !=null){
			String id= (String) session.getAttribute("loginId");
			users users = repo.selectOne(id);
			model.addAttribute("users",users);
			System.out.println(users);
		}
		return "home";
	}
	
	@RequestMapping(value="joinForm")
	public String joinForm(){
		return "join";
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String join(users users, MultipartFile photo,Model model){
		/*
		 *	#####변수를 upload에서 photo로 바꾸니 잘 됩니다##### 
		 */
		System.out.println("조인컨트롤러");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String ran = sdf.format(new Date());
		
		users.setNum(ran);
		if(!photo.isEmpty()){
			users.setImage(photo.getOriginalFilename());
			fileService.saveFile(photo, uploadPath);
		}
		System.out.println(users);
		int result = repo.join(users);
		String message= "";
		if(result ==1) message="<script>alert('가입성공');</script>";
		else message="<script>alert('가입실패');</script>";
		model.addAttribute("m",message);
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="check")
	public String check(String id){
		System.out.println("체크진입");
		users user = repo.check(id);
		String message = "";
		if(user == null) message = "가입할 수 있는 ID입니다";
		else message = "이미 있는 ID입니다";
		System.out.println(user);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value="login", method=RequestMethod.POST)
	public users login(String id, String pw,HttpSession session){
		users user = repo.login(id, pw);
		if(user != null){
			session.setAttribute("loginId", user.getUserid());
			session.setAttribute("loginUser", user);
		}
		System.out.println(user.getBirthdate());
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		session.removeAttribute("loginId");
		session.removeAttribute("loginUser");
		System.out.println("로그아웃");
		return "로그아웃 되셨습니다";
	}
	
	@RequestMapping(value="updateForm")
	public String updateForm(){
		System.out.println("업뎃폼요청");
		return "update";
	}
	
	@ResponseBody
	@RequestMapping(value="updateinfo")
	public users updateinfo(HttpSession session){
		users user = repo.selectOne((String)session.getAttribute("loginId"));
		return user;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(users user, MultipartFile photo,HttpSession session,Model model){
		String message ="";
		String path=null;
		String id = (String) session.getAttribute("loginId");
		users selectuser= repo.selectOne(id);
		selectuser.setPassword(user.getPassword());
		if(selectuser.getImage() !=null){ //파일업로드가 있으면
			path = uploadPath + "/" + selectuser.getImage();
		}
		if(!photo.isEmpty()){ //이미지가 존재하면
			user.setImage(photo.getOriginalFilename());
			fileService.saveFile(photo, uploadPath);
			System.out.println("삭제할패스:"+path);
			fileService.deleteFile(path);//기존파일삭제
		}
		System.out.println("수정직전:"+user);
		user.setNum(selectuser.getNum());
		user.setUserid(selectuser.getUserid());
		user.setUsername(selectuser.getUsername());
		user.setBirthdate(selectuser.getBirthdate());
		int result = repo.update(user);
		
		if(result ==1) message="<script>alert('수정되었습니다');</script>";
		else message="<script>alert('수정실패');</script>";
		model.addAttribute("r",message);
		System.out.println(message);
		return "home";
	}
	
	@RequestMapping("/download")
	public String download(String id, HttpServletResponse response) {
		users user = repo.selectOne(id);
		String ofn = user.getImage();
		
		try {
			response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(ofn,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fullPath = uploadPath + "/" + ofn;
		FileInputStream filein = null;
		ServletOutputStream sout = null;
		try {
			filein = new FileInputStream(fullPath); //HDD에서 파일을 받아와서
			sout = response.getOutputStream(); //클라이언트에게 내보낸다
			FileCopyUtils.copy(filein, sout); //파일을 읽어서 내보낸다
			filein.close();
			sout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
