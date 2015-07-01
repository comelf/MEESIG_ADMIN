<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>추천상품 관리</title>
</head>
<body>
	 <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
				<div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">메인화면 노출상품<small>Main Menus</small></h1>
                        <ol class="breadcrumb">
                            <li class="active">메인화면 노출상품 관리</li>
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
            
      		
      		<form id="form-banner" role="form" action="/main/menus/update" method="post" >
	      		<div class="row menus-row">
	      		<c:forEach var="menu" items="${menus}">
	      			
	      				<div class="col-lg-12">
	      					<div class="menus-component">
	      						<div class="form-inline menus-form">
	      							<input class="form-control hidden" name="row_id" type="text" value="${menu.row_id}" readonly="readonly">
	      							<input class="form-control hidden" name="main_key" type="text" value="${menu.main_key}" readonly="readonly">
	      							<input class="form-control hidden" name="item_id" type="text" value="${menu.items_item_id}" readonly="readonly">
	      							<input class="form-control hidden" name="media_id" type="text" value="${menu.media_media_id}" readonly="readonly">
									<img src="${menu.media.originImg}" style="width: 100px;">
									<input type="button" class="btn btn-primary menus-upload" value="상품선택">
									<label>순서</label><input class="form-control" name="main_order" type="text" value="${menu.main_order}">
									<label>타이틀</label><input class="form-control" name="main_text" type="text" value="${menu.main_text}" disabled="disabled" >
	                			</div>
							</div>
						</div>
						<hr>
					
				</c:forEach>
				</div>
					<div class="row">
	      				<div class="col-lg-12">
							<div class="form-group">
								<input type="button" class="btn btn-primary" id="newbutton" value="추가">
								<input type="submit" class="btn btn-danger" id="savebutton" value="저장">
							</div>
						</div>
				</div>
			</form>
			
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
                		<form method="POST" enctype="multipart/form-data" action="/media/originPhotoUpload" role="form" class="form-horizontal" id="form-img-upload">
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
	<!-- 	
			<script src="/js/plugins/handlebars-v3.0.3.js"></script>
			<script src="/js/meesig.admin.js"></script>
			<script src="/js/mainmenus.js"></script>
			<script id="menus-template" type="text/x-handlebars-template">
				<div class="col-lg-12">
	      				<div class="menus-component">
	      					<div class="form-inline menus-form">
	      						<input class="form-control hidden" name="row_id" type="text" value="0" readonly="readonly">
	      						<input class="form-control hidden" name="main_key" type="text" value="7" readonly="readonly">
	      						<input class="form-control hidden" name="item_id" type="text" value="0" readonly="readonly">
	      						<input class="form-control hidden" name="media_id" type="text" value="0" readonly="readonly">
								<img src="${menu.media.sumnailImg}" style="width: 100px;">
								<input type="button" class="btn btn-primary menus-upload" value="상품선택" onclick="MENUS.itemChoiceClick(event);">
								<label>순서</label><input class="form-control" name="main_order" type="text" value="1">
								<label>타이틀</label><input class="form-control" name="main_text" type="text" value="" readonly="readonly">
	                		</div>
					</div>
				</div>
				<hr>
			</script>
		-->
</content>
</body>
</html>