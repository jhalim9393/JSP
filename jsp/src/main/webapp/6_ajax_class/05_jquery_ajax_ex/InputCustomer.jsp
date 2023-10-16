<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 고객관리 프로그램 </title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" >

	$(function(){
		// '입력버튼'이 눌렸을 때
		$("#btnInsert").click(function(){
			// *****************
			// 원래 방식 (ajax 아님)
/* 			var form1 = $('form[name="inForm"]');
			form1.attr('action','DataInput.jsp'); // form 태그에 액션이 없어서 속성 추가
			form1.submit(); // form 태그에 액션이 없어서 속성 추가 */
			
			// 비동기 방식 (ajax)
			
			// (1) 사용자 입력값들을 객체형식(json)으로 만들기
			var param = {
				name 	:	$("#name").val(),
				age	 	:	$("#age").val(),
				tel	 	 	:	$("#tel").val(),
				addr	 	:	$("#addr").val()
			}
			
			// (2) 비동기 통신
			$.ajax({
				type			:	'post',
				data			:	 param,
				url				:	'DataInput.jsp',
				success	:	function(data){
					if(data.trim()=='1'){
						alert("디비입력성공");
					}
				}

			}); // end of ajax

		}); // end of $("#btnInsert").click
		
		
		// 가져오기 버튼이 눌렸을 때
		$("#btnSelect").click(function(){
			
			$.ajax({
				url				:	'DataSelect.jsp',
				dataType	:	'xml',     // 받을 때 무슨타입으로 받을지?
				success	:	selectResult
				
			});
			
			
			function selectResult(data){
				var person = $(data).find('person');
				
				$("#tbd").empty(); // id가 tbd인 애를 찾아서 자식을 다 날려버림
				
				// 테이블에 요소 붙이기
				person.each(function(){ // 하나씩 각자 처리하고 싶을 때 each
					var name = $(this).find('name').text();
					var age = $(this).find('age').text();
					var tel = $(this).find('tel').text();
					var addr = $(this).find('addr').text();
					
					$("#tbd").append('<tr>'
							+ '<td>' + name + '</td>'
							+ '<td>' + age + '</td>'
							+ '<td>' + tel + '</td>'
							+ '<td>' + addr + '</td>'
							+ '</tr>'
					
					);
					
				});
				
			}
			
		});
		
		
	});

</script>

</head>


<!-- <body> -->
<body>

<h2> 고객정보 입력 </h2>

<form name="inForm" method="post">
<table border = 1>
	<tr>
		<td width="80" align="center">Name</td>
		<td width="50" align="center">Age</td>
		<td width="100" align="center">Tel</td>	
		<td width="250" align="center">Addr</td>
	</tr>
	<tr>
		<td align="center"><input type="text" size="8" name="name" id="name"></td>
		<td align="center"><input type="text" size="4" name="age" id="age"></td>
		<td align="center"><input type="text" size="12" name="tel" id="tel"></td>
		<td align="center"><input type="text" size="30" name="addr" id="addr"></td>
	</tr>
	<tr>
		<td colspan="4" align="center"> 
			<input type="button" id='btnInsert' value="입력">
		</td>
	</tr>
</table>

</form>
<br>
<hr>

<h2> 고객정보 목록보기  </h2>
<table border='0' width="510"> 
	<tr>
		<td align="right"><input type="button"  id='btnSelect' value="가져오기"></td>
	</tr>
</table>
<table border = 1 id="listTable">
	<tr>
		<td width="80" align="center">Name</td>
		<td width="50" align="center">Age</td>
		<td width="100" align="center">Tel</td>	
		<td width="250" align="center">Addr</td>
	</tr>
	<tbody id='tbd'></tbody>
</table>
<div id="myDiv"> </div>

</body>
</html>