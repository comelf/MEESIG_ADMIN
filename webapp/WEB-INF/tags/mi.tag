<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="path1" required="true"%>
<%@ attribute name="path2" required="true"%>
<%@ attribute name="item1" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="item2" rtexprvalue="true" type="java.lang.String"%>
<div class="form-inline">
	<input id="${path1}" name="${path1}" class="form-control" type="text" value="${item1}">
	<input id="${path2}" name="${path2}" class="form-control" type="text" value="${item2}">
	<input type="button" class="btn btn btn-warning" value="삭제" onclick="ADMIN.delDpo(this)"/>
</div>


