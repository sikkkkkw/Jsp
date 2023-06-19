<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "nboard.BoardDBBean" %>
<%@ page import = "nboard.BoardDataBean" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="color.jspf"%>

<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class='container'>

<header >
			<div class='banner_wrap'>
			<!-- 리스트로 이동 -->
				<span class='title'>글내용 보기</span>
			
			<a href="list.jsp">
					<span  class='back_button1'>글목록</span>
			</a>
					
			</div>
			</header>
<%
   int num = Integer.parseInt(request.getParameter("num"));
   String pageNum = request.getParameter("pageNum");

   SimpleDateFormat sdf = 
        new SimpleDateFormat("yyyy-MM-dd HH:mm");

   try{
      BoardDBBean dbPro = BoardDBBean.getInstance(); 
      BoardDataBean article =  dbPro.getArticle(num);
  
	  int ref=article.getRef();
	  int re_step=article.getRe_step();
	  int re_level=article.getRe_level();
%>

<form>
<table style="border:1px solid black;margin-left:auto;margin-right:auto;">  
  <tr height="30"class='list'>
    <td align="center"width="90">글번호</td>
    <td align="center" width="125">작성자</td>
    <td align="center"width="125">글제목</td>
    <td align="center"width="125">조회수</td>
    <td align="center" width="125">작성일</td>
    <td align="center" width="125">글내용</td>
  </tr>
  <tr height="30">
    <td align="center"align="center" > 
	     <%=article.getNum()%></td>
	<td align="center"  align="center">
	     <%=article.getWriter()%></td>
	<td align="center"align="center">
	     <%=article.getSubject()%></td>     
	<td align="center" width="125" align="center">
	     <%=article.getReadcount()%></td>
    <td align="center" align="center">
	     <%= sdf.format(article.getReg_date())%></td>
	<td  colspan="3">
           <pre><%=article.getContent()%></pre></td>
  </tr>
  <tr height="30">
  </tr>
  <tr>      
    <td colspan=5 align="left" class="Flexbox">  
	  <input type="button" class="fontw" value="글수정" 
       onclick="document.location.href='updateForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	  <input type="button" class="fontw"  value="글삭제"
       onclick="document.location.href='deleteForm.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" class="fontw"  value="답글쓰기" 
       onclick="document.location.href='writeForm.jsp?num=<%=num%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
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