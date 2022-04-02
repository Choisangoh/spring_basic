<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/userInfo" method="post">
		유저번호 : <input type="number" name="userNum" placeholder="유저번호"><br/>
		유저아이디 : <input type="text" name="userId" placeholder="유저아이디"><br/>
		<input type="submit" value="회원정보 제출"/>
	</form>
</body>
</html>