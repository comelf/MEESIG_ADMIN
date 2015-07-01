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
                        <h1 class="page-header">추천상품<small>Recommend</small></h1>
                        <ol class="breadcrumb">
                            <li class="active">메인화면 추천상품 관리</li>
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
            	<form id="form-banner" role="form" action="/main/rectop/update" method="post" >
            	<div class="col-lg-12">
            		<label>WEEKLY BEST</label>
            		<div class="form-group">
	            		<img src="${wBest.media.originImg}" style="width: 200px;">
	            		<input class="form-control hidden" name="row_id" type="text" value="${wBest.row_id }" readonly="readonly">
		      			<input class="form-control hidden" name="main_key" type="text" value="${wBest.main_key}" readonly="readonly">
		      			<input class="form-control hidden" name="item_id" type="text" value="${wBest.items_item_id}" readonly="readonly">
		      			<input class="form-control hidden" name="media_id" type="text" value="${wBest.media_media_id}" readonly="readonly">
		      			<input type="button" class="btn btn-primary recommend-change" value="상품변경">
	      			</div>
	      			<label>SOMETING NEW</label>
            		<div class="form-group">
	            		<img src="${sNew.media.sumnailImg}" style="width: 200px;">
	            		<input class="form-control hidden" name="row_id" type="text" value="${sNew.row_id }" readonly="readonly">
		      			<input class="form-control hidden" name="main_key" type="text" value="${sNew.main_key}" readonly="readonly">
		      			<input class="form-control hidden" name="item_id" type="text" value="${sNew.items_item_id}" readonly="readonly">
		      			<input class="form-control hidden" name="media_id" type="text" value="${sNew.media_media_id}" readonly="readonly">
		      			<input type="button" class="btn btn-primary recommend-change" value="상품변경">
	      			</div>
            	</div>
      				<div class="col-lg-12">
						<div class="form-group">
							<input type="submit" class="btn btn-danger" id="savebutton" value="저장">
						</div>
					</div>
					<hr>
            	</form>
            </div>
            
            
      		
      		<form id="form-banner" role="form" action="/main/recommend/update" method="post" >
	      		<c:forEach var="recomend" items="${recommends}">
	      			<div class="row recommend-row">
	      				<div class="col-lg-12">
	      					<div class="recommend-component">
	      						<div class="form-group recommend-form">
	      							<input class="form-control hidden" name="row_id" type="text" value="${recomend.row_id}" readonly="readonly">
	      							<input class="form-control hidden" name="main_key" type="text" value="${recomend.main_key}" readonly="readonly">
	      							<input class="form-control hidden" name="item_id" type="text" value="${recomend.items_item_id}" readonly="readonly">
	      							<input class="form-control hidden" name="media_id" type="text" value="${recomend.media_media_id}" readonly="readonly">
									<img src="${recomend.media.sumnailImg}" style="width: 200px;">
									<input type="button" class="btn btn-primary recommend-upload" value="상품선택">
	                			</div>
	                			<div  class="form-inline">
	                				<label>순서</label><input class="form-control" name="main_order" type="text" value="${recomend.main_order}">
									<label>타이틀</label><input class="form-control" name="main_text" type="text" value="${recomend.main_text}">
	                				<label>상세</label><input class="form-control" name="main_sub" type="text" value="${recomend.main_sub}">
	                			</div>
							</div>
						</div>
						<hr>
					</div>
				</c:forEach>
					<div class="row">
	      				<div class="col-lg-12">
							<div class="form-group">
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
			<script src="/js/recommend.js"></script>
		-->
</content>
</body>
</html>