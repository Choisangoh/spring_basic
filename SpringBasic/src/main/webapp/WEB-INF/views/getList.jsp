<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${array }<br/>
	<!-- 위 array를 c:forEach를 이용해 하나하나 나열하기 -->
	<c:forEach var="abc" items="${array }">
		${abc }<br/>
	</c:forEach>
</body>
</html>