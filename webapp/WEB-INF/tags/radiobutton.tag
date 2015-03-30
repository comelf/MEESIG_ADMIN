<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="path" required="true"%>
<%@ attribute name="items" rtexprvalue="true" type="java.util.ArrayList"%>
<%@ attribute name="labelClass"%>
<c:forEach var="item" items="${items}">
	<label class="${labelClass}">
	<form:radiobutton path="${path}" value="${item.value}"/>${item.label}</label>
</c:forEach>
