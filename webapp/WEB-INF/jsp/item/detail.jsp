<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header"> 상품 편집  <small>User</small></h1>
                        <ol class="breadcrumb">
                            <li class="active"> 상품 정보를 수정합니다.</li>
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
                
                <div class="row">
                	<div class="col-lg-12">
                	<form:form modelAttribute="item" role="form" id="form" action="/item/edit" method="post" >
                		<div class="col-lg-6">
                			<form:input path="item_id" cssClass="form-control display-none" readonly="true"/>
	                		<div class="form-group">
	                			<label>분류</label>
	                			<form:select path="item_category_id" cssClass="form-control">
	                				<c:forEach var="c" items="${category}">
	                					<form:option value="${c.category_id}">${c.category_name}</form:option>
	                				</c:forEach>
							    </form:select>
	                		</div>
	                		<div class="form-group">
	                			<label>상점</label>
	                			<form:select path="shops_shop_id" cssClass="form-control">
	                				<c:forEach var="s" items="${shops}">
	                					<form:option value="${s.shop_id}">${s.shop_name}</form:option>
	                				</c:forEach>
							    </form:select>
	                		</div>
	                		
	                		<div class="form-group">
								<label>상품 이름</label>
								<form:input path="item_name" type="text" cssClass="form-control" placeholder="Item Name"/>
							    <form:errors path="item_name" cssClass="error-msg" />
	                		</div>
	                		<div class="form-group">
								<label>상품 고유 아이디(영문)</label>
								<form:input path="item_path_url" type="text" cssClass="form-control" placeholder="Itme Id" readonly="true"/>
							    <form:errors path="item_path_url" cssClass="error-msg" />
	                		</div>
	                		<div class="form-group">
								<label>상품 설명</label>
								<form:input path="item_discription" type="text" cssClass="form-control" placeholder="Discription"/>
							    <form:errors path="item_discription" cssClass="error-msg" />
	                		</div>
	                	</div>
	                	<div class="col-lg-6">
	                		<div class="form-group">
								<label>판매가격</label>
								<form:input path="item_sell_price" type="text" cssClass="form-control currency-won" placeholder="0"/>
							    <form:errors path="item_sell_price" cssClass="error-msg" />
	                		</div>
	                		<div class="form-group">
								<label>공급가격</label>
								<form:input path="item_supply_price" type="text" cssClass="form-control currency-won" placeholder="0"/>
							    <form:errors path="item_supply_price" cssClass="error-msg" />
	                		</div>
	                		<div class="form-group">
								<label>세일 가격</label>
								<form:input path="item_sale_price" type="text" cssClass="form-control currency-won" placeholder="0"/>
							    <form:errors path="item_sale_price" cssClass="error-msg" />
	                		</div>
	                		<div class="form-group">
								<label>하루 최대 판매량(제한없음 : 0)</label>
								<form:input path="item_daily_stock" type="text" cssClass="form-control" placeholder="0"/>
							    <form:errors path="item_daily_stock" cssClass="error-msg" />
	                		</div>
	                		<div class="form-group">
								<label>상태</label>
								<div class="form-block">
								    <label class="radio-inline margin-zero">
								    	<form:radiobutton path="item_option_state" value="1" />단순상품</label>
								    <label class="radio-inline margin-zero">
								    	<form:radiobutton path="item_option_state" value="2"/>그룹상품</label>
								    <label class="radio-inline margin-zero">
								    	<form:radiobutton path="item_option_state" value="3"/>옵션상품</label>
								    <label class="radio-inline margin-zero">
									  	<form:radiobutton path="item_option_state" value="6"/>세일상품</label>
									<label class="radio-inline margin-zero">
									  	<form:radiobutton path="item_option_state" value="9"/>판매대기</label>
									<label class="radio-inline margin-zero">
									  	<form:radiobutton path="item_option_state" value="0"/>판매종료</label>
									<form:errors path="item_option_state" cssClass="error-msg" />
								</div>
	                		</div>
					    </div>
					</div>
	                <div class="row">
	                	<div class="col-lg-12">
	                		<div class="col-lg-12">
						    <div class="form-group">
						   		<label>Contents</label>
						    	<form:textarea path="item_content" cssClass="form-control" rows="5"  />
						   	</div>
						   	</div>
						</div>
					</div>
					 <div class="row">
	                	<div class="col-lg-12">
	                		<div class="col-lg-12">
						    <div class="form-group">
						    	<label>원산지 표시</label>
								<form:textarea path="item_origin" cssClass="form-control" rows="5"  />						    	
						   	</div>
						   	</div>
						</div>
					</div>
					<div class="row">
	                	<div class="col-lg-12">
	                	<div class="col-lg-12">
						    <div class="form-group">
						    	<label>배송 사항</label>
								<form:textarea path="item_delivery" cssClass="form-control" rows="5" />						    	
						   	</div>
						   	</div>
						</div>
					</div>
					<div class="col-lg-12">
					<input type="button" class="btn btn-danger" id="savebutton" value="저장" />
					</div>
					</form:form>
                	
                </div>
                
                <div class="row">
                	<div class="col-lg-12">
                		<div class="block-200">
                		</div>
                	</div>
                </div>
			</div>
        </div>

<content tag="local_script">
	<!-- 	<script src="/js/tinymce/tinymce.min.js"></script>
			<script src="/js/tinymce.init.js"></script>
		-->
</content>
</body>
</html>