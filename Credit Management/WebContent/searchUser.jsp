<%@ include file="include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<div style="background-color: #f8f8ff">
	<form action="UserSearch">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="input-group">
						<input class="form-control border-secondary py-2" type="search"
							name="search">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" type="submit">
								Search</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<div style="background-color: #f8f8ff; margin-left:4%; margin-top:5%">
		<table border="0" width="100%">
			<tr>
				<td colspan='5' style="color: black;">
					<%
						String ERROR = (String) request.getAttribute("error");

						if (ERROR != null) {
							out.println(ERROR);
						}
					%>
				</td>
			</tr>
			<%
				if (ERROR == null) {
					String NAME = (String) request.getAttribute("NAME");
			%>

			<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
				url="jdbc:mysql://db4free.net:3306/creditmgt_db" user="marvensizer"
				password="marvensiser" />

			<sql:query var="rs" dataSource="${db}">
				SELECT * FROM customer_tbl WHERE NAME='<%=NAME%>';
			</sql:query>

			<c:forEach var="table" items="${rs.rows}">
				<tr style="color: black;">
					<td>Name:</td>
					<td><c:out value="${table.NAME}" /></td>
					<td rowspan='3'><button class="btn btn-dark" type="button">
						<a href="UserProfile?uname=${table.NAME}">View</a></button></td>
				</tr>
				<tr style="color: black;">
					<td>Email:</td>
					<td><c:out value="${table.EMAIL}" /></td>
				</tr>
				
			</c:forEach>
			<%
				}
			%>


		</table>
	</div>


</div>
<%@ include file="include/footer.jsp"%>
