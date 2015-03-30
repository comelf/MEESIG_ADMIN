<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<div id="page-wrapper">

		<div class="container-fluid">
		
			<c:set var="celNum" value="10,20,30,50,100"/>
			
			<!-- Page Heading -->
			<div class="row">
			
				<div class="col-lg-12">
					<h1 class="page-header">회원목록</h1>
					<ol class="breadcrumb">
						User
					</ol>
				</div>
			</div>
			
		    <c:if test="${not empty msg}">
                <div class="row">
                 	<div class="col-lg-12">
						<div class="alert alert alert-danger alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					    	<strong>${msg}</strong>
						</div>
					</div>
                </div>
            </c:if>
			
			<!-- User List table -->
			<div class="row">
				<div class="col-lg-6">
					
					<div class="fl-left mg-bottom">
						<form class="form-inline" role="form">
							<label>출력개수</label>
							<select class="form-control" name="count">
									<c:forEach var="num" items="${celNum}">
										<c:choose>
								  			<c:when test="${num eq paging.pageSize}"><option value="${num}"  selected="selected">${num}</option></c:when>
								  			<c:otherwise><option value="${num}">${num}</option></c:otherwise>
								  		</c:choose>
								  		
								  	</c:forEach>
							</select>
							<button class="btn btn-default" type="submit">적용</button>
						</form>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="fl-right mg-bottom">
						<form class="form-inline" role="form" action="/user/list/search" method="get">
							<div class="form-group">
								<select class="form-control" name="type">
									<option value="name">이름</option>
									<option value="id">ID</option>
									<option value="email">이메일</option>
								</select>
								<input type="text" class="form-control" name="query" placeholder="Search">
								<button class="btn btn-default" type="submit">사용자 검색</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-heading">사용자 목록 (${paging.totalCount})</h4>
						</div>
						<div class="panel-body">
							
							<div class="row">
								<div class="col-lg-12">
									<div class="table-responsive">
										<table class="table table-striped table-hover">
											<thead>
												<tr>
													<th>+</th>
													<th>사용자 ID</th>
													<th>이름</th>
													<th>이메일</th>
													<th>성별</th>
													<th>등급</th>
													<th>가입일</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="row" items="${userList}">
													<tr>
														<td class="non-click"><input type="checkbox" id="inlineCheckbox1" value="${row.user_id}"></td>
														<td>${row.user_login_id}</td>
														<td>${row.user_name}</td>
														<td>${row.user_email}</td>
														<td>${row.user_gender}</td>
														<td>${row.user_grade}</td>
														<td> <fmt:formatDate value="${row.user_join_date}" pattern="yyyy-MM-dd HH:mm" /></td>
													</tr>
												</c:forEach>
											</tbody>
											</div>
										</table>
									</div>
								</div>
							</div>
							
							<div class="row center">
								<ul class="pagination">
									<c:if test="${paging.firstPageNo < paging.startPageNo}">
										<li><a href="/user/list?page=${paging.startPageNo-1}&count=${paging.pageSize}">«</a></li>
									</c:if>
								
								  	<c:forEach var="pageNum" begin="${paging.startPageNo}" step="1" end="${paging.endPageNo}">
								  		<c:choose>
								  			<c:when test="${paging.pageNo eq pageNum}"><li class="active"></c:when>
								  			<c:otherwise><li></c:otherwise>
								  		</c:choose>
								  		<a href="/user/list?page=${pageNum}&count=${paging.pageSize}">${pageNum}</a></li>
								  	</c:forEach>
								  	<c:if test="${paging.endPageNo < paging.finalPageNo}">
										<li><a href="/user/list?page=${paging.endPageNo+1}&count=${paging.pageSize}">»</a></li>
									</c:if>
									
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
            	<div class="col-lg-12">
                	<div class="block-200"></div>
                </div>
            </div>
		</div>

	</div>



<content tag="local_script">
<!-- 	<script src="/js/table.js"></script>	-->
</content>


</body>
</html>