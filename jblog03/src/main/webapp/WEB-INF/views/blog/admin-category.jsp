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
					<li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${blog.blogId}/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>    		
		      		<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${list.subList(0,list.size()-1) }" var="vo" varStatus="status">
						<tr>
							<td>${count-status.index }</td>
							<td>${vo.name }</td>
							<td>${vo.postNum }</td>
							<td>${vo.description }</td>
							<td><a href="${pageContext.request.contextPath }/${blog.blogId}/admin/category/delete/${vo.id }"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
						</tr>		
					</c:forEach>
					<tr>
						<td>1</td>
						<td>${list.get(list.size()-1).name }</td>
						<td>${list.get(list.size()-1).postNum }</td>
						<td>${list.get(list.size()-1).description }</td>
						<td></td>
					</tr>	  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="${pageContext.request.contextPath }/${blog.blogId }/admin/category/add" method="post">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table>
		      	</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>