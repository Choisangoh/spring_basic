<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- /score라는 공통 주소를 쓰되, get, post접속으로 폼 접속인지, 결과 확인인지 나뉜다.
따라서 폼 접속을 이미 했다면 목적지는 공통주소인 /score가 되고, 
post방식으로 전송하도록 하면 결과페이지에 도달할 수 있다. --%>
	<form action="/score" method="post">
		<input type="number" max="100" min="0" name="math" placeholder="수학">
		<input type="number" max="100" min="0" name="eng" placeholder="영어"><br/>
		<input type="number" max="100" min="0" name="lang" placeholder="언어">
		<input type="number" max="100" min="0" name="social" placeholder="사탐">
		<input type="number" max="100" min="0" name="computer" placeholder="컴퓨터"><br/>
		<input type="submit" value="성적 확인">
	</form>
</body>
</html>