<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "nboard.BoardDBBean" %>
<%@ page import = "nboard.BoardDataBean" %>
<%@ include file="color.jspf"%>
<html>
<head>
<title>게시판</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<div class='decontainer'>	
		<header >
			<div class='banner_wrap'>
			<!-- 리스트로 이동 -->
			<a href='Card.do'>
					<span class='back_button'><
					</span>
			</a>
				<span class='title'>글수정</span>
			
			<a href="list.jsp">
					<span  class='back_button3'>목록보기</span>
			</a>
					
			</div>
		</header>
<%
  int num = Integer.parseInt(request.getParameter("num"));
  String pageNum = request.getParameter("pageNum");
  try{
      BoardDBBean dbPro = BoardDBBean.getInstance(); 
      BoardDataBean article =  dbPro.updateGetArticle(num);

%>
<br>
<form method="post" name="writeform" 
action="updatePro.jsp?pageNum=<%=pageNum%>" onsubmit="return writeSave()">
<table style="border:1px solid black;margin-left:auto;margin-right:auto;">
  <tr>
    <td  width="70" class="fontw">이 름</td>
    <td width="300">
       <input type="text" size="41" maxlength="10" name="writer" 
         value="<%=article.getWriter()%>" style="ime-mode:active;"pattern=".{0,9}" placeholder="(9자 내외)">
	   <input type="hidden" name="num" value="<%=article.getNum()%>"></td>
  </tr>
  <tr>
    <td  width="70" class="fontw">제 목</td>
    <td width="300">
       <input type="text" size="41" maxlength="50" name="subject"
        value="<%=article.getSubject()%>" style="ime-mode:active;"></td>
  </tr>
  <tr>
    <td  width="70"  class="fontw">Email</td>
    <td width="300">
       <input type="email" size="41" maxlength="30" name="email" 
        value="<%=article.getEmail()%>" style="ime-mode:inactive;"></td>
  </tr>
  <tr>
    <td  width="70" class="fontw">내 용</td>
    <td  width="300">
     <textarea name="content" rows="13" cols="40" 
       style="ime-mode:active;"><%=article.getContent()%></textarea></td>
  </tr>
  <tr>
    <td width="70" class="fontw">비밀번호</td>
    <td width="300" >
     <input type="password" size="8" maxlength="12" 
               name="passwd" style="ime-mode:inactive;">
     
	 </td>
  </tr>
  <tr>      
   <td colspan=3 align="center" class="Flexbox"> 
     <input type="submit" class="fontw" value="글수정" >  
     <input type="reset" class="fontw" value="다시작성">
   </td>
 </tr>
 </table>
</form>
<%
}catch(Exception e){}%>      
  </div>    
</body>
</html>