<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Ajax 테스트</h2>
	
	<div>
		<div>
			댓글 쓴 사람 <input type="text" id="newReplyer">
		</div>
		<div>
		 	댓글 내용 <input type="text" id="newReply">
		</div>
		<button id="replyAddBtn">댓글 추가</button>
	</div>
	
	<!-- jquery cdn -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script type="text/javascript">
	var bno = 196626;
		$("#replyAddBtn").on("click", function(){
			
			var replyer = $("newReplyer").val();
			var reply = $("newReply").val();
			
			$.ajax({
				type : 'post',
				url : '/replies',
				headers : {
					"content-type" : "application/json",
					"X_HTTP_Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : bno,
					replyer : replyer,
					reply : reply
				}),
				success : function(result){
					if(result == 'SUCCESS'){
						alert("댓글 등록 완료")
					}
				}
			});
		});
	</script>
	
</body>
</html>