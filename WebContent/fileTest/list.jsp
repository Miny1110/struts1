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

<table width="500" align="center">
<tr height="30">
	<td align="right">
		<input type="button" value="파일 업로드" onclick="location.href='<%=cp%>/file.do?method=write';">
	</td>
</tr>
</table>

<table width="500" align="center" border="1" style="font-size: 10pt;"> 
<tr height="30">
	<td width="50">번호</td>
	<td width="200">제목</td>
	<td width="200">파일</td>
	<td width="50">삭제</td>
</tr>
</table>

</body>
</html>