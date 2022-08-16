package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

public class FileManager {
	
	//파일 업로드
	public static String doFileUpload(FormFile upload, String path) throws Exception{
		
		//새로운 파일의 이름을 저장할 변수
		String newFileName;
		
		if(upload==null) {
			return null;
		}
		
		
		//클라이언트가 업로드한 파일 이름
		String originalFileName = upload.getFileName();
		
		if(originalFileName.equals("")) {
			return null;
		}
	
		
		//파일 확장자
		//뒤에서부터 .을 찾아서 끝까지 
		String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		if(fileExt==null || fileExt.equals("")) {
			return null;
		}
		
		
		//서버에 저장할 새로운 파일명 생성
		//뒤에 인수는 1개인데 포멧의 6군데에서 써야 하기 때문에 반복하기 위해 1$를 붙여준다.
		newFileName = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
		
		//년월일시분초의 중복값이 없게 하기 위해 추가하는 문장
		newFileName += System.nanoTime(); //10의 -9승
		
		//새로운 이름 뒤에 확장자명을 더해준다.
		newFileName += fileExt;
		
		//업로드할 경로 생성
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		//폴더명까지 더한 총 경로 
		String fullFilePath = path + File.separator + newFileName;
				
		
		//struts1의 파일 업로드
		byte[] fileData = upload.getFileData();
		FileOutputStream fos = new FileOutputStream(fullFilePath);
		fos.write(fileData);
		fos.close();
		
		return newFileName;
	}
	
	
	//파일 다운로드 메소드
	public static boolean doFileDownload(HttpServletResponse response, 
			String saveFileName, String originalFileName, String path) {
		//서버가 클라이언트한테 데이터를 주는 것이기 때문에 response가 필요
		
		try {
			
			String fullPath = path + File.separator + saveFileName;
			
			if(originalFileName==null || originalFileName.equals("")) {
				originalFileName = saveFileName;
			}
		
			//한글파일 이름 깨짐 방지
			originalFileName = new String(originalFileName.getBytes("euc-kr"),"iso-8859-1");
			
			File f = new File(fullPath);
			
			if(!f.exists()) {
				return false;
			}
			
			
			//파일 다운로드
			response.setContentType("application/octet-stream");
			
			response.setHeader("Content-disposition", "attachment;fileName=" + originalFileName);
			
			//클라이언트한테 파일을 주려면 우선 읽어야한다. FIS사용 필요(인풋스트림)
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			
			OutputStream out = response.getOutputStream();
			
			int data;
			byte[] bytes = new byte[4096];
			while((data=bis.read(bytes, 0, 4096))!=-1) {
				out.write(bytes,0,data);
			}
			
			out.flush(); //버퍼가 꽉 차지 않아도 마지막 남은 데이터를 내보내기 위해 필요
			out.close();
			bis.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		return true; //스크립트를 만들기 위해 반환했다.
		
	}
	
	
	
	//파일 삭제 메소드 (DB 말고 물리적으로 삭제)
	public static void doFileDelete(String fileName,String path) {
		
		try {
			
			String filePath = path + File.separator + fileName;
		
			File f = new File(filePath);
		
			if(f.exists()) {
				f.delete(); //물리적 파일 삭제
				//저장되어 있는 공간으로 가서 파일을 삭제
			}
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	

}
