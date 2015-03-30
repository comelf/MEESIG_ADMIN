<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>

	<c:choose>
		<c:when test="${category.category_id < 1}">
			<c:set var="action" value="/item/category/add"/>
			<c:set var="btn_msg" value="추가"/>
		</c:when>
		<c:otherwise>
			<c:set var="action" value="/item/category/edit"/>
			<c:set var="btn_msg" value="편집"/>
		</c:otherwise>
	</c:choose>
	
	
	

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
                
                <div class="row">
                	<div class="col-lg-6">
                		<div class="table-responsive">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>+</th>
										<th>분류</th>
										<th>-</th>
										<th>-</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="row" items="${categoryList}">
										<tr>
											<td>${row.category_id}</td>
											<td>${row.category_name}</td>
											<td><a href="/item/category?category_id=${row.category_id}">편집</a></td>
											<td><a href="/item/category/delete?category_id=${row.category_id}">삭제</a></td>
										</tr>
									</c:forEach>
								</tbody>
								</div>
							</table>
						</div>
                	</div>
                	<div class="col-lg-6">
                		<c:if test=""></c:if>
                		<form:form modelAttribute="category" method="post" action="${action}">
                				<form:input path="category_id" type="text" cssClass="form-control display-none" readonly="true"/>
                			<div class="form-group">
								<label>분류명</label>
                				<form:input path="category_name" type="text" cssStyle="form-control"/>
                				<button type="submit" class="btn btn-danger">${btn_msg}</button>
                			</div>
                		</form:form>
                	</div>
                </div>
                
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