<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                        <h1 class="page-header"> 상품추가 <small>Item</small></h1>
                        <ol class="breadcrumb">
                            <li class="active">새로운 상품을 추가합니다.</li>
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
                
                <!-- Page Body -->
                <form:form modelAttribute="addItem" role="form" id="form" action="/item/create" method="post" >
                <div class="row">
                	<div class="col-lg-12">
                		<div class="col-lg-6">
	                		<div class="form-group">
	                			<label>분류</label>
	                			<form:select path="item_category_id" cssClass="form-control">
	                				<c:forEach var="c" items="${addItem.categoryList}">
	                					<form:option value="${c.category_id}">${c.category_name}</form:option>
	                				</c:forEach>
							    </form:select>
	                		</div>
	                		<div class="form-group">
	                			<label>상점</label>
	                			<form:select path="shops_shop_id" cssClass="form-control">
	                				<c:forEach var="s" items="${addItem.shopList}">
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
								<form:input path="item_path_url" type="text" cssClass="form-control" placeholder="Itme Id"/>
							    <form:errors path="item_path_url" cssClass="error-msg" />
	                		</div>
	                		<div class="form-group">
								<label>상품 설명</label>
								<form:input path="item_description" type="text" cssClass="form-control" placeholder="Description"/>
							    <form:errors path="item_description" cssClass="error-msg" />
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
	                		
					    </div>
					</div>
				</div>
				<div class="row">
                	<div class="col-lg-12">
                		<div class="col-lg-6">
                			<div class="image-block">
	                			<label>상품사진</label>
	                			<img src="${addItem.media_photo_url}" height="200" width="200" class="">
	                			<form:input path="media_media_id" type="text" cssClass="display-none" />
	                			<form:errors path="media_media_id" cssClass="error-msg" />
	                			<form:input path="media_photo_url" type="text" cssClass="display-none" />
	                			<input type="button" class="btn btn-primary" id="add-image" value="이미지 업로드" onclick="ADMIN.photoUpload(this)">
                			</div>
                		</div>
                	</div>
                </div>
					<div class="row">
						<div class="col-lg-12 das">
							<div class="col-lg-6">
								<div class="form-group">
									<label>배송일 관리</label>
										<div class="form-block mes-cho">
											<meesig:radiobutton path="item_shipping_day_state" items="${addItem.shippingDaySelectList}" labelClass="radio-inline"/>
											<form:errors path="item_shipping_day_state" cssClass="error-msg" />
										</div>
		                		</div>
							</div>
							<div class="col-lg-6">
								<div class="1" style="display:none;">
									<label>발송일 선택(발송가능한 날짜 선택)</label>
									<div class="form-block">
									    <label class="checkbox-inline">
									    	<form:checkbox path="sdm.dayOpt1" value="1" />월</label>
									    <label class="checkbox-inline">
									    	<form:checkbox path="sdm.dayOpt1" value="2"/>화</label>
									    <label class="checkbox-inline">
									    	<form:checkbox path="sdm.dayOpt1" value="3"/>수</label>
									    <label class="checkbox-inline">
										  	<form:checkbox path="sdm.dayOpt1" value="4"/>목</label>
										<label class="checkbox-inline">
										  	<form:checkbox path="sdm.dayOpt1" value="5"/>금</label>
										<label class="checkbox-inline">
										  	<form:checkbox path="sdm.dayOpt1" value="6"/>토</label>
									</div>
									<label>주문마감시간</label>
									<div class="form-block">
										<form:input path="sdm.dayOpt1time" type="time"/>
									</div>
								</div>
								<div class="2" style="display:none;">
									<label>발송일 선택(발송가능한 날짜 선택)</label>
									<div class="form-block">
									    <label class="checkbox-inline">
									    	<form:checkbox path="sdm.dayOpt2" value="1" />월</label>
									    <label class="checkbox-inline">
									    	<form:checkbox path="sdm.dayOpt2" value="2"/>화</label>
									    <label class="checkbox-inline">
									    	<form:checkbox path="sdm.dayOpt2" value="3"/>수</label>
									    <label class="checkbox-inline">
										  	<form:checkbox path="sdm.dayOpt2" value="4"/>목</label>
										<label class="checkbox-inline">
										  	<form:checkbox path="sdm.dayOpt2" value="5"/>금</label>
										<label class="checkbox-inline">
										  	<form:checkbox path="sdm.dayOpt2" value="6"/>토</label>
									</div>
									<label>주문마감시간</label>
									<div class="form-block">
										<form:input path="sdm.dayOpt2time" type="time"/>
									</div>
								</div>
								<div class="3" style="display:none;">
									<label>발송일 선택</label>
									<div class="form-block">
								    	<form:input path="sdm.dayOpt3" type="date"/>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12 dps">
							<div class="col-lg-6">
								<div class="form-group">
									<label>배송비 관리</label>
										<div class="form-block mes-cho">
										    <meesig:radiobutton path="item_shipping_price_state" items="${addItem.shippingPriceSelectList}" labelClass="radio-inline"/>
											<form:errors path="item_shipping_price_state" cssClass="error-msg" />
										</div>
		                		</div>
							</div>
							<div class="col-lg-6">
								<div class="1" style="display:none;">
									<label>무료배송</label>
								</div>
								<div class="2" style="display:none;">
									<label>배송비 입력</label>
									<div class="form-inline">
								    	<form:input path="spm.priceOpt2" type="text" cssClass="form-control"/>원
									</div>
								</div>
								<div class="3" style="display:none;">
									<label>선택형 배송비 (내용 / 가격)</label>
									<c:forEach var="des" items="${addItem.spm.priceOpt3Des}" varStatus="n">
										<meesig:mi path1="spm.priceOpt3Des" path2="spm.priceOpt3Pri" item1="${des}" item2="${addItem.spm.priceOpt3Pri[n.index]}"/>
									</c:forEach>
										<meesig:mi path1="spm.priceOpt3Des" path2="spm.priceOpt3Pri" item1="" item2=""/>
										<input type="button" class="btn btn-primary" id="adddpo" value="추가" onclick="ADMIN.addDeliveryPriceOptringInput(this)"/>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12 os">
							<div class="col-lg-6">
								<div class="form-group">
									<label>상태</label>
									<div class="form-block mes-cho">
									   <meesig:radiobutton path="item_option_state" items="${addItem.itemOptionSelectList}" labelClass="radio-inline"/>
										<form:errors path="item_option_state" cssClass="error-msg" />
									</div>
		                		</div>
							</div>
							<div class="col-lg-6">
								<div class="1" style="display:none;">
									<label>옵션 없음</label>
									
								</div>
								<div class="2" style="display:none;">
									<label>옵션 항목 (옵션명 / 가격)</label>
									<meesig:option item="${addItem.iom}"/>																		
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12 os">
							<div class="col-lg-6">
							<div class="form-group">
								<label>상태</label>
								<div class="form-block mes-cho">
								   <meesig:radiobutton path="item_state" items="${addItem.itemStateList}" labelClass="radio-inline"/>
									<form:errors path="item_state" cssClass="error-msg" />
								</div>
	                		</div>
							</div>
							<div class="col-lg-6">
							</div>
						</div>
					</div>

					<!-- Textarea For Tinymce -->
	                <div class="row">
	                	<div class="col-lg-12">
	                		<div class="col-lg-12">
							    <div class="form-group">
							   		<label>Contents</label>
							    	<form:textarea path="item_content" cssClass="form-control" rows="20" />
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
									<form:textarea path="item_shipping" cssClass="form-control" rows="5" />						    	
							   	</div>
						   	</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="col-lg-3">
								<input type="button" class="btn btn-danger" id="savebutton" value="저장" />
							</div>
						</div>
					</div>
				
				</form:form>
                
                <div class="row">
                	<div class="col-lg-12">
                		<div class="block-400">
                		</div>
                	</div>
                </div>
                
			</div>
			<!-- Photo Uploader -->
	        <div id="photo-uploader" class="pop-layer" >
	            <div class="bg"></div>
                <div class="pop-container">
                	<div class="btn-r">
                		<span class="glyphicon glyphicon-remove close-btn"></span>
                	</div>
                	<div class="pop-conts">
                		<h4><label  class="col-lg-10">상품 이미지</label></h4>
                		<form method="POST" enctype="multipart/form-data" action="" role="form" class="form-horizontal" id="">
                			 <div class="form-group">
                			 	<label class="col-lg-3 control-label">이미지</label>
                			 	<div class="col-lg-7">
                			 		<input type="file" name="file">
                			 		<span class="error-msg"></span>
                			 	</div>
					         </div>
					         <div class="form-group">
					         	<label class="col-lg-3 control-label">설명</label>
					         	<div class="col-lg-7">
					         		<input type="text" name="des" class="form-control">
					         	</div>
					         </div>
					         <div class="form-group">
					         	<div class="col-lg-offset-3 col-lg-7">
					         		<input type="submit" class="btn btn-primary" value="파일 올리기">
					         	</div>
					         </div>
					     </form>
                	</div>
                	
                </div>
            </div>
        </div>
		
      
<content tag="local_script">
	<!-- 	<script src="/js/tinymce/tinymce.min.js"></script>
			<script src="/js/tinymce.init.js"></script>
			<script src="/js/item.choice.js"></script>
		-->
</content>
        
</body>
</html>