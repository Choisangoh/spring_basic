<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.uploadResult {
		width:100%;
		background-color:lightyellow;
	}
	.uploadResult ul {
		display:flex;
		flex-flow:row;
		justify-content:center;
		align-items: center;
	}
	.uploadResult ul li{
		list-style:none;
		padding: 10px;
	}
	.uploadResult ul li img{
		width:20px;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- /boardInsert로 보내는 post방식 폼 생성
	폼에서 보내는 요소와 name속성값은 쿼리문 참조해서 작성
	insert로직을 실행하는 컨트롤러도 생성하고 return값은 "";로 우선 설정 -->
	<form action="board/boardInsert" method="post">
		<input type="text" name="title" placeholder="제목">
		<input type="text" name="writer" placeholder="글쓴이"><br/>
		<textarea name="content" rows="20" cols="100"></textarea><br/>
		<input id="submitBtn" type="submit" value="작성">
		<input type="reset" value="초기화">
	</form>
	
	<h2>첨부파일 영역</h2>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<div class="uploadResult">
		<ul>
			<!-- 업로드된 파일이 들어갈 자리 -->
		</ul>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script>
	
	var csrfHeaderName = "${_csrf.headerName}"
	var csrfTokenValue="${_csrf.token}" 
	
	$(document).ready(function(){
								// .(아무문자한개)*(갯수제한없음) \.(.을 아무문자1개가 아닌. 자체로 쓸때)
								//"(.*?)@(.*?)\.(com|net|기타확장자)$" <- 이메일 검증 정규표현식
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; //5MB
		
		function checkExtension(fileName, fileSize){
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드할 수 없습니다. ");
				return false;
			}
			return true;
		}
			// 첨부가 안 된 상태의 .uploadDiv를 깊은 복사해서 
			// 첨부 완료 후에 안 된 시점의 uploadDiv로 덮어씌우기
			var cloneObj = $(".uploadDiv").clone();	
		
			$('#uploadBtn').on("click", function(e){
					
				
				var formData = new FormData();
				
				var inputFile = $("input[name='uploadFile']");
				
				var files = inputFile[0].files;
				
				console.log(files);
				
				// 파일 데이터를 폼에 집어넣기
				for(var i=0; i<files.length; i++){
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					}
				
					formData.append("uploadFile", files[i]);
				}
				$.ajax({
					url: '/uploadAjaxAction',
					processData: false,
					contentType: false,
					data: formData,
					type: 'POST',
					beforeSend : function(xhr) {
						 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
					},
					dataType:'json',
					success : function(result){
						console.log(result);
						showUploadedFile(result);
					// 업로드 성공시 미리 복사해둔 uploadDiv 로 덮어씌워서 첨부파일이 없는 상태로 되돌려 놓기
						$(".uploadDiv").html(cloneObj.html());
						alert("Uploaded");
						
					}
				}); //ajax
			});
			
		}); //onclick uploadBtn
		var uploadResult = $(".uploadResult ul");
		
		function showUploadedFile(uploadResultArr){
			var str="";
			
			$(uploadResultArr).each(function(i, obj){
				// BoardAttachVO내부에서 이미지여부를 fileType변수에 저장하므로 obj.image를 obj.fileType로 전환
					if(!obj.fileType) {
					
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
					
					str += "<li" 
						+ "data-path='" 
						+ obj.uploadPath
						+ "'data-uuid'"
						+ obj.uuid
						+ "'data-filename'"
						+ obj.fileName
						+ "'data-type'"
						+ obj.fileType
						+ "'>"
						+ "<img src='/resource/free-icon-attached-file-1209914.png'>"
						+ obj.fileName 
						+ "</a>"
						+ "<span data-file=\'"
						+ fileCallPath
						+ "\' data-type='file'> X </span>"
						+ "</li>";
				}else { 
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
					var fileCallPathOriginal = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
					
					str += "<li"
						+ "data-path='" 
						+ obj.uploadPath
						+ "'data-uuid'"
						+ obj.uuid
						+ "'data-filename'"
						+ obj.fileName
						+ "'data-type'"
						+ obj.fileType
						+ "'><a href='/download?fileName="	
						+ fileCallPathOriginal
						+ "'>"
						+ "<img src='/display?fileName="
						+ fileCallPath
						+ "'>"
						+ obj.fileName
						+ "</a>"
						+ "<span dat-file=\'"
						+ fileCallPath
						+ "\' data-type='image'> X </span>"
						+ "</li>";
				}
			});
			uploadResult.append(str);
		}// showUploadedfile
		
		
		$(".uploadResult").on("click", "span", function(e){
			var targetFile = $(this).data("file");
			var type = $(this).data("type");
			
			var targetLi = $(this).closest("li");
			
			$.ajax({
				url : 'deleteFile',
				data : {fileName: tagetFile, type: type},
				beforeSend : function(xhr) {
					 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				},
				dataType : 'text',
				type : 'POST',
				success : function(result){
					alert(result);
					targetLi.remove();
				}
			}); // ajax
		}); // click span	
		
		//제출버튼 누를 경우, 첨부파일 정보를 폼에 추가해서 전달하는 코드
		$("#submitBtn").on("click", function(e){
			// 1. 제출버튼을 눌렀을 때 바로 작동하지 않도록 기능막기
			e.preventDefault();
			
			// 2. var formObj = $("form"); 로 폼태그를 가져옴
			var formObj = $("form");
			
			$(".uploadResult ui li").each(function(i, obj){
				console.log($(obj));
			})
		}); 
	</script>
</body>
</html>