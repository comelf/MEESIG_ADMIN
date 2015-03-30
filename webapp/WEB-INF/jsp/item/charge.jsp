<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="meesig" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
				<div class="row">
					
                    <div class="col-lg-12">
                        <h1 class="page-header"> 상품 분류<small>Category</small></h1>
                        <ol class="breadcrumb">
                            <li class="active">상품 분류를 추가하거나 수정합니다.</li>
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
                
                <meesig:test/>
                
                
                <div class="row">
                	<div class="col-lg-12">
                		<div class="block-400">
                		</div>
                	</div>
                </div>
			</div>
        </div>
        
</body>
</html>