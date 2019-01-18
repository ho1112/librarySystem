<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="resources/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	$("#cancel").on("click",cancel)
	$("#check").on("click",check)
	//$("#joinBtn").on("click",join)
});


function check(){
	var id = $("#userid").val();
	$.ajax({
		url:"check"
		,method:"get"
		,data: "id="+id
		,dataType:"text"
		,success : function(resp){
			alert(resp)
		}
	});
}

  /* function join(){
	var pw= $("#pw").val();
	var pwch=$("#pwch").val();
	if(pw != pwch){
		alert("비밀번호를 확인해주세요");
		return;
	}
	var form= $("#joinForm").serialize();
	var id=$("#userid").val();
	var file= $("#file").val();
	alert(form);
	$.ajax({
		url : "join",
		type : "post",
		data : "users="+form+"&id="+id
		,dataType:"text"
		,success : function(resp){
			alert(resp);
			location.href="${pageContext.request.contextPath}/"
		},
		error : function(resp){
			alert(resp);
			return
		}
	});
}   */

function cancel(){
	alert("취소");
	location.href="${pageContext.request.contextPath}/"
}

</script>
<body>
<h2>[ 회 원 가 입  ]</h2>
<form id="joinForm" action="join" method="POST" enctype="multipart/form-data">
<table>
	<tr>
		<td>아이디</td>
		<td><input type="text" id="userid" name="userid"></td>
		<td><input type="button" id="check" value="중복체크" />
	</tr>
	<tr>
		<td>패스워드</td>
		<td><input type="password" id="pw" name="password"></td>
	</tr>	
	<tr>
		<td>패스워드 확인</td>
		<td><input type="password" id="pwch"></td>
	</tr>
	<tr>
		<td colspan='2'><h2>[ 회 원 정 보 입 력  ]</h2></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" id="name" name="username"></td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td><input type="date" id="birth" name="birthdate"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td>
		<select id="tel1" name="tel1">
			<option value="010">010</option>
			<option value="011">011</option>
		</select>
		</td>
		<td><input type="text" id="tel2" name="tel2"></td>
		<td><input type="text" id="tel3" name="tel3"></td>
	</tr>
	<tr>
		<td>사진</td>
		<td><input id="file" type="file"  name="photo"></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" id="joinBtn" value="가입하기"></td>
		<td><input type="button" id="cancel" value="취소"></td>
	</tr>
</table>
</form>

</body>
</html>