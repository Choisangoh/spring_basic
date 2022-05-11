<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>member 주소</h1>
	
	<sec:authorize access="hasAnyRole('ROROLE_ADMIN'">
		<a href="/secu/admin"> 관리페이지로 이동</a>
	</sec:authorize>
</body>
</html>