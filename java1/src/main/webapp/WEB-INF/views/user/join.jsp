<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!-- 절대경로로 수정하기 위한 코드 -->
<c:set var="root" value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	
<script>
	function checkUserIdExist() {
		let user_id = $("#user_id").val()//사용자가 입력한 id 값 가져오기
		console.log('${root}');
		if(user_id.length == 0){ //아이디가 db에 있는지 없는지 확인
			alert("아이디를 입력하세요")
			return 
		}
		
		$.ajax({
		
			url: '${root}/user/checkUserIdExist/'+ user_id,//요청할 페이지의 주소
			type: 'get',//요청타입
			dataType : 'text',
			success: function(result){ //RestApiController에서 값을 가져옴
				//그게 트루를 가져온거면 사용가능 / 펄스를 가져오면 사용 불가능
				if(result.trim()=="true"){ //문자열로 받아서 확인		
					alert("사용할 수 있는 아이디입니다.")
					$("#userIdExist").val("true")
				}else {
					alert("사용할 수 없는 아이디입니다.")
					$("#userIdExist").val("false")
				}
			}
		})

	}
	//사용자 ID란에 키보드 입력 시 무조건 False
	function resetUserIdExist() {
		$("#userIdExist").val("false")
		
	}
</script>
</head>
<body>

	<!-- 상단 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="container" style="margin-top: 100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">
					
					<!-- form -> form:form -->
					
						<form:form action="${root}user/join_pro" modelAttribute="joinUserBean" method="post">
							<div class="form-group">
							
								<!-- label -> form:label -->
								<form:label path="user_name">이름</form:label>
								<!-- input -> form:input -->
								<form:input path="user_name" class="form-control" />
								<!-- form:errors -->
								<form:errors path="user_name" />
								
							</div>
							
							<!-- form:hidden -->
							<form:hidden path="userIdExist"/> <!-- 펄스값드감 -->
							
							<div class="form-group">
								<!-- label -> form:label -->
								<form:label path="user_id">아이디</form:label>
								<div class="input-group" >
								
								<!-- input -> form:input -->
								<!-- 버튼이벤트 -->
							
								<form:input path="user_id" class="form-control" onkeypress="resetUserIdExist()"/>
								
					
								
								
								
								
								<div class="input-group-append">
									<button type="button" class="btn btn-primary" onclick="checkUserIdExist()">중복확인</button>
								</div>
								
								</div>
								<form:errors path="user_id" />
							</div>
							<div class="form-group">
							
								<!-- label -> form:label -->
								<form:label path="user_pw">비밀번호</form:label>
								
								<!-- input -> form:password -->
								<form:password path="user_pw" class="form-control" />
								
								<form:errors path="user_pw" />
								
							</div>
							<div class="form-group">
							
								<!-- label -> form:label -->
								<form:label path="user_pw2">비밀번호 확인</form:label>
								
								<!-- input -> form:password -->
								<form:password path="user_pw2" class="form-control" />
								
								<form:errors path="user_pw2" />
								
							</div>
							<div class="form-group">
								<div class="text-right">
								
									<!-- button -> form:button -->
									<form:button type="submit" class="btn btn-primary">회원가입</form:button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>
	<!-- 하단  -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>








