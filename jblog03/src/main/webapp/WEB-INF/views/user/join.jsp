<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	var el = $("#btn-check");
	el.click(function(){
		var id = $("#id").val();
		if(id==""){
			return;
		}	
	
		$.ajax({
			url:"${pageContext.request.contextPath }/api/checkid?id="+id,
			type: "get",
			dataType: "json",
			success: function(response){
				console.log(response);
				if(response.result!="success"){
					console.error(response.message);
					return;
				}
				
				if(response.data.exist){
					alert("이미 존재하는 아이디입니다. 다른 아이디를 사용해 주세요.");
					$("#id").val("");
					$("#id").focus();
					return;
				}
				
				$("#btn-check").hide();
				$("#img-check").show();
				// 쓰는 란 막기
			},
			error: function(xhr,status,err){
				console.error(err);
			}
		});
	
	});
});



</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
			<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
		</ul>
		<form class="join-form" method="post" action="${pageContext.request.contextPath }/join">
			<label class="block-label" for="name">이름</label>
			<input type="text" name="name">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="id" type="text" name="id">
			<input id="btn-check" type="button" value="id 중복체크">
			<img id="img-check" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input type="password" name="password">

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">
		</form>
	</div>
</body>
</html>
