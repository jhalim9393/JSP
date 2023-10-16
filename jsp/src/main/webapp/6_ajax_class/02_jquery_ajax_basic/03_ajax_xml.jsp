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
		
		var param = { cate : "book", name : "hong"};
		
		$.ajax({
			type			:	'get',
			data			:	param,				// 서버로 보내는 데이터
			url				:	'03_server.jsp',
			dataType	:	'xml',				// 서버에서 받은 데이터의 타입
			success	:	parseData
		});
		
		
		function parseData(result){
			$('#cate').val( $(result).find('first').text() );
			console.log(result);
		}
		
	});
	</script>
</head>

<body>
서버로부터 넘겨받은 데이터<br/>
첫번째 데이터 : <input type="text" name="" id="cate"/><br/>
두번째 데이터 : <input type="text" name="" id="name"/><br/>
</body>
</html>


