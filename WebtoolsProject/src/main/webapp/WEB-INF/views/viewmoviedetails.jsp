<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="home.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>


<script src="resources/main.js"></script>
</head>

<body>

	<jsp:directive.include file="header.jsp" />

	<div class="container">
		<div class="row" id="row1">
			<div class="col-lg-12 moviename">
				<h2>${movie.title}</h2>
			</div>
		</div>

		<div class="row" id="row2">
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-5">
				<img src="${movie.photoName}" alt="${movie.title}" class="imagesize" />
			</div>
			<div class="col-lg-8 col-md-8 col-sm-9 col-xs-7">
				<div class="col-lg-4 col-md-4 col-sm-4 moviedetails">
					<strong>Movie Title:</strong>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-8 moviedetails">${movie.title}</div>
				<div class="col-lg-4 col-md-4 col-sm-4 moviedetails">
					<strong>Release Year:</strong>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-8 moviedetails">${movie.year}</div>
				<div class="col-lg-4 col-md-4 col-sm-4 moviedetails">
					<strong>Length:</strong>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-8 moviedetails">${movie.length}
					minutes</div>
				<div class="col-lg-4 col-md-4 col-sm-4 moviedetails">
					<strong>Category:</strong>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-8 moviedetails">${movie.genre.name}</div>
				<div class="col-lg-4 col-md-4 col-sm-4 moviedetails">
					<strong>Directors:</strong>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-8 moviedetails">
					<c:forEach var="director" items="${movie.directors}">
				${director.firstName} ${director.lastName}
				</c:forEach>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4 moviedetails">
					<strong>Actors:</strong>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-8 moviedetails">
					<c:forEach var="actor" items="${movie.actors}">
				${actor.firstName} ${actor.lastName}
				</c:forEach>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4 moviedetails">
					<strong>Actress:</strong>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-8 moviedetails">
					<c:forEach var="actress" items="${movie.actress}">
				${actress.firstName} ${actress.lastName}
				</c:forEach>
				</div>
			</div>
		</div>
		<div class="row" id="plot">
			<div class="col-lg-5">
				<h3>The plot :</h3>${movie.description}
				</div>

		</div>
		<c:if test="${movie.category ne 'Coming Soon' }">
			<div class="row" id="row3">
				<div class="col-lg-7"></div>
				<div class="col-lg-5">
					<button class="button" onclick='showlocations("${movie.movieid}")'>
						<span class="detail">Proceed to Booking</span>
					</button>
				</div>
			</div>
		</c:if>
		<div class="row" id="row4">
			<div class="col-lg-12" id="location"></div>
		</div>
		<div class="row" id="row5">
			<div class="col-lg-12" id="theatre"></div>
		</div>
		<div class="row" id="row6">
			<div class="col-lg-12" id="showtimings"></div>
		</div>
		<div class="row" id="row7">
			<div class="col-lg-12" id="price">
				<span><strong>Select Price ==> </strong></span> <label
					class='radio-inline'> <input type='radio' name='price'
					onclick='showseats()' value='100'>$ 100
				</label> <label class='radio-inline'> <input type='radio'
					name='price' onclick='showseats()' value='200'>$ 200
				</label> <label class='radio-inline'> <input type='radio'
					name='price' onclick='showseats()' value='300'>$ 300
				</label>
			</div>
		</div>
		<div class="row" id="row8">
			<div class="col-lg-12" id="seats">
				<span><strong>Select Number Of Seats ==> </strong></span> <label
					class='radio-inline'> <input type='radio' name='seat'
					onclick='book()' value='1'> 1
				</label> <label class='radio-inline'> <input type='radio'
					name='seat' onclick='book()' value='2'> 2
				</label> <label class='radio-inline'> <input type='radio'
					name='seat' onclick='book()' value='3'> 3
				</label> <label class='radio-inline'> <input type='radio'
					name='seat' onclick='book()' value='4'> 4
				</label>
			</div>
		</div>
		<div class="row" id="row9">
			<div class="col-lg-7"></div>
			<div class="col-lg-5">
				<button class="button" onclick='bookticket()' id="book">
					<span class="detail"> Book </span>
				</button>
				<span id="message"></span>
			</div>
		</div>
	</div>
	<jsp:directive.include file="footer.jsp" />
	<input type="hidden" value="${movie.movieid}" id="movieid" />
	<style>
.imagesize {
	width: 90%;
	height: 400px;
}

