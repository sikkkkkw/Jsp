<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/signup.css">
</head>
<body>
               <div class="left-banner">
    <img src="이미지_파일_경로" alt="이미지_설명">
    <h2>광고 제목</h2>
    <p>광고 내용</p>
    <a href="광고_링크">자세히 보기</a>
</div>
<div class='container'>
	<div class='login_wrap'>
    <!--  Login 자체를 감싸는 Wrap -->
    <div class='login_account'>
        <h3>회원가입</h3>


        <div class='login_guide'>
            <p> 여러분의 계정을 등록해보세요. </p>
            <p> 여러 명함 관리를 할 수 있습니다!</p>
        </div>
        <div class="login_input">
            <!-- 회원가입 -->
            <form action="User.do" method="POST">
                <ul>
                    <li>
                        <input type="text" name="name" placeholder="이름">
                    </li>
                    <li>
                        <input type="text" name="id" required pattern=".{5,11}" placeholder="아이디(5~11자)">
                    </li>
                    <li>
                        <input type="password" name="password1" required pattern=".{8,}"placeholder="비밀번호(최소 8자)">
                    </li>
                    <li>
                        <input type="password" name="password2" required pattern=".{8,}" placeholder="비밀번호 확인(최소 8자)">
                    </li>

                    <li>
                        <div class='btn_login'>
                        	<input type="hidden" name="a" value="signup">
                            <button type='submit'>회원가입</button>
                        </div>
                    </li>
                </ul>
            </form>
           	 		<div class='btn_login'>
                       	<a href="login_page.jsp"><button style="background:grey;"type='submit'>로그인 하러가기</button></a>
                    </div>
        </div>

        <div class='signup_guide'>
            <div class='signup_txt'>
                <p class="sign_txt"> 회원 가입을 하게 되면 다양한 혜택이 쏟아진다!</p>
                <br>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>