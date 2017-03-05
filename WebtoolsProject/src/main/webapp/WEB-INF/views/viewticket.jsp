<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="home.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap4.min.js"></script>

<script src="resources/main.js"></script>
<link rel="stylesheet" type="text/css" href="home.css">
<style>
.download:link, .download:visited {
	background-color: #f44336;
	color: white;
	padding: 8px 15px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
}

.download:hover, .download:active {
	background-color: red;
}

.table td {
	text-align: center;
}

<!--
Footer CSS Start -->#footerrow {
	margin-top: 5px;
	padding-bottom: 100px;
	background-color: black;
}

#footer_container {
	margin-top: 80px !important;
}

.footer {
	font-size: 20px;
	color: #ffcb05;
	font-weight: bold;
	font-family: sourcesanspro;
}

.footer_details ul {
	list-style: none;
	list-style-position: outside;
	padding: 0px 0px 15px;
}

.footer_details ul li a {
	color: white;
	margin-bottom: 15px;
	text-decoration: none;
}

.footer_details ul li {
	padding-top: 8px;
}

.footer_details ul li a:hover {
	color: #ffcb05;
}

iframe {
	width: 350px;
	height: 335px;
}
</style>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="container">

		<c:if test="${empty sessionScope.customer.tickets }">
			<h2>
				No Tickets Booked yet. Click<a id="home" href="home.htm?action=home">
					<i>here</i>
				</a>to start booking
			</h2>
		</c:if>

		<c:if test="${!empty sessionScope.customer.tickets }">
			<h2>List Of Tickets Booked</h2>

			<table class="table table-bordered table-hover" id="table">
				<thead>
					<tr class="success">

						<th>Ticket Id</th>
						<th>Movie Image</th>
						<th>Movie</th>
						<th>Theatre</th>
						<th>Location</th>
						<th>Auditorium</th>
						<th>ShowTime</th>
						<th>Total Seats</th>
						<th>Total Cost</th>
						<th>Download</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="ticket" items="${sessionScope.customer.tickets}">

						<tr>
							<td>${ticket.ticketid}</td>
							<td><img src="${ticket.movie.photoName}"
								style="width: 100px" /></td>
							<td>${ticket.movie.title}</td>
							<td>${ticket.showTimings.theatre.name}</td>
							<td>${ticket.showTimings.theatre.location.locate}</td>
							<td>${ticket.showTimings.audi.name}</td>
							<td>${ticket.showTimings.date}</td>
							<td>${ticket.noOfSeats}</td>
							<td>${ticket.noOfSeats * ticket.cost}</td>
							<td><a class="download"
								href="ticketpdf.htm?ticket=${ticket.ticketid}" target="_blank">eTicket</a></td>
						</tr>

					</c:forEach>


					</tr>
				</tbody>
			</table>
		</c:if>
	</div>
	<c:if test="${fn:length(customer.tickets) gt 4}">
		<jsp:directive.include file="footer.jsp" />
	</c:if>
	<script>
		$(document).ready(function() {
			var table = $('#table').DataTable();
		});
	</script>
</body>
</html>
