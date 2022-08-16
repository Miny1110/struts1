package com.fileTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

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
