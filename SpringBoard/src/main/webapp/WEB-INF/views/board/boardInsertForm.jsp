<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- /boardInsert로 보내는 post방식 폼 생성
	폼에서 보내는 요소와 name속성값은 쿼리문 참조해서 작성
	insert로직을 실행하는 컨트롤러도 생성하고 return값은 "";로 우선 설정 -->
	<form action="/boardInsert" method="post">
		<input type="text" name="title" placeholder="제목">
		<input type="text" name="writer" placeholder="글쓴이"><br/>
		<textarea name="content" rows="20" cols="100"></textarea><br/>
		<input type="submit" value="작성">
		<input type="reset" value="초기화">
	</form>
</body>
</html>