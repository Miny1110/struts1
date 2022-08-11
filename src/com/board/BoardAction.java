package com.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BoardAction extends DispatchAction {
//dispatchAction을 상속받으면 if-elseif로 uri에서 주소를 찾아서 실행하던 서블릿 방식과는 달리 그걸 메소드화 해서 사용한다.
//기존 방식처럼 쓰려면 Action을 상속받으면 된다. (TestAction 파일 참고)
	
	public ActionForward created(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		
		
		
		
		return mapping.findForward("created");
		
	}
}

