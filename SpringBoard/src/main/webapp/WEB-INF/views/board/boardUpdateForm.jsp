<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/boardUpdate" method="post">
		<input type="hidden" name="bno" value="${board.bno }" >
		<input type="text" name="title" value="${board.title }" >
		<input type="text" name="writer" value="${board.writer }" ><br/>
		<textarea  name="content" rows="20" cols="100">${board.content }</textarea><br/>
		<!-- 수정을 했다면, 수정완료후에도 페이지번호, 검색조건, 검색어가 유지되도록 
		전달받은 데이터를 폼으로 다시 넘겨줘야 함. -->
		<input type="hidden" value="${param.pageNum}" name="pageNum">
		<input type="hidden" value="${param.searchType}" name="searchType">  		
		<input type="hidden" value="${param.keyword}" name="keyword">
		<input type="submit" value="수정">
		<input type="reset" value="초기화">
	</form>
</body>
</html>