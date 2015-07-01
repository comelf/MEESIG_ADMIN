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
			
			
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-heading">주문 목록</h4>
						</div>
						<div class="panel-body">
							
							<div class="row">
								<div class="col-lg-12">
									<div class="table-responsive">
										<table class="table table-striped table-hover">
											<thead>
												<tr>
													<th>상점명</th>
													<th>메뉴이름</th>
													<th>결제완료</th>
													<th>상점발주</th>
													<th>배송중</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="row" items="${menuOrders}">
													<tr>
														<%-- <td>${row.order_id}<input type="text" class="hidden" value="${row.order_id}" readonly="readonly"></td> --%>
														<td>${row.shop_name}</td>
														<td>${row.item_name}</td>
														<td>${row.sum_state_process}</td>
														<td>${row.sum_state_shop}</td>
														<td>${row.sum_state_delivery}</td>
													</tr>
												</c:forEach>
											</tbody>
											</div>
										</table>
									</div>
								</div>
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
	<!-- 		-->
</content>


</body>
</html>