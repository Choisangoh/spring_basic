<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
			<h1 class="text text-primary">${board.bno }번글 조회중</h1>
			<div class="row">
				<div class="col-md-9">
					<input type="text" class="form-control" value="제목 : ${board.title }" />
				</div>
				<div class="col-md-3">
					<input type="text" class="form-control" value="글쓴이 : ${board.writer }" />
				</div>
			</div>
			<textarea rows="10" class="form-control">${board.content }</textarea>
			<div class="row">
				<div class="col-md-3">쓴날짜 : </div>
				<div class="col-md-3">${board.regdate }</div>
				<div class="col-md-3">수정날짜 : </div>
				<div class="col-md-3">${board.updatedate }</div>
			</div>
			<div class="row">
				<div class="col-md-1">
					<a href="/board/boardList?pageNum=${param.pageNum == null ? 1 : param.pageNum}&searchType=${param.searchType }&keyword=${param.keyword}" class="btn btn-success btn-sm">글목록</a>
				</div>
				<div class="col-md-1">
					<form action="/board/boardDelete" method="post">
						<input type="hidden" value="${board.bno }" name="bno" />
						<input type="hidden" name="pageNum" value="${param.pageNum}" />
						<input type="hidden" name="searchType" value="${param.searchType}" />
						<input type="hidden" name="keyword" value="${param.keyword}" />
						<input type="submit" value="삭제" class="btn btn-danger btn-sm">
					</form>
				</div>
				<div class="col-md-1">
					<form action="/board/boardUpdateForm" method="post">
						<input type="hidden" name="bno" value="${board.bno }" />
						<input type="hidden" name="pageNum" value="${param.pageNum}" />
						<input type="hidden" name="searchType" value="${param.searchType}" />
						<input type="hidden" name="keyword" value="${param.keyword}" />
						<input type="submit" value="수정" class="btn btn-warning btn-sm">
					</form>
				</div>
			</div>
		
		
		
		<!-- 댓글이 추가될 공간 -->
			<div class="row">
			<ul id="replies">
				
			</ul>
			
			
			<!-- 댓글 작성 공간 -->
			<div>
				<div>
					댓글 글쓴이 <input type="text" name="replyer" id="newReplyWriter">
				</div>
				<div>
					댓글 내용 <input type="text" name="reply" id="newReplyText"> 
				</div>
				<button id="replyAddBtn">댓글 추가</button>
			</div>
			
			<!-- modal 창 띄우기 -->
			<div id="modDiv" style="display:none;">
				<div class="modal-title"></div>
				<div>
					<input type="text" id="reply">
				</div>
				<div>
					<button id="replyModBtn">수정</button>
					<button id="replyDelBtn">삭제</button>
					<button id="closeBtn">닫기</button>
				</div>
			</div>
		
			</div><!-- class=row 닫는 부분 -->
	</div><!-- 컨테이너가 닫히는 부분 -->
	
	<!-- jquery cdn 가져오기 -->  
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	 
	<!-- 여기부터는 댓글 비동기 처리 자바스크립트 처리 영역 -->
	<script>
		// 댓글 전체 목록
		let bno = ${board.bno};  // let 또는 var 둘다 사용 가능 
		function getAllList(){
			$.getJSON("replies/all" + bno, function(data){
				let str = "";
				$(data).each(function(){
					// 날짜 처리를 위해 자바스크립트의 Date 객체를 이용
					let timestamp = this.updateDate;
					let date = new Date(timestamp);
					
					let formattedTime = "게시일 : "
									  + date.getFullYear() // 년도
									  + "/" 
									  + (date.getMonth()+1) // 월
									  + "/"
									  + date.getDate() // 일
									  + "-"
									  + date.getHours() // 시
									  + ":"
									  + date.getMinute() // 분
									  + ":"
									  + date.getSeconds() // 초
									  
					str += "<div class='replyLi' data-rno='" 
						+ this.rno 
						+ "'><strong>@"
						+ this.replyer 
						+ "</strong> - "
						+ formattedTime
						+ "<br>"
						+"<div class='reply'>"
						+ this.reply
						+ "</div>"
						+ "<button class='btn btn-info'>수정/삭제</button>"
						+ "</div>";						
				});
				$("#replies").html(str);	
			});
		}
		getAllList();
		
		// 댓글 작성 로직
		$("#replyAddBtn").on("click", function(){			
			var replyer = $("#newReplyWriter").val();
			var reply = $("#newReplyText").val();
			$.ajax({
				type : 'post',
				url : '/replies',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : bno,
					replyer : replyer,
					reply : reply
				}),
				success : function(result){
					if(result == 'SUCCESS'){
						alert("등록 되었습니다.");
						getAllList();
						$("#newReplyWriter").val("");
						$("#newReplyText").val("");
					}
				}
			});					
		});
		
		// 이벤트 위임
		$("#replies").on("click", ".replyLi button", function(){
			
			var replytag = $(this).parent();
			// this(button)의 부모(.replyLi)가 아닌 
			// 형제 태그 .reply의 내용을 대신 가져올 수 있도록
			// 변수 replyContent를 선언해 거기에 저장하기
			// 힌트는 .sibling("요소명"); 으로 형제태그를 가져올 수 있다.
			
			var rno = replytag.attr("data-rno");
			var reply = replytag.text();
			
			alert(rno + " : " + reply);
			
			$(".modal-title").html(rno); 
			$("#reply").val(reply); 
			$("#modDiv").show("slow") 
		});
		
		// 모달 창 닫기 이벤트
		$("#closeBtn").on("click", function(){ 
			$("#modDiv").hide("slow"); 
		});
		
		// 삭제 로직
		$("#replyDelBtn").on("click", function(){
				let rno = $(".modal-title").html();		
				$.ajax({
					type : 'delete',
					url : '/replies/' + rno,
					headers : {
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : 'text',
					success : function(result) {
						comsole.log("result: " + result)
						if(result == 'SUCCESS'){
							alert("삭제 되었습니다");
							$("#modDiv").hide("slow");
							getAllList();						
						}
					}
				}),		
			});	
		
			// 수정 로직
			$("#replyModBtn").on("click", function(){
				let rno = $(".modal-title").html();
				let reply = $("#reply").val();
			
				$.ajax({
					type : 'delete',
					url : '/replies/' + rno,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "PATCH"
					},
					contentType : "application/json",
					data : JSON.stringify({reply:reply}),
					dataType : 'text',
					success : function(result) {
						console.log("result: " + result); 
						if(result == 'SUCCESS'){
							alert("수정 되었습니다");
							$("#modDiv").hide("slow");
							getAllList();
						}
					}
				}),		
			});
			
	</script>
	
</body>
</html>