package com.fileTest;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.util.FileManager;
import com.util.MyPage;
import com.util.dao.CommonDAO;
import com.util.dao.CommonDAOImpl;

public class FileTestAction extends DispatchAction{

	//execute 메소드를 오버라이드 해서 우리가 사용하려는 의도에 맞게 변경
	public ActionForward write(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("write");
	}
	
	
	public ActionForward write_ok(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		HttpSession session = request.getSession();
		
		String root = session.getServletContext().getRealPath("/");
		
		String savePath = root + "pds" + File.separator + "saveFile";
		
		FileTestForm f = (FileTestForm)form;
		
		//파일 업로드
		String newFileName = FileManager.doFileUpload(f.getUpload(), savePath);
		
		if(newFileName!=null) {
			int maxNum = dao.getIntValue("fileTest.maxNum");
			
			f.setNum(maxNum + 1);
			f.setSaveFileName(newFileName);
			f.setOriginalFileName(f.getUpload().getFileName());
			
			dao.insertData("fileTest.insertData", f);
		}
		
		return mapping.findForward("write_ok");
		
	}	
	
	
	public ActionForward list(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		String cp = request.getContextPath();
		
		MyPage myPage = new MyPage();
		
		int numPerPage = 5;
		int totalPage = 0;
		int totalDataCount = 0;
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum!=null && !pageNum.equals("")) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		//전체 데이터 개수
		totalDataCount = dao.getIntValue("fileTest.dataCount");
		
		//페이지 수
		if(totalDataCount!=0) {
			totalPage = myPage.getPageCount(numPerPage, totalDataCount);
		}
		
		if(currentPage>totalPage) {
			currentPage = totalPage;
		}
		
		Map<String, Object> hMap = new HashMap<String, Object>();
		
		int start = (currentPage-1) * numPerPage+1;
		int end = currentPage*numPerPage;
		
		hMap.put("start", start);
		hMap.put("end", end);
		
		List<Object> lists = dao.getListData("fileTest.listData",hMap);
		
		
		//일련번호 만들기
		Iterator<Object> it = lists.iterator();
		
		int listNum, n = 0;
		String str; //파일 다운로드 경로를 만들기 위한 변수
		
		while(it.hasNext()) {
			
			FileTestForm dto = (FileTestForm)it.next();
			
			listNum = totalDataCount - (start+n-1);
			
			dto.setListNum(listNum);
			
			n++;
			
			//파일 다운경로
			str = cp + "/file.do?method=download&num=" + dto.getNum();
			dto.setUrlFile(str);
		}
		
		String urlList = cp + "/file.do?method=list";
		
		request.setAttribute("lists", lists);
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("totalDataCount", totalDataCount);
		request.setAttribute("pageIndexList", myPage.pageIndexList(currentPage, totalPage, urlList));

		return mapping.findForward("list");
	}	
	
	
	public ActionForward delete_ok(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		HttpSession session = request.getSession();
		String root = session.getServletContext().getRealPath("/");
		String savePath = root + "pds" + File.separator + "saveFile";
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		//하나의 데이터 읽어오기
		FileTestForm dto = (FileTestForm)dao.getReadData("fileTest.readData",num);
		
		//파일삭제
		FileManager.doFileDelete(dto.getSaveFileName(), savePath);
		
		//DB삭제
		dao.deleteData("fileTest.deleteData", num);
		
		return mapping.findForward("delete_ok");
	}	
	
	
	public ActionForward download(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		HttpSession session = request.getSession();
		String root = session.getServletContext().getRealPath("/");
		String savePath = root + "pds" + File.separator + "saveFile";
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		FileTestForm dto = (FileTestForm)dao.getReadData("fileTest.readData",num);
		
		boolean flag = FileManager.doFileDownload(response, dto.getSaveFileName(), 
				dto.getOriginalFileName(), savePath);
		
		if(!flag) {
			response.setContentType("text/html);charest=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script type='text/javascript'>");
			out.print("alert('다운로드 에러');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		
		
		//다운로드가 되었다고 해서 창이 다른 곳으로 이동하면 안되기 때문에 null을 반환값으로 넣는다.
		return mapping.findForward(null);
	}	
	
	
	
	
	
}
