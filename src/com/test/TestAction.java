package com.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TestAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String uri = request.getRequestURI();
		
		if(uri.indexOf("/write_ok.do")!=-1) {
			
			TestForm f = (TestForm)form;
			//사용자가 보낸걸 받아서 downcast

			request.setAttribute("vo", f);
			
			return mapping.findForward("ok");
			
		}
		
		return mapping.findForward("error");
		
	}
	
		/*uri에 /write_ok.do가 있으면 form을 읽어서 downcast한 후에 vo에 담아서 jsp로 보낸다.
		ok라는 문자를 가지고 mapping(/WEB-INF/struts-config_test.xml)으로 돌아간다.*/

}
