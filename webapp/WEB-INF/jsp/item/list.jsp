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
					<h1 class="page-header">상품 목록</h1>
					<ol class="breadcrumb">
						Item
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
			
			<!-- Item List table -->
			<div class="row">
				<div class="col-lg-12">
					<div class="fl-right mg-bottom">
						<form class="form-inline" role="form" action="/user/list/search" method="get">
							<div class="form-group">
								<select class="form-control" name="type">
									<option value="name">상품명</option>
									<option value="shop">상점</option>
									<option value="location">지역</option>
									<option value="category">분류</option>
								</select>
								<input type="text" class="form-control" name="query" placeholder="Search">
								<button class="btn btn-default" type="submit">상품 검색</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-heading">상품 목록 (${paging.totalCount})</h4>
						</div>
						<div class="panel-body">
							
							<div class="row">
								<div class="col-lg-12">
									<div class="table-responsive">
										<table class="table table-striped table-hover">
											<thead>
												<tr>
													<th>+</th>
													<th>Image</th>
													<th>분류</th>
													<th>상품명</th>
													<th>판매가격</th>
													<th>상태</th>
													<th>상점</th>
													<th>지역</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="row" items="${itemList}">
													<tr>
														<td><input type="checkbox" id="inlineCheckbox1" value="${row.item_id}"></td>
														<td>${row.item_img_filename}.${row.item_img_extension}</td>
														<td>${row.category_name}</td>
														<td>${row.item_name}</td>
														<td>${row.item_sell_price}</td>
														<td>${row.item_state}</td>
														<td>${row.shop_name}</td>
														<td>${row.location_name}</td>
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