.moviedetails {
	font-size: 23px;
	font-style: sans-serif;
	margin-bottom: 22px;
}

.button {
	height: 32px;
	width: 180px;
	border: none;
	padding-top: 5px;
	border-radius: 5px;
	box-shadow: 0px 3px #000;
	background-color: #ffcb05;
	font-weight: bold;
	font-style: sourcesanspro;
}

.button:active {
	background-color: #ffcb05;
	box-shadow: 0 2px #666;
	transform: translateY(2px);
}

#location, #theatre, #showtimings, #price, #seats {
	font-size: 20px;
	margin-bottom: 10px;
}

.moviename {
	font-size: 24px;
	color: #000;
	text-transform: uppercase;
	margin-top: 40px;
	border-bottom: solid 2px #3e3e3e;
	padding-bottom: 3px;
	margin: 15px 0;
	letter-spacing: -1px;
}

#plot h3 {
	text-transform: uppercase;
	font-size: 20px;
	letter-spacing: 1px;
	font-weight: bold;
}
#plot div {
	font-size: 15px;
	letter-spacing: 1px;
	font-weight: bold;
}

#seats, #price, #book {
	display: none;
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
	<script>
		$(".detail").click(function() {

			if ($(this).text() == "Proceed to Booking") {
				$(this).text("Restart Booking");
			}
		});
		function showlocations(movieid) {

			$("#location").empty();
			$("#theatre").empty();
			$("#showtimings").empty();
			$("#price").hide();
			$("#seats").hide();
			$("#book").hide();
			$("#message").html("");

			$.ajax({
				type : "GET",
				url : "displaymoviebasedlocations1.htm",
				data : {
					movieid : movieid
				},
				dataType : "html",
				success : function(data) {

					$("#location").html(data);

				}

			});
		}

		function showtheatres(locationid) {
			$("#showtimings").empty();
			$("#price").hide();
			$("#seats").hide();
			$("#book").hide();
			$("#message").html("");
			var movieid = $("#movieid").val();

			$.ajax({
				type : "GET",
				url : "displaylocationbasedtheatres1.htm",
				data : {
					locationid : locationid,
					movieid : movieid
				},
				dataType : "html",
				success : function(data) {

					$("#theatre").html(data);

				}

			});
		}

		function showtimings(theatreid) {
			$("#price").hide();
			$("#seats").hide();
			$("#book").hide();
			$("#message").html("");
			var movieid = $("#movieid").val();
			var locationid = $('input:radio[name=locationid]:checked').val();

			$.ajax({
				type : "GET",
				url : "displaytheatrebasedshowtimings1.htm",
				data : {
					locationid : locationid,
					movieid : movieid,
					theatreid : theatreid
				},
				dataType : "html",
				success : function(data) {

					$("#showtimings").html(data);

				}

			});
		}

		function showprice() {

			$("#price").show();
			$("#seats").hide();
			$("#book").hide();
			$("#message").html("");
		}

		function showseats() {

			$("#seats").show();
			$("#book").hide();
			$("#message").html("");
		}
		function book() {

			$("#book").show();
			$("#message").html("");
		}

		function bookticket() {

			var movieid = $("#movieid").val();
			var locationid = $('input:radio[name=locationid]:checked').val();
			var theatreid = $('input:radio[name=theatreid]:checked').val();
			var showTimeId = $('input:radio[name=showtimeid]:checked').val();
			var cost = $('input:radio[name=price]:checked').val();
			var seat = $('input:radio[name=seat]:checked').val();

			$.ajax({
				type : "GET",
				url : "bookticket.htm",
				data : {
					movieid : movieid,
					locationid : locationid,
					theatreid : theatreid,
					showTimeId : showTimeId,
					cost : cost,
					seat : seat
				},
				dataType : "html",
				success : function(data) {
					var data = data.split(",");
					// 			alert("hi");
					// 			alert(data[0] == "false");
					// 			alert(data[1].trim() == "false");

					if (data[0] == "true") {
						// 				$("#message").text("Booking Successful");
						// 				$("#message").css({
						// 					"color" : "green",
						// 					"font-weight" : "bold"
						// 				});

						location.href = "entercarddetails.htm";
					} else {
						$("#message").html(
								"Only " + data[1] + " seats remaining");
						$("#message").css({
							"color" : "red",
							"font-weight" : "bold"
						});
					}

				}

			});

		}
	</script>
</body>
</html>