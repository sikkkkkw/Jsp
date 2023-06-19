<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="color.jspf"%>
<html>
<html>
<head>
<title>게시판</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0">
</head>

<body>
<% 
  int num = 0, ref = 1, re_step = 0, re_level = 0;
  String strV = "";
  try{
    if(request.getParameter("num")!=null){
	   num=Integer.parseInt(request.getParameter("num"));
	   ref=Integer.parseInt(request.getParameter("ref"));
	   re_step=Integer.parseInt(request.getParameter("re_step"));
	   re_level=Integer.parseInt(request.getParameter("re_level"));
    }
%>
<div class='container'>

<header >
			<div class='banner_wrap'>
				<span class='title'>글쓰기</span>		
			</div>
			<a href="list.jsp">
					<span  class='back_button2'>목록보기</span>
			</a>
			<!-- 글목록 이동 -->

		</header>	
<form method="post" name="writeform" 
      action="writePro.jsp" onsubmit="return writeSave()">
<input type="hidden" name="num" value="<%=num%>">
<input type="hidden" name="ref" value="<%=ref%>">
<input type="hidden" name="re_step" value="<%=re_step%>">
<input type="hidden" name="re_level" value="<%=re_level%>">

<table style="border:1px solid black;margin-left:auto;margin-right:auto;">
   <tr>
    <td  width="70"  class="fontw">이 름</td>
    <td  width="300">
       <input type="text" size="41" maxlength="10" 
          name="writer" style="ime-mode:active;"pattern=".{0,9}" placeholder="(9자 내외)"></td><!--active:한글-->
  </tr>
  <tr>
    <td  width="70"   class="fontw">제 목</td>
    <td  width="300">
    <%
      if(request.getParameter("num")==null) 
    	 strV = "";
      else
    	 strV = "[답변]";
    %>
    <input type="text" size="41" maxlength="50" name="subject"
         value="<%=strV%>" style="ime-mode:active;" ></td>	
  </tr>
  <tr>
    <td  width="70" class="fontw">Email</td>
    <td  width="300">
       <input type="email" size="41" maxlength="30" name="email"
           style="ime-mode:inactive;" ></td><!--inactive:영문-->
  </tr>
  <tr>
    <td  width="70"   class="fontw">내 용</td>
    <td  width="300">
     <textarea rows="13" cols="40" name="content"
              style="ime-mode:active;"></textarea> </td>
  </tr>
  <tr>
    <td  width="70"   class="fontw">비밀번호</td>
    <td  width="300">
     <input type="password" size="8" maxlength="12" 
             name="passwd" style="ime-mode:inactive;"> 
	 </td>
  </tr>
  <tr>      
    <td colspan=3 align="center" class="Flexbox"> 
      <input type="submit" class="fontw" value="글쓰기" >  
      <input type="reset" class="fontw" value="다시작성">
    </td>
  </tr>
</table>    
 <%
  }catch(Exception e){}
%>     
</form> 
</div>   
</body>
</html>