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
	updateinfo();
	var userid = "";
});

function updateinfo(){
	$.ajax({
		url:"updateinfo"
		,method:"get"
		,dataType:"json"
		,success: createTable
	});
}

function createTable(resp){
	var table = "<form id='upf' action='update' method='POST' enctype='multipart/form-data'><table><tr>";
	table += "<td>회원번호</td><td>"+resp.num+"</td>";
	table += "<tr><td>아이디</td><td id='id' name='userid'>"+resp.userid+"</td>";
	table += "<tr><td>기존 패스워드</td><td><input type='text' value='"+resp.password+"'></td></tr>";
	table += "<tr><td>새 패스워드</td><td><input id='password' type='text' name='password'></td></tr>";
	table += "<tr><td>새 패스워드 확인</td><td><input id='pwch' type='text'></td></tr>";
	table += "<tr><td>이름</td><td>"+resp.username+"</td></tr>";
	table += "<tr><td>생년월일</td><td>"+resp.birthdate+"</td>";
	table += "<tr><td>전화번호</td><td><select id='tel1' name='tel1'>";
	table += "<option value='010'>010</option><option value='011'>011</option></select></td>"
	table += "<td><input type='text' name='tel2' value='"+resp.tel2+"'></td><td><input type='text' name='tel3' value='"+resp.tel3+"'></td></tr>";
	table += "<tr><td>사진</td><td>#######</td><td><input type='file' name='photo'></td></tr>";
	table += "<tr><td><input id='updateBtn' type='submit' value='수정'></td></tr>";
	table += "</table></form>";
	
	$("#updatediv").html(table);
	userid = resp.userid;
	//$("#updateBtn").on("click",update);
}

function update(){
	var pw= $("#password").val();
	var pwch=$("#pwch").val();
	if(pw != pwch){
		alert("비밀번호를 확인해주세요");
		return;
	}
	var form=$("#upf").serialize();
	alert(form);
	$.ajax({
		url : "update",
		method : "post",
		data : "users="+form+"&id="+userid+"&pw="+pw
		,dataType:"text"
		,success : function(resp){
			alert(resp);
			location.href="${pageContext.request.contextPath}/"
		},
		error : function(){
			alert("error");
			return;
		}
	});
}


</script>

<body>
<h2>[ 회 원 정 보 수 정 화 면  ]</h2>
<div id="updatediv">

</div>	
</body>
</html>