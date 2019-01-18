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
	$("#sb").on("click",search)
	$("#alllist").on("click",allList)
	$("#lendhistory").on("click",lendhistory)
	
});

function lendhistory(){
	$.ajax({
		url:"lendhistory",
		method:"get",
		dataType:"json",
		success: history
		,error:function(){
			alert("대출이력이 없습니다");
		}
	});
}

function history(resp){
	var his = "";
	var st ="";
	var stable = "<table id='his' border='1'>";
	stable += "<tr><th>대출일</th><th>반납일</th><th>이미지</th><th>제목</th><th>상태</th>";
	$.each(resp, function(index,item){	
	stable += "<tr><td>"+item.startdate+"</td>";
	stable += "<td>"+item.returndate+"</td>";
		$.ajax({
			url:"lendbook",
			method:"get",
			async:false,
			data:"bnum="+item.booknum,
			dataType:"json",
			success: function(resp){
				his = resp;
			}
			});
	stable += "<td>"+his.imageurl+"</td>";
	stable += "<td>"+his.title+"</td>";
	if(item.status == 'returned' || item.status=='delay_returned'){
		st="Y";
	}else{
		st="N";
	}
	stable += "<td>"+st+"</td></tr>";
	})
	stable += "</table>";
	
	$(".listdiv").html(stable);
}
	



function allList(){
	$.ajax({
		url:"allList"
		,method:"get"
		,dataType:"json"
		,success: 
			outallList
		,error:function(){
			alert("책이 없습니다");
		}
	});
}




function outallList(resp){
	var st = "";
	var stable = "<table id='st' border='1'>";
	stable += "<tr><th>이미지</th><th>제목</th><th>출판사</th><th>작가</th><th>대출가능</th>";
	$.each(resp, function(index,item){	
	stable += "<tr><td>"+"<a href='bookinfo?num="+item.num+"'><img src='"+item.imageurl+"'></a></td>";
	stable += "<td>"+"<a href='bookinfo?num="+item.num+"'>"+item.title+"</a></td>";
	stable += "<td>"+item.publisher+"</td>";
	stable += "<td>"+item.author+"</td>";
		$.ajax({
			url:"statusinfo",
			method:"get",
			async:false,
			data:"bnum="+item.num,
			dataType:"text",
			success: function(resp){
				st = resp;
			}
			});
	stable += "<td>"+st+"</td></tr>";
	})
	stable += "</table>";
	$(".listdiv").html(stable);
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

function search(){
	var sct = $("#searchtype").val();
	var scw = $("#searchword").val();
	$.ajax({
		url:"search"
		,method:"post"
		,data:"searchtype="+sct+"&searchword="+scw
		,dataType:"json"
		,success: 
			searchlist
		,error:function(){
			alert("검색결과가 없습니다")
		}
	});
}

function searchlist(resp){
	var stable = "<table id='st' border='1'>";
	stable += "<tr><th>이미지</th><th>제목</th><th>출판사</th><th>작가</th><th>대출가능</th>";
	$.each(resp, function(index,item){	
	stable += "<tr><td>"+"<a href='bookinfo?num="+item.num+"'><img src='"+item.imageurl+"'></a></td>";
	stable += "<td>"+"<a href='bookinfo?num="+item.num+"'>"+item.title+"</a></td>";
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
${r}
<div id="wrapper">
<div id="header">
<h2>[ 도 서 관  ]</h2>
<c:if test="${not empty loginId}">
<input id="alllist" type="button" value="도서목록" />
<input id="lendhistory" type="button" value="대출이력" />
</c:if>
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
<div class="util">
	<form id="search" method="POST" >
	<select id="searchtype"  name="searchtype"> 
		<option value="title" ${searchtype=='title' ? 'selected' : ''}>제목</option>
		<option value="publisher" ${searchtype=='publisher' ? 'selected' : ''}>출판사</option>
		<option value="author" ${searchtype=='author' ? 'selected' : ''}>저자</option>
		<option value="status" ${searchtype=='status' ? 'selected' : ''}>대출가능</option>
	</select>
	<!-- 검색 -->
	<table id="searchbar" border="1">
	<tr><td>
	<input type="text" id="searchword" name="searchword" value="${searchword}"/> 
	</td></tr></table>
	<input id="sb" type="button" value="검색" />
	</form>
	<!-- 페이지 출력 수 -->
	<select id="pagecontrol" name="pagecontrol" onchange="pagechange()">
		<option id="10" value="10" ${countpage=='10' ? 'selected' : ''}>10개씩 보기</option>
		<option id="15" value="15" ${countpage=='15' ? 'selected' : ''}>15개씩 보기</option>
		<option id="20" value="20" ${countpage=='20' ? 'selected' : ''}>20개씩 보기</option>
		<option id="25" value="25" ${countpage=='25' ? 'selected' : ''}>25개씩 보기</option>
		<option id="30" value="30" ${countpage=='30' ? 'selected' : ''}>30개씩 보기</option>
	</select>
</div>

<!-- 목록 -->
<div class="listdiv">
</div>
<input type="button" value="맨 위로">
</div>
</div><!-- end wrapper -->
</body>
</html>