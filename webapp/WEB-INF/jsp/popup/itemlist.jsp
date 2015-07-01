<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>아이템 선택</title>
	<link href="/css/plugins/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div id="page-wrapper">

		<div class="container-fluid">
		

			<!-- Page Heading -->
			<div class="row">
			
				<div class="col-lg-12">
					<h1 class="page-header">상품 목록</h1>
					<ol class="breadcrumb">
						Item
					</ol>
				</div>
			</div>
			
			<!-- Item List table -->
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
													<th>설명</th>
													<th>상태</th>
													<th>상점</th>
													<th>지역</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="row" items="${itemList}">
													<tr class="item-row">
														<td class="item_id">${row.item_id}</td>
														<td>
															<img class="media_path" src="${row.media_photo_url}" height="30" width="30">
															<input class="media_id hidden" value="${row.media_id }" readonly="readonly">
														</td>
														<td>${row.category_name}</td>
														<td class="item_name">${row.item_name}</td>
														<td class="item_des">${row.item_description}</td>
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


<div id="script-wrapper">
	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('.item-row').click(function(e){
			var target = e.target.parentElement;
			var data = {};
			data.itemId = target.getElementsByClassName('item_id')[0].innerHTML;
			data.mediaPath = target.getElementsByClassName('media_path')[0].src;
			data.itemName = target.getElementsByClassName('item_name')[0].innerHTML;
			data.itemDes = target.getElementsByClassName('item_des')[0].innerHTML;	
			data.mediaId = target.getElementsByClassName('media_id')[0].value;
			window.opener.ADMIN.popupCallback(data);
			window.close();
		})
	
	</script>
</div>

</body>
</html>