<%@ include file="include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://db4free.net:3306/creditmgt_db" user="marvensizer" password="marvensiser" />

<sql:query var="rs" dataSource="${db}">
		SELECT * FROM customer_tbl;
</sql:query>

<div align="center">
<form action="">
	
	<table border="0" width="100%">
	<tr>
		<td colspan='5' style="color: black;">
			<%
				String ERROR = (String)request.getAttribute("error");
				if(ERROR!=null){
					out.println(ERROR);
				}
			%>
		</td>
	</tr>
	<c:forEach var="table" items="${rs.rows}">
		<tr style="color: black;">
			<td><c:out value="${table.NAME}" /></td>
			<td><c:out value="${table.EMAIL}" /></td>
			<td><button class="btn btn-outline-secondary" type="button">
			<a href="UserProfile?uname=${table.NAME}">View</a>
			</td>
		
		</tr>
	</c:forEach>
</table>
</form>
</div>
<%@ include file="include/footer.jsp"%>