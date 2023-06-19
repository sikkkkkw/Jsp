<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="color.jspf"%>

<%
  int num = Integer.parseInt(request.getParameter("num"));
  String pageNum = request.getParameter("pageNum");

%>
<html>
<head>
<title>게시판</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0">
<script type="text/javascript">  
 <!--    
  function deleteSave(){	
	if(!document.delForm.passwd.value){
	   alert("비밀번호를 입력하십시요.");
	   document.delForm.passwd.focus();
	   return false;
    }
  }    
-->
</script>
</head>
<body>
<div class='decontainer'>

<header >
			<div class='banner_wrap'>
				<span class='title'>글삭제</span>		
			</div>

			<!-- 글목록 이동 -->

		</header>	
<br>
</div>
<form method="POST" name="delForm" 
   action="deletePro.jsp?pageNum=<%=pageNum%>" 
   onsubmit="return deleteSave()"> 
 <table style="border:1px solid black;margin-left:auto;margin-right:auto;"
 class='list'>
  <tr >
     <td align=center>
       <b>비밀번호를 입력해 주세요.</b></td>
  </tr>
  <tr>
     <td align=center>비밀번호 :   
       <input type="password" name="passwd" size="8" maxlength="12">
	   <input type="hidden" name="num" value="<%=num%>"></td>
 </tr>
 <tr >
    <td align=center>
      <input type="submit" class="fontw" value="글삭제" >
      <input type="button" class="fontw" value="글목록" 
       onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">     
   </td>
 </tr>  
</table> 
</form>

</body>
</html>