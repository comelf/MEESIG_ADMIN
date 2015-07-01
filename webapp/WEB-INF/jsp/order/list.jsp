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
					<h1 class="page-header">주문 목록</h1>
					<ol class="breadcrumb">Order</ol>
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
			
			<!-- Item List table -->
			<div class="row">
				<div class="col-lg-12">
					<div class="fl-right mg-bottom">
						<form class="form-inline" role="form" action="/order/list/search" method="get">
							<div class="form-group">
								<select class="form-control" name="type">
									<option value="name">상품명</option>
									<option value="oid">주문번호</option>
									<option value="userId">고객아이디</option>
									<option value="userName">고객이름</option>
									<option value="userPhone">고객전화번호</option>
								</select>
								<input type="text" class="form-control" name="query" placeholder="Search">
								<button class="btn btn-default" type="submit">주문 검색</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-heading">주문 목록 (${paging.totalCount})</h4>
						</div>
						<div class="panel-body">
							
							<div class="row">
								<div class="col-lg-12">
									<div class="table-responsive">
										<table class="table table-striped table-hover">
											<thead>
												<tr>
													<th>주문번호</th>
													<th>상태</th>
													<th>주문명</th>
													<th>주문가격</th>
													<th>주문시간</th>
													<th>주문자아이디</th>
													<th>주문자이름</th>
													<th>주문자번호</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="row" items="${orderList}">
													<tr>
														<td>${row.order_id}<input type="text" class="hidden" value="${row.order_id}" readonly="readonly"></td>
														<td>${row.convertOrderState}</td>
														<td>${row.order_name}</td>
														<td>${row.order_payment_price}</td>
														<td><fmt:formatDate value="${row.order_date}" pattern="MM월 dd일 (E) HH:mm:ss" /></td>
														<td>${row.user_login_id}</td>
														<td>${row.user_name}</td>
														<td>${row.user_phone}</td>
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