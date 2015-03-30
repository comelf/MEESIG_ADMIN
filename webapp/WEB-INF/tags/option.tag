<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="item" required="true" rtexprvalue="true"
	type="com.meesig.model.ItemOptionManage"%>
<c:forEach var="block" items="${item.optionBlocks}" varStatus="b">
	<div class="form-group">
		<div class="form-inline">
	 		<form:input path="iom.optionBlocks[${b.index}].optionTitle" cssClass="form-control" placeholder="항목 이름"/>
	 		<input type="button" class="btn btn btn-warning" value="항목삭제" onclick="ADMIN.delItemOption(this)"/>
	 	</div>
		<c:forEach var="row" items="${block.optionRow}" varStatus="r">
			<div class="form-inline">
				<form:input path="iom.optionBlocks[${b.index}].optionRow[${r.index}].price" type="text" cssClass="form-control"/>
				<form:input path="iom.optionBlocks[${b.index}].optionRow[${r.index}].description" type="text" cssClass="form-control"/>
				<input type="button" class="btn btn btn-warning" value="삭제" onclick="ADMIN.delOptRow(this)"/>
			</div>
		</c:forEach>
			<input type="button" class="btn btn-primary" id="adddpo" value="옵션추가" onclick="ADMIN.addOptRow(this)"/>
	</div>		
</c:forEach>
<input type="button" class="btn btn-success" id="adddpo" value="새로운항목" onclick="ADMIN.addItemOption(this)"/>
