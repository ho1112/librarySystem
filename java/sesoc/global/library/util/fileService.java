package sesoc.global.library.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import sesoc.global.library.vo.books;


public class fileService {

	final String uploadPath = "/libraryfile"; //파일이 저장되는 HDD 경로
	
	public static String saveFile(MultipartFile upload, String uploadPath){
			//파일이 전송되었으므로 복사작업시작
			File path = new File(uploadPath);
			//저장디렉토리가 없으면 디렉토리 생성
			if(!path.isDirectory()){
				path.mkdirs();
			}
			String originalFileName = upload.getOriginalFilename();
			
			
			String filename; //확장명을 뺀 파일명
			String ext; //파일명을 뺀 확장자
			String savedFilename; //HDD에 저장되는 이름
			
			int lastIndex = originalFileName.lastIndexOf(".");
			filename = originalFileName.substring(0,lastIndex); //-1이면 0부터 전체 다
			
			//확장자가 없으면 -1 리턴
			if(lastIndex == -1) {
				ext = "";
			} else{ //확장자가 있으면
				ext = originalFileName.substring(lastIndex+1);
			}
			savedFilename = filename + "."+ext;
			System.out.println("파일명 : "+originalFileName+" , "+savedFilename);
		
			//파일 객체 생성 : 경로 + 파일명 경로는 C:에 생성되어있음
			File serverFile = null;
			serverFile = new File(uploadPath+"/"+savedFilename);
			//저장만 함
			try {
				upload.transferTo(serverFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		return savedFilename;
	}
	
	/**
	 * HDD에 저장된 파일을 삭제함
	 * @param fullPath = 경로 + 파일명
	 * @return
	 */
	public static boolean deleteFile(String fullPath){
		boolean result = false;
		File delFile = new File(fullPath);
		if(delFile.isFile()){
			delFile.delete();
			result = true;
		}
		return result;
	}
	
	
	
}
