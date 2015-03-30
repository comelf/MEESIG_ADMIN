<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" %>
<%@ attribute name="hi" %>

<font color="#333">
	<%
		for (int i = 0; i < 10; i++)
			out.print("★");
	%>
</font>

