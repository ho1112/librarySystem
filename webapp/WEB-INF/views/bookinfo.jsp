<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#header{
		width:100%;
		height:150px;
	}
	#table{
		width:250px;
		height:800px;
		float: left;
	}
	#main{
		width:1100px;
		height:500px;
		float: right;
	}
	#st{
		width : 500px;
	}
	
</style>
</head>
<script src="resources/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	
	$("#loginBtn").on("click",login)
	$("#joinBtn").on("click",join)
	$("#logout").on("click",logout)
	$("#uu").on("click",update)
	$("#lend").on("click",lend)
});

function lend(){
	var bnum= ${book.num};
	var unum =${users.num};
	alert(bnum+"/"+unum);
	$.ajax({
		url:"lend"
		,method:"get"
		,data: "bnum="+bnum+"&unum="+unum
		,dataType:"text"
		,success:function(resp){
			alert(resp);
		},error:function(resp){
			alert(resp);
		}
	});
}

function join(){
	location.href="${pageContext.request.contextPath}/joinForm"
}

function login(){
	var id = $("#id").val();		
	var pw = $("#pw").val();
	$.ajax({
		url:"login"
		, method:"post"
		,data:"id="+id+"&pw="+pw
		,dataType:"json"
		,success: function(resp){
			alert("로그인 성공");
			location.href="${pageContext.request.contextPath}/";
		}
		,error: function(){
			alert("errrrr");
		}
	});
}

function logout(){
	$.ajax({
		url:"logout"
		,method:"get"
		,dataType:"text"
		,success :function(resp){
			alert(resp);
			location.href="${pageContext.request.contextPath}/"
		}
		,error:function(){
			alert("실패");
		}
	});
}
function update(){
	location.href="${pageContext.request.contextPath}/updateForm"
}


function searchlist(resp){
	var stable = "<table id='st' border='1'>";
	stable += "<tr><th>이미지</th><th>제목</th><th>출판사</th><th>작가</th><th>대출가능</th>";
	$.each(resp, function(index,item){	
	stable += "<tr><td>"+"<img src='"+item.imageurl+"'</td>";
	stable += "<td>"+item.title+"</td>";
	stable += "<td>"+item.publisher+"</td>";
	stable += "<td>"+item.author+"</td>";
	stable += "<td>"+item.status+"</td></tr>";
	})
	stable += "</table>";
	$(".listdiv").html(stable);
}


</script>
<body>
${m}
<div id="wrapper">
<div id="header">
<h2>[ 도 서 관  ]</h2>
<hr>
</div>
<div id="table">
<c:if test="${empty loginId}">
<table id="logta" border='1'>
	<tr>
		<td>아이디</td>
		<td><input id="id" type="text"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input id="pw" type="text"></td>
	</tr>
	<tr>
		<td><input id="joinBtn" type="button" value="회원가입"></td>
		<td><input id="loginBtn" type="button" value="로그인"></td>
	</tr>
</table>	
</c:if>
<c:if test="${not empty loginId}">
<table id="logta" border='1'>
		<tr>
			<td rowspan='2'><img src ="download?id=${loginId}" /></td>
			<td rowspan='2'>${users.username} 님 로그인 중<br>회원번호${users.num}</td>
		<tr>
			<td><input id='uu' type='button' value='회원정보수정'>	
				<input id='logout' type='button' value='로그아웃'>
			</td>
		</tr>	
</table>
</c:if>
</div>

<div id="main">

<!-- 목록 -->
<div class="listdiv">
<table>
	<tr>
		<th>도서정보</th>
	</tr>
	<tr>
		<td><img src="${book.imageurl}"></td>
		<td>제목</td>
		<td>${book.title}</td>
	</tr>
	<tr>
		<td>출판사</td>
		<td>${book.publisher}</td>
	</tr>
	<tr>
		<td>저자</td>
		<td>${book.author}</td>
	</tr>
	<tr>
		<td>대출가능여부</td>
		<td>${status}</td>
		<td><c:if test="${not empty loginId}">
		<input id="lend" type="button" value="대출신청"></c:if></td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${book.content}</td>
	</tr>
</table>


</div>
</div>
</div><!-- end wrapper -->
</body>
</html>