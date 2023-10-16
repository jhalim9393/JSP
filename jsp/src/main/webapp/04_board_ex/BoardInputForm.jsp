<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글쓰기</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	// 작성 버튼이 눌렸을 때
	$('input[value="작성"]').click(function(){
		// 폼태그의 action 속성을 'BoardSave.jsp'
		// 폼태그의 submit() 호출
		var frm = $('form[name="frm"]');
		frm.attr('action','BoardSave.jsp');
		frm.submit();
		// 각 input 태그에 name 부여 
	});
});
</script>

<style type="text/css">
h4 {
	color : #2ABCB4;
}

</style>


</head>
 <body>
	<h4> 게시판 글 쓰기 </h4><br/>
	<form name='frm' method='post'>
		<table>
			<tr>
				<td>작성자 : </td>
				<td><input type='text' name='writer'><!-- vo에 있는 변수명과  name이 같아야함 --></td>
			</tr>
			<tr>
				<td>제  목 : </td>
				<td><input type='text' name='title'></td>
			</tr>
			<tr>
				<td>내  용 : </td>
				<td><textarea rows='10' cols='40' name='content'></textarea></td>
			</tr>
			<tr>
				<td>비밀번호 : </td>
				<td><input type='password' name='pass'></td>
			</tr>
			<tr>
				<td>
					<br/><input type='button' value='작성'>
                 	<input type='reset' value='취소'>
                </td>
			</tr>
		
		</table>

	</form>

</body>
</html>