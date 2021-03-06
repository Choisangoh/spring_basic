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
	<h1>Upload with Ajax</h1>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<div class="uploadResult">
		<ul>
			<!-- 업로드된 파일이 들어갈 자리 -->
		</ul>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<!-- jquery  cdn 가져오기 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script>
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
				if(!obj.image) {
					
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
					
					str += "<li><a href='/download?fileName=" 
						+ fileCallPath 
						+ "'>"
						+ "<img src='/resource/free-icon-attached-file-1209914.png'>"
						+ obj.fileName 
						+ "</a>"
						+ "<span data-file=\'"
						+ fileCallPath
						+ "\' data-type='file'> X </span>"
						+ "</li>";
				}else {
					// str += "<li> + obj.fileName + "</li>";
					// 수정 코드
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
					var fileCallPathOriginal = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
					
					str += "<li><a href='/download?fileName="
						+ fileCallPath
						+ "'>"
						+ "<img src='display?fileName="
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
				dataType : 'text',
				type : 'POST',
				success : function(result){
					alert(result);
					targetLi.remove();
				}
			}); // ajax
		}); // click span
		
	</script>

</body>
</html>