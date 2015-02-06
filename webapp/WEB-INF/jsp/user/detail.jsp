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
                        <h1 class="page-header"> 회원 편집  <small>User</small></h1>
                        <ol class="breadcrumb">
                            <li class="active"> 회원 정보를 수정합니다.</li>
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
                	<form:form modelAttribute="user" role="form" action="/user/edit" method="post">
                		<div class="col-lg-5">
                			
                				<form:input path="user_id" cssClass="form-control display-none" readonly="true"/>
                			
							<div class="form-group">
							    <label>ID</label>
							    <form:input path="user_login_id" type="text" cssClass="form-control" placeholder="User ID" readonly="true"/>
							    <form:errors path="user_login_id" cssClass="error-msg" />
							</div>
							<div class="form-group">
							    <label for="exampleInputPassword1">Password</label>
							    <form:input path="user_password" type="password" cssClass="form-control" placeholder="Password" />
							    <form:errors path="user_password" cssClass="error-msg" />
							</div>
							<div class="form-group">
							    <label>Name</label>
							    <form:input path="user_name" type="text" cssClass="form-control" placeholder="User Name" />
							    <form:errors path="user_name" cssClass="error-msg" />
							</div>
							<div class="form-group">
							    <label>Email address</label>
							    <form:input path="user_email" type="email" cssClass="form-control" placeholder="E-mail" />
							    <form:errors path="user_email" cssClass="error-msg" />
							</div>
							<div class="form-group">
							    <label>생년월일</label>
							    <form:input path="user_b_date" type="text" cssClass="form-control" placeholder="Disabled" disabled="true" />
							</div>
							<div class="form-group">
							    <label>가입일</label>
							    <form:input path="user_join_date" type="text" cssClass="form-control" placeholder="Disabled" disabled="true" />
							</div>
							<div class="form-group">
							    <label>탈퇴여부</label>
							    <form:select path="user_enabled" cssClass="form-control">
							    	<form:option value="0">아니오</form:option>
							    	<form:option value="1">예</form:option>
							    </form:select>
							  </div>
							<div class="form-group">
							    <label>탈퇴일</label>
							    <form:input path="user_exit_date" type="text" cssClass="form-control" placeholder="" disabled="true" />
							</div>
							
							
						</div>
						<div class="col-lg-5">
						  	<div class="form-group">
							    <label>성별</label>
							    <div class="form-block">
							    <label class="radio-inline">
							    	<form:radiobutton path="user_gender" value="남"/>남</label>
								<label class="radio-inline">
								  	<form:radiobutton path="user_gender" value="여"/> 여</label>
								</div>
								<form:errors path="user_gender" cssClass="error-msg" />
						  	</div>
							  <div class="form-group">
							    <label>등급</label>
							    <input type="text" class="form-control" id="disabledInput" placeholder="Disabled" disabled>
							  </div>
							  <div class="form-group">
							    <label>권한</label>
							    <form:select path="user_role" cssClass="form-control">
							    	<form:option value="USERS">USERS</form:option>
							    	<form:option value="MANAGER">MANAGER</form:option>
							    	<form:option value="COWORKER">COWORKER</form:option>
							    	<form:option value="ADMIN">ADMIN</form:option>
							    </form:select>
							  </div>
							  <div class="form-group">
							    <label">지역</label>
							    <form:input path="user_location" type="text" cssClass="form-control" placeholder="Location"/>
							    <form:errors path="user_location" cssClass="error-msg" />
							  </div>
							  <div class="form-group">
							    <label>상태</label>
							    <form:select path="user_status" cssClass="form-control">
							    	<form:option value="0">0</form:option>
							    	<form:option value="1">1</form:option>
							    	<form:option value="2">2</form:option>
							    	<form:option value="3">3</form:option>
							    </form:select>
							  </div>
							  <div class="form-group">
							    	<label">포인트</label>
							    	<form:input path="user_point" type="text" cssClass="form-control" placeholder="0"/>
							    	<form:errors path="user_point" cssClass="error-msg" />
							  </div>
						  	<button type="submit" class="btn btn-danger">수정</button>
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
        
</body>
</html>