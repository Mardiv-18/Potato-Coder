<%@ include file="include/header.jsp"%>
<div style="background-color: #f8f8ff">
	<form action="TransferCredit">
		<%
			String ERROR = (String) request.getAttribute("error");
			if (ERROR != null) {
		%>
		<div class="row" align="center" style="margin-bottom: 3%;">
			<div class="col" style="margin-top: 5%">
				<h3>
					<%
						out.println(ERROR);
					%>
				</h3>

			</div>
		</div>
		<%
			}
		%>
		<div class="row">
			<div class="col">
				<input type="text" class="form-control" placeholder="From Where"
					name="FromUser">
			</div>
			<div class="col">
				<input type="text" class="form-control" placeholder="To Which"
					name="ToUser">
			</div>
		</div>

		<div class="row" align="center">
			<div class="col" style="margin-top: 5%">
				<input type="text" class="form-control" placeholder="Amount"
					name="Amount">
			</div>
		</div>

		<div class="row" align="center">
			<div class="col" style="margin-top: 5%">
				<button class="btn btn-outline-secondary" type="submit">Submit</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="include/footer.jsp"%>