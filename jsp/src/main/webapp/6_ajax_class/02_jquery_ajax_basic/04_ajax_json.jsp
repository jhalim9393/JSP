<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script  type="text/javascript"  src="libs/jquery-1.9.1.min.js"> </script>
	<script type="text/javascript">
	    
	$(function(){
		
		/*
			json 구조로 통신을 하려면 관련 라이브러리 필요함
			dataType : 'json'
		*/
		
		$.ajax({
			type			:	'post',
			data			:	{ cate : "book", name : "hong"},
			url				:	'04_server.jsp',
			dataType	:	'text',		
			success	:	function(result){
				var obj = {};
				obj = eval("(" + result + ")");
				// 추후에는 'json'으로 받아서 바로 사용가능
				
				$("#cate").val(obj.first);
			}
		});
		
		
		
		
	});
	</script>
</head>

<body>
서버로부터 넘겨받은 데이터<br/>
첫번째 데이터 : <input type="text" name="" id="cate"/><br/>
두번째 데이터 : <input type="text" name="" id="name"/><br/>
</body>
</html>


