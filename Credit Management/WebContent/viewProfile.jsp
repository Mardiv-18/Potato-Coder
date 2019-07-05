<%@ include file="include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
	String USER= (String) request.getAttribute("USER");
	
%>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://db4free.net:3306/creditmgt_db"
	user="marvensizer" password="marvensiser" />

<sql:query var="rs" dataSource="${db}">
		SELECT * FROM customer_tbl WHERE NAME='<%= USER %>';
</sql:query>

<div class="card" style="background-color: #f8f8ff">
	<table border="0" width="100%">
	<tr>
		<td colspan='5' style="color: black;">
			<%
				String ERROR = (String)request.getAttribute("error");
				if(ERROR!=null){
					out.println("ERROR");
				}
			%>
		</td>
	</tr>
	<c:forEach var="table" items="${rs.rows}">
		<tr style="color: black;">
			<td>Name:</td>
			<td><c:out value="${table.NAME}" /></td>
		</tr>
		<tr style="color: black;">
			<td>Email:</td>
			<td><c:out value="${table.EMAIL}" /></td>
		</tr>
		<tr style="color: black;">
			<td>Contact No:</td>
			<td><c:out value="${table.CONTACT_NO}" /></td>
		</tr>
		<tr style="color: black;">
			<td>Credits:</td>
			<td><c:out value="${table.CREDITS}" /></td>
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="include/footer.jsp"%>