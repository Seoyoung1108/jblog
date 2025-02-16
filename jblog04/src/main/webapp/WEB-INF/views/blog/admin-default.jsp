<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/header.jsp" />
			<ul>
				<c:choose>
					<c:when test='${authUser==null }'>
						<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
					</c:when>
					<c:when test='${authUser.id==blog.blogId }'>
						<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/${blog.blogId}">메인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin/write">글작성</a></li>
				</ul>
				<form action="${pageContext.request.contextPath }/${blog.blogId}/admin/default/update" method="post" enctype="multipart/form-data">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value="${blog.title }"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img src="${pageContext.request.contextPath}${blog.profile }"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="hidden" name="profile" value="${blog.profile }"></td>
			      			<td><input type="file" name="file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>