<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="meesig" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>

<body>
		<!-- Page -->
        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
				<div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">주문상세<small>Order</small></h1>
                        <ol class="breadcrumb">
                            <li class="active">주문의 상세내역</li>
                        </ol>
                    </div>
                </div>
                
                <!-- Message -->
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
                
                <div class="row">
                	<div class="col-lg-12">
                		<div class="col-lg-6">
	                		<div class="form-group">
	                			<label>주문번호</label>
	                			<input class="form-control" value="${order.order_id}" disabled="disabled">
	                		</div>
	                		<div class="form-group">
	                			<label>주문명</label>
	                			<input class="form-control" value="${order.order_name}" disabled="disabled">
	                		</div>
	                		<div class="form-group">
								<label>주문시간</label>
								<input class="form-control" value="<fmt:formatDate value="${order.order_date}" pattern="YYYY년 MM월 dd일 (E) HH:mm:ss" />" disabled="disabled">
	                		</div>
	                		<div class="form-group">
								<label>주문자 연락처</label>
								<input class="form-control" value="${order.order_call_num}" disabled="disabled">
	                		</div>
	                		<div class="form-group">
								<label>주문상태</label>
								<input class="form-control" value="${order.order_state}" disabled="disabled">
	                		</div>
	                	</div>
	                	<div class="col-lg-6">
	                		<div class="form-group">
								<label>주문 금액</label>
								<input class="form-control" value="${order.convertedPaymentPrice}" disabled="disabled">
	                		</div>
	                		<div class="form-group">
								<label>상품 총액</label>
								<input class="form-control" value="${order.convertedMultiplyPrice}" disabled="disabled">
	                		</div>
	                		<div class="form-group">
								<label>배송비</label>
								<input class="form-control" value="${order.convertedShippingPrice}" disabled="disabled">
	                		</div>
	                		<div class="form-group">
								<label>쿠폰사용</label>
								<input class="form-control" value="${order.order_use_coupon}" disabled="disabled">
	                		</div>
	                		<div class="form-group">
								<label>포인트사용</label>
								<input class="form-control" value="${order.order_use_point}" disabled="disabled">
	                		</div>
	                		
					    </div>
					</div>
				</div>
                <div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
						  <div class="panel-heading">주문상품</div>
						  <div class="panel-body">
							<div class="table-responsive">
								
								<c:forEach var="bundle" items="${order.bundles}">
								
								
									<table class="table table-striped table-hover">
										<thead>
											<tr>
												<th>메뉴</th>
												<th>Image</th>
												<th>상품명</th>
												<th>수량</th>
												<th>배송일</th>
												<th>옵션</th>
												<th>배송비</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="menu" items="${bundle.orderMenus}">
											<tr>
												<td>${menu.items_item_id}</td>
												<td><img src="${menu.menuImg}" height="30" width="30"></td>
												<td>${menu.item_name}</td>
												<td>${menu.om_item_count}</td>
												<td>${menu.om_reserve_delivery_day}</td>
												<td>${menu.om_option}</td>
												<td>${menu.om_charge_shipping_price}</td>
											</tr>
											</c:forEach>	
										</tbody>
									
									</table>
								</c:forEach>
								
							</div>
						</div>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
						  <div class="panel-heading">배송지 정보</div>
						  <div class="panel-body">
						   <label>주문번호</label>
								${order.delivery.delivery_name}
						  </div>
						</div>
					</div>
				</div>
            </div>
        </div>
		
      
<content tag="local_script">
	<!-- 	
			<script src="/js/meesig.admin.js"></script>
			
		-->
</content>
        
</body>
</html>