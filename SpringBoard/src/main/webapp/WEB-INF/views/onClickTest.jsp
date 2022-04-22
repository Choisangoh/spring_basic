<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ui id="replies">
	
	</ui>
	<button id="requestBtn">댓글 로딩해오기</button>
	
	<!-- jquery cdn -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- script 태그에 #requsetBtn에 대한 onclick 이벤트 걸기
	onclick시 $getJSON을 이용해 댓글 데이터를 요청한다음(글번호는 test.jsp에 설정해둔 글번호 그대로 쓰기)
	얻어온 데이터를 이용해 #replies 내부에 댓글 집어넣기 -->
	<script type="text/javascript">
	var bno = 196626;
		
		// onclick이 걸려있으므로, 실행 조건은 클릭 시 실행
		$("#requestBtn").on("click", function(){
			// #replies 사이에 들어갈 태그를 저장하는 변수
			str = "";
			
			// #requestBtn 클릭 시
			$.getJSON("/replies/all/" + bno, function(data){
				// 댓글 집어넣어주는 로직 실행	
				console.log(data);
				$(data).each(function(){
					// str에 적절하게 댓글형식으로 문자열을 추가한 다음
					str += "<li>" + this.rno + ":" + this.reply + "</li>";
					console.log(str);
					console.log("---------------");
				});
				// #replies에 댓글 끼워넣기
				$("#replies").html(str);
			});
		});
	</script>
	
</body>
</html>