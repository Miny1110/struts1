package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileManager {
	
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
