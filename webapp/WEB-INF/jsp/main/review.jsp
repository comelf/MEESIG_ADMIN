<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 리뷰 관리</title>
</head>
<body>
	 <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
				<div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">메인 리뷰<small>Review</small></h1>
                        <ol class="breadcrumb">
                            <li class="active">메인화면 리뷰 관리</li>
                        </ol>
                    </div>
                </div>
                
      		
      		<input type="text" id="review-type" class="hidden" value="1">
      		<form id="form-review" role="form" action="/main/review/update" method="post" >
	      		<div class="row review-row">
	      		<c:forEach var="review" items="${reviews}">
	      			
	      				<div class="col-lg-12">
	      					<div class="review-component">
	      						<div class="form-group">
	      							<input class="form-control hidden" name="row_id" type="text" value="${review.row_id}" readonly="readonly">
	      							<input class="form-control hidden" name="main_key" type="text" value="${review.main_key}" readonly="readonly">
	      							<input class="form-control hidden" name="media_id" type="text" value="${review.media_media_id}" readonly="readonly">
									<img src="${review.media.originImg}" style="width: 200px;">
									<input type="button" class="btn btn-primary review-upload" value="파일 올리기">
	                			</div>
	                			<div  class="form-group">
	                				<label>순서</label><input class="form-control" name="main_order" type="text" value="${review.main_order}">
									<label>리뷰</label><input class="form-control" name="main_text" type="text" value="${review.main_text}">
	                				<label>작성자</label><input class="form-control" name="main_sub" type="text" value="${review.main_sub}">
	                				<label>메뉴</label><input class="form-control" name="item_id" type="text" value="${review.items_item_id}" required="required" readonly="readonly">
	                				<input type="button" class="btn btn-primary review-item-choice" value="메뉴선택">
	                			</div>
							</div>
							<hr>
						</div>
					
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
			<script src="/js/review.js"></script>
			<script id="review-template" type="text/x-handlebars-template">
				<div class="col-lg-12">
      				<div class="review-component">
      					<div class="form-group">
      						<input class="form-control hidden" name="row_id" type="text" value="0" readonly="readonly">
      						<input class="form-control hidden" name="main_key" type="text" value="6" readonly="readonly">
      						<input class="form-control hidden" name="media_id" type="text" value="0" readonly="readonly">
							<img src="${review.media.sumnailImg}"  style="width: 200px;">
							<input type="button" class="btn btn-primary review-upload" value="파일 올리기" onclick="REVIEW.reviewUploadClick(event)">
                		</div>
                		<div  class="form-group">
                			<label>순서</label><input class="form-control" name="main_order" type="text" value="1">
							<label>리뷰</label><input class="form-control" name="main_text" type="text" value="">
                			<label>작성자</label><input class="form-control" name="main_sub" type="text" value="">
                			<label>메뉴</label><input class="form-control" name="item_id" type="text" value="" required="required" readonly="readonly">
                			<input type="button" class="btn btn-primary review-item-choice" value="메뉴선택" onclick="REVIEW.itemChoiceClick(event)">
                		</div>
					</div>
				</div>
			</script>
		-->
</content>
</body>
</html>