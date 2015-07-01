<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
				<div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">메인배너<small>Banner</small></h1>
                        <ol class="breadcrumb">
                            <li class="active">메인화면 배너 리스트 및 관리</li>
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
                
      		
      		<input type="text" id="banner-type" class="hidden" value="2">
      		<form id="form-banner" role="form" action="/main/mobilebanner/create" method="post" >
	      		<div class="row banner-row">
	      		<c:forEach var="banner" items="${mobileBanners}">
	      			
	      				<div class="col-lg-12">
	      					<div class="banner-component">
	      						<div class="form-group">
	      							<input class="form-control hidden" name="slide_id" type="text" value="${banner.slide_id}" readonly="readonly">
	      							<input class="form-control hidden" name="slide_type" type="text" value="${banner.slide_type}" readonly="readonly">
	      							<input class="form-control hidden" name="slide_src" type="text" value="${banner.slide_src}" readonly="readonly">
	      							<input class="form-control hidden" name="media_id" type="text" value="${banner.MEDIA_media_id}" readonly="readonly">
									<img src="${banner.slide_src}"  style="height: 100px;">
									<input type="button" class="btn btn-primary banner-upload" value="파일 올리기">
	                			</div>
	                			<div  class="form-inline">
	                				<label>순서</label><input class="form-control" name="slide_order" type="text" value="${banner.slide_order}">
									<label>링크</label><input class="form-control" name="slide_href" type="text" value="${banner.slide_href}">
	                				<label>설명</label><input class="form-control" name="slide_des" type="text" value="${banner.slide_des}">
	                			</div>
							</div>
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
			<script src="/js/banner.js"></script>
			<script id="banner-template" type="text/x-handlebars-template">
				<div class="col-lg-12">
				<div class="banner-component">
					<div class="form-group">
						<input class="form-control hidden" name="slide_id" type="text" value="0" readonly="readonly">
						<input class="form-control hidden" name="slide_type" type="text" value="1" readonly="readonly">
						<input class="form-control hidden" name="slide_src" type="text" value="" readonly="readonly">
						<input class="form-control hidden" name="media_id" type="text" value="" readonly="readonly">
						<img src="" style="height: 100px;">
						<input type="button" class="btn btn-primary banner-upload" value="파일 올리기" onclick="BANNER.bannerUploadClick(event)">
					</div>
					<div class="form-inline">
						<label>순서</label><input class="form-control" name="slide_order" type="text" value="${banner.slide_order}">
						<label>링크</label><input class="form-control" name="slide_href" type="text" value="${banner.slide_href}">
	                	<label>설명</label><input class="form-control" name="slide_des" type="text" value="${banner.slide_des}">
					</div>
				</div>
				</div>
			</script>
		-->
</content>
</body>
</html>