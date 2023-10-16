<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function() { 
    function validateId() {
        var id = $("#id").val();
        var idPattern = /^[a-zA-Z0-9]{5,10}$/;
        if (!id.match(idPattern)) {
            $("#idError").text("아이디는 5자~10자의 영어와 숫자 조합이어야 합니다.");
        } else {
            $("#idError").text("");
        }
    }

    function validatePassword() {
        var pass = $("#pass").val();
        var passPattern = /^[a-zA-Z0-9]{4,}$/;
        if (!pass.match(passPattern)) {
            $("#passError").text("비밀번호는 4자 이상의 영어와 숫자 조합이어야 합니다.");
        } else {
            $("#passError").text("");
        }
    }

    function validatePasswordConfirmation() {
        var pass = $("#pass").val();
        var pass_chk = $("#pass_chk").val();
        if (pass !== pass_chk) {
            $("#passChkError").text("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        } else {
            $("#passChkError").text("");
        }
    }

    function validateName() {
        var name = $("#name").val();
        var namePattern = /^[가-힣]{,5}+$/;
        if (!name.match(namePattern)) {
            $("#nameError").text("이름은 한글로 5자 이내여야 합니다.");
        } else {
            $("#nameError").text("");
        }
    }

    function validatePhone() {
        var phone_num = $("#phone_num").val();
        var phonePattern = /^[0-9]*$/;
        if (!phone_num.match(phonePattern)) {
            $("#phoneNumError").text("전화번호는 숫자만 입력해야 합니다.");
        } else {
            $("#phoneNumError").text("");
        }
    }

    function validateAddress() {
        var addr = $("#addr").val();
        if (addr.length > 20) {
            $("#addrError").text("주소는 20자 이내여야 합니다.");
        } else {
            $("#addrError").text("");
        }
    }

    $("#id").blur(validateId);
    $("#pass").blur(validatePassword);
    $("#pass_chk").blur(validatePasswordConfirmation);
    $("#name").blur(validateName);
    $("#phone_num").blur(validatePhone);
    $("#addr").blur(validateAddress);
    
    var id_valid = $("table tr:eq(0) > td:eq(2) > input");
    id_valid.click(function(){
    	var Id = $("#id").val();
    	var url = "CheckId.jsp?userId=" + Id;
        window.open(url, "", "width=200,height=150");
    });
    
});
</script>

</head>
<body>

<form action="Join_success.jsp" method="post">
<h1>회원가입서 작성하기</h1>
    <table>
    <tr>
        <td>아이디</td>
        <td><input id="id" name="id" type="text" placeholder="5자~10자 영어와 숫자조합" required></td>
        <td><input type="submit" value="중복확인"><span id="idError" class="error"></span></td>
        <td></td>
    </tr>
    <tr>
        <td>비밀번호</td>
        <td><input id="pass" name="pass" type="password" placeholder="4자이상 영어와 숫자조합"  required></td>
        <td><span id="passError" class="error"></span></td>
    </tr>
    <tr>
        <td>비밀번호 확인</td>
        <td><input id="pass_chk" name="pass_chk" type="password" required></td>
        <td><span id="passChkError" class="error"></span></td>
    </tr>
    <tr>
        <td>이름</td>
        <td><input id="name" name="name" type="text" placeholder="한글 5자 이내" required></td>
        <td><span id="nameError" class="error"></span></td>
    </tr>
    <tr>
        <td>전화번호</td>
        <td><input id="phone_num" name="phone_num" type="text" placeholder="-없이 숫자조합" required></td>
        <td><span id="phoneNumError" class="error"></span></td>
    </tr>
    <tr>
        <td>주소</td>
        <td><input id="addr" name="addr" type="text" placeholder="20자 이내" required></td>
        <td><span id="addrError" class="error"></span></td>
    </tr>
    </table>
    
    <input type="submit" value='회원가입'>
    <a href="Join_view.jsp"><input type="reset" value='취소'></a>
</form>

</body>
</html>