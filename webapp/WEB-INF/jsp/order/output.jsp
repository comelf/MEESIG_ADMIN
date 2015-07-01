<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
													<th>id</th>
													<th>상점명</th>
													<th>날짜</th>
													<th>출력</th>
													<th>발주</th>
												</tr>
											</thead>
											<tbody>
												
													<c:forEach var="shop" items="${shopOrders}" >
														
														<tr>
														
															<td>${shop.shop_id}</td>
															<td class="shop-name">${shop.shop_name}</td>
															<td>
																<c:if test="${not empty shop.sumOfDays}">
																	<select name="date" class="sel-date">
																		<c:forEach items="${shop.sumOfDays}" var="day">
																			<option value="${day.reserve_date}"><fmt:formatDate value="${day.reserve_date}" pattern="MM월 dd일 E" /> - ${day.sum}개</option>
																		</c:forEach>
																	</select>
																</c:if>
															</td>
															<td>
																<c:if test="${not empty shop.sumOfDays}">
																<form action="/order/excel">
																<input name="shop" class="shop-id hidden" readonly="readonly" value="${shop.shop_id}">
																<input name="date" class="input-date hidden" readonly="readonly" value="">
																<input type="button" class="btn btn-primary outbutton" value="출력">
																</form>	
																</c:if>
															</td>
															<td>
																<c:if test="${not empty shop.sumOfDays}">
																<form action="/order/update/process">
																<input name="shop" class="shop-id hidden" readonly="readonly" value="${shop.shop_id}">
																<input name="date" class="input-date hidden" readonly="readonly" value="">
																<input type="button" class="btn btn-danger processbutton" value="발주">
																</form>
																</c:if>
															</td>
														
														</tr>
														
													</c:forEach>
												
											</tbody>
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
	<!-- 	<script src="/js/order.output.js"></script>	-->
</content>


</body>
</html>