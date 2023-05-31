<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/new_list.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, 
  maximum-scale=1.0, minimum-scale=1.0">
<title>명함 리스트 페이지 </title>
</head>

<body>
<div class='container'>
	
	<!-- 메인 nav -->
	<header>
		<div class='header_wrap'>
				<div class='logobox'>
				<!-- 로고 클릭하면 홈으로 와야함  -->
					<a href='Card.do'>
						<img alt='고순조' src="img/고순조logo2.png" width="100px" height="100px" />
					</a>
				</div>
				<div class='userbox'>
					<ul>
						<li class='list_name'>명함 목록</li> 
					</ul>
					<ul>
						<!-- 로그아웃 이동 -->
						<a href="User.do?a=logout">
							<li class='menubar'>로그아웃</li>
						</a>
						<!-- 명함 등록 이 -->
						<a href="new_add.jsp?num=${user_unum }">
							<li class='menubar'>명함등록</li>
						</a>
						<!-- user 부분에 로그인한 사람 아이디 들어가기 -->
						<li class='menubar'><strong class='point_name'>${username }</strong>님이 접속하였습니다.</li>				
					</ul>
				</div>
		</div>
	</header>
	<hr>
	<main>
	<div class='main_wrap'>
			<div class='searchbox'>
				<form action="Card.do">
					<div class='searchinput'>
						<input type='text' name='search' placeholder="이름 혹은 회사명을 검색하시오."/>
						<input type='hidden' name='a' value='Search'/>
					<!-- 돋보기버튼 -->
						<button class='zoom'>
							<img alt="돋보기" src="img/zoom.png" width="15px" height="15px"/>
						</button>
					</div>
				</form>
			</div>
			<hr>	
				
			<!-- flex 모바일은 1줄, 컴퓨터는 2줄 -->
			<div class='card_list_wrap'>
					
					<!-- 반복 될 명함 아이템 + 상세 페이지 이동 -->
				<c:forEach var="bean" items="${v}">
					<div class='card_wrap'>
						<div class='card_img'>
							<!-- 디테일 페이지 이동 -->
							<a href='Card.do?cnum=${bean.cnum}&a=Detail'>
								<img alt="프로필 사진" src="img/profile.png" width="50px" height="50px">
								<span class='company_name'>${bean.cb.comname }</span>
							</a>
						</div>
						
						<div class='card_info'>
						<!-- 디테일 페이지 이동 -->
						<a href='Card.do?cnum=${bean.cnum}&a=Detail'>
							<span class='card_name'>${bean.cname }</span>
							<span class='card_number'>${bean.phone }</span>
							<span class='card_address'>${bean.cb.address }</span>
							<span class='card_department'>${bean.dep }</span>
							<span class='card_position'>${bean.pos }</span>
						</a>
							<div class='card_menu'>
								<a href='Card.do?cnum=${bean.cnum}&a=Update'><span class='card_function'>수정</span></a>
								<a href='Card.do?cnum=${bean.cnum}&a=Delete'><span class='card_function'>삭제</span></a>
							</div>
						</div>
					</div>
				</c:forEach>
					
			</div>
			
	</div>
	</main>
</div>
</body>
</html>