package com.fileTest;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.util.FileManager;
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
		
		return mapping.findForward("list");
	}	
	
	
	public ActionForward delete_ok(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		return mapping.findForward("delete_ok");
	}	
	
	
	public ActionForward download(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		//다운로드가 되어싿고 해서 창이 다른 곳으로 이동하면 안되기 때문에 null을 반환값으로 넣는다.
		return mapping.findForward(null);
	}	
	
	
	
	
	
}
