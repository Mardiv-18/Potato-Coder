<%@ include file="include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://db4free.net:3306/creditmgt_db" user="marvensizer" password="marvensiser" />

<sql:query var="rs" dataSource="${db}">
		SELECT * FROM transaction_tbl;
</sql:query>

<div align="center">
<form action="">
	
	<table border="0" width="100%">
	<tr>
		<td colspan='5' style="color: black;">
			<%
				String notify = (String)request.getAttribute("notification");
				if(notify!=null){
					out.println(notify);
				}
				int count=0;
			%>
		</td>
	</tr>
	<tr style="color: black;">
			<th><c:out value="No." /></th>
			<th><c:out value="Sender" /></th>
			<th><c:out value="" />Receiver</th>
			<th><c:out value="" />Receiver Amount</th>
		</tr>
	<c:forEach var="table" items="${rs.rows}">
		<tr style="color: black;">
			<td><c:out value="No" /></td>
			<td><c:out value="${table.SENDER}" /></td>
			<td><c:out value="${table.RECEIVER}" /></td>
			<td><c:out value="${table.FUND}" /></td>
		</tr>
	</c:forEach>
</table>
</form>
</div>
<%@ include file="include/footer.jsp"%>