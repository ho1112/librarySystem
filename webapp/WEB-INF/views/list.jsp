<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#wrapper{
		width: 850px;
		height:1000px;
	}


</style>
</head>
<script src="resources/jquery-3.2.1.min.js"></script>
<script>


</script>
<body>
<div id="wrapper">
<select>
	<option value="title">제목</option>
	<option value="company">출판사</option>
	<option value="writer">저자</option>
	<option value="lental">대출가능</option>
</select>
<!-- 검색 -->
	<table id="searchbar" border="1">
	<tr><td>
	<select name="searchtype"> 
		<option value="title" ${searchtype=='title' ? 'selected' : ''}>제목</option>
		<option value="custid" ${searchtype=='custid' ? 'selected' : ''}>작성자</option>
		<option value="content" ${searchtype=='content' ? 'selected' : ''}>내용</option>
	</select>
	<input type="text" name="searchword" value="${searchword}"/> 
	</td></tr></table>
	<input class="search" type="button" value="검색" />
<!-- 페이지 출력 수 -->
	<select id="pagecontrol" name="pagecontrol" onchange="pagechange()">
		<option id="10" value="10" ${countpage=='10' ? 'selected' : ''}>10개씩 보기</option>
		<option id="15" value="15" ${countpage=='15' ? 'selected' : ''}>15개씩 보기</option>
		<option id="20" value="20" ${countpage=='20' ? 'selected' : ''}>20개씩 보기</option>
		<option id="25" value="25" ${countpage=='25' ? 'selected' : ''}>25개씩 보기</option>
		<option id="30" value="30" ${countpage=='30' ? 'selected' : ''}>30개씩 보기</option>
	</select>
<br>
<hr>
<!-- 목록 -->
<div>
	<table border="1">
		<tr>
			<td>이미지</td>
			<td>제목</td>
			<td>출판사</td>
			<td>저자</td>
			<td>대출가능</td>
		</tr>
		<!-- 반복문 돌려서 리스트 출력 -->
		
	</table>
</div>

<input type="button" value="맨 위로">
</div>
</body>
</html>