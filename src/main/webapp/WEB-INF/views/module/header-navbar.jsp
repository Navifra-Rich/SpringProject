<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="header">
	<div class="goHomeLogo">
		GO<br />HOME
	</div>
	<div class="headLogo">head Logo~</div>
	<div class="search_navbar"></div>
	<div class="section_navbar">
		<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
			<ul class="navbar-nav">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown">지역으로 찾기 </a>
					<div class="dropdown-menu">
						<c:forEach var="loca" items="${locations}">
							<a class="dropdown-item" onClick="byLocation('${loca.name}')">${loca.name}</a>
						</c:forEach>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown">활동으로 찾기 </a>
					<div class="dropdown-menu">
						<c:forEach var="cate" items="${categories}">
							<a class="dropdown-item" onClick="byCategory'${cate.name}')">${cate.name}</a>
						</c:forEach>
					</div></li>
				<li class="nav-item active"><a class="nav-link"
					href="/ex/Board/getBoardList">전체게시판 <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/ex/Board/getBoardList">삐롱삐롱삐로로롱 <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			</ul>
			<form class="form-inline my-2 my-md-0">
				<input class="form-control" type="text" placeholder="Search">
			</form>
		</nav>
	</div>
</div>