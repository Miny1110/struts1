<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="<%=cp%>/write_ok.do" method="post">

아이디: <input type="text" name="userId"/><br/>
패스워드: <input type="password" name="userPwd"><br/>
이름: <input type="text" name="userName"><br/>
<input type="submit" value=" 전송 "><br/>

<%-- 전송을 누르면 <%=cp%>/write_ok.do를 실행한다.
그런데 .do가 있으니까 다시 /WEB-INF/struts-config_test.xml로 간다. --%>

</form>

</body>
</html>