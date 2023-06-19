<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "nboard.BoardDBBean" %>
<%@ page import = "nboard.BoardDataBean" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="color.jspf"%>

<%!
    int pageSize = 10;
    SimpleDateFormat sdf = 
        new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%
    String pageNum = request.getParameter("pageNum");

    if (pageNum == null) {
        pageNum = "1";
    }

    int currentPage = Integer.parseInt(pageNum);
    int startRow = (currentPage - 1) * pageSize + 1;
    int endRow = currentPage * pageSize;
    int count = 0;
    int number = 0;
    List<BoardDataBean> articleList = null; 
    
    BoardDBBean dbPro = BoardDBBean.getInstance();
    count = dbPro.getArticleCount();
    
    if (count > 0) {
        articleList = dbPro.getArticles(startRow, pageSize);
    }

   number = count-(currentPage-1)*pageSize;
%>
<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0">
<title>게시판</title>
</head>
	<body>
<div class='container'>	
		<header >
			<div class='banner_wrap'>
			<!-- 리스트로 이동 -->
			<a href='Card.do'>
					<span class='back_button'><
					</span>
			</a>
				<span class='title'>글목록(전체 글:<%=count%>)</span>
			
			<a href="writeForm.jsp">
					<span  class='back_button1'>글쓰기</span>
			</a>
					
			</div>
		</header>
		<main>
		<% if (count == 0) { %>

<table style="border:1px solid black;margin-left:auto;margin-right:auto;">
<tr>
    <td align="center" class="miss">
              게시판에 저장된 글이 없습니다.
    </td>
</table>

<% } else {%>
<table style="border:1px solid black;margin-left:auto;margin-right:auto;"> 
    <tr height="30" class='list'> 
      <td align="center">번 호</td> 
      <td align="center">제   목</td> 
      <td align="center">작성자</td>
      <td align="center">작성일</td> 
      <td align="center" >조 회</td> 
      <td align="center">IP</td>    
    </tr>
<%  
   for (int i = 0 ; i < articleList.size() ; i++) {
       BoardDataBean article = articleList.get(i);
%>
   <tr height="30" >
    <td  width="50" align="center"> <%=number--%></td>
    <td  width="250" align="center">
<%
   int wid=0; 
   if(article.getRe_level()>0){
      wid=5*(article.getRe_level());
%>
     <img src="img/level.png" width="20" height="20">
<%  }else{%>
     <img src="img/level.png" width="<%=wid%>" height="20">
<%  }%>
           
      <a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
           <%=article.getSubject()%></a> 
<% if(article.getReadcount()>=15){%>
         <img src="img/fire.png" border="0"  height="16" ><%}%> </td>
    <td width="100" align="center"> 
       <a><%=article.getWriter()%></a></td>
    <td width="150" align="center"><%= sdf.format(article.getReg_date())%></td>
    <td width="50" align="center"><%=article.getReadcount()%></td>
    <td width="100" align="center"><%=article.getIp()%></td>
  </tr>
<%}%>
</table>
<%}%>
<table style="border:1px solid white;margin-left:auto;margin-right:auto;">
<tr height="30" bgcolor="<%=back_c%>">
<%
    if (count > 0) {
        int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
      int startPage =1;
      
      if(currentPage % 10 != 0)
           startPage = (int)(currentPage/10)*10 + 1;
      else
           startPage = ((int)(currentPage/10)-1)*10 + 1;

      int pageBlock = 10;
        int endPage = startPage + pageBlock - 1;
        if (endPage > pageCount) endPage = pageCount;
        
        if (startPage > 10) { %>
          <td align="center"><a href="list.jsp?pageNum=<%= startPage - 10 %>">[이전]</a>
          </td>
<%      }
        
        for (int i = startPage ; i <= endPage ; i++) {  %>
           <td align="center"><a href="list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
           </td>
<%      }
        
        if (endPage < pageCount) {  %>
        <td align="center"><a href="list.jsp?pageNum=<%= startPage + 10 %>">[다음]</a>
        </td>
<%
        }
    }
%>
</tr>
</table>
		</main>
	</div>
	</body>
</html>