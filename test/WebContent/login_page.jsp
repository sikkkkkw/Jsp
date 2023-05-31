<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="container">
	<div class='login_wrap'>
    <!--  Login 자체를 감싸는 Wrap -->
    <div class='login_account'>
        <h3>로그인</h3>
        <div class='login_guide'>
            <p> 여러분의 계정을 등록해보세요. </p>
            <p> 여러 명함 관리를 할 수 있습니다!</p>
        </div>


        <div class="login_input">
            <!-- 로그인 입력 시 이동 -->
            <form action="User.do" method="POST">
                <ul>
                    <li>
                        <input type="text" id='id' name="id" placeholder="아이디를 입력해주세요.">
                    </li>
                    <li>
                        <input type="password" id='password' name="password" placeholder="비밀번호를 입력해주세요.">
                    </li>
                    <li>
                        <div class='btn_login'>
                      	<!-- Login 함수 실행 -->
                      		<input type="hidden" name="a" value="login">
                            <button type='submit'>로그인</button>
                        </div>
                    </li>
                </ul>
            </form>
        </div>

        <div class='signup_guide'>
            <div class='signup_txt'>
                <p class="sign_txt">아직 <strong class='color_point'>회원</strong>이 아니신가요?</p>
                <br>
                <p>지금 회원에 가입하시면 <br>최상의 명함 관리를 할 수 있습니다.</p>
            </div>
            <div class='btn_signup'>
                <a href="signup.jsp">
                    <!-- 회원가입 페이지 버튼 -->
                    <button>회원가입</button>
                </a>
            </div>
        </div>


		    </div>
		</div>
	
	</div>
	
</body>
</html>