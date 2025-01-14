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
					<c:when test='${authUser.id==blogVo.blogId }'>
						<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}">메인</a></li>
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
					<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/admin/default">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/admin/category">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>
				<form action="${pageContext.request.contextPath }/${blogVo.blogId}/admin/write/add" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select name="categoryId">
				      				<c:forEach items="${categoryVoList }" var="vo" varStatus="status">
				      					<option value="${vo.id }">${vo.name }</option>
									</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="contents"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>