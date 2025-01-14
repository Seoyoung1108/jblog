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
						<li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin">블로그 관리</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${targetPost.title }</h4>
					<p>
						${fn:replace(targetPost.contents, newLine, "<br>") }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postVoList }" var="vo" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/${vo.categoryId}/${vo.id}">${vo.title }</a> <span>${vo.regDate }</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.profile}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryVoList }" var="vo" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/${vo.id}">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>