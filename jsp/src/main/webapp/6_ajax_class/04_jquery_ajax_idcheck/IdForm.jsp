<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 아이디 중복 검사 </title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		// '중복체크' 버튼을 클릭했을 때
		
/* 		$("#id_check").click(function()*/
		
		//ajax의 극적인 효과를 위해 확인함 -> 제발 쓰지 마셈
		$('.userinput').keyup(function(){
			
			$.ajax({
				type			:	'get',
				url				:	'IdCheck.jsp',
				data			:	{ id : $('.userinput').val() }, // 뒷단에서 받아온 파라미터 이름으로 가져와야 함
				dataType	:	'text',
				success	:	function(data){
					//alert(data);
					if(data.trim()=='YES'){
						$('#idmessage').text('이미 사용 중인 아이디입니다.');
						$('#idmessage').show();
					}
					else{
						$('#idmessage').text('사용 가능한 아이디입니다.');
						$('#idmessage').show();
					}
				},
				error			:	function(err){
					console.log(err);
				}
			});
		});
		
});

</script>


</head>
<body>

<input name="id" type="text" class="userinput" size="15" />
<button type="button" id="id_check">중복체크</button><br/><br/>
<div id="idmessage" style="display:none;"></div>

</body>
</html>
