<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script  type="text/javascript"  src="libs/jquery-1.9.1.min.js"> </script>
<script type="text/javascript">
$(function(){
	// 서버로 보낼 데이터
	var param = { cate : "book", name : "hong"};
	
	// 비동기 통신 ajax
 	$.ajax({
 		type 		 :  'post',				// 전송방식(GET/POST) -> 지정하는 이유 : 보내는 데이터 양, 브라우저마다 다른 디폴트값 ... 때문에 지정해야 함
 		data 		 :  param,				// 서버로 보낼 데이터
 		url 		 :  '01_server.jsp', // 서버에 요청할 페이지
 		success :  parseData,		// 성공했을 때 연결할 함수
 		error		 :  function(){		// 실패했을 때 연결할 함수
 			alert("error");
 		}
 	});
	
 	function parseData(strText){
		
		// alert( strText );
		
		var aryData = strText.split("|");
					
		for(var i=0;i<aryData.length;i++){
			var param  = aryData[i].split("=");				
			if( param[0].trim() == 'cate'){  // 공백제거를 하지 않으면 처음에 공백에 들어와서 cate를 찾지 못함
				 document.getElementById("cate").value = param[1];
			}
			
			if( param[0].trim() == 'name'){
				document.getElementById("name").value = param[1];
			}
		
		}
		
	}
})

</script>
</head>


<body>
서버로부터 넘겨받은 데이터<br/>
첫번째 데이터 : <input type="text" name="" id="cate"/><br/>
두번째 데이터 : <input type="text" name="" id="name"/><br/>
</body>
</html>


