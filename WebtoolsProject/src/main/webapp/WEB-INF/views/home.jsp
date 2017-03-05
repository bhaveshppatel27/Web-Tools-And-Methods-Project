<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link rel="stylesheet" type="text/css" href="searchbar.css">

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
			<div class="col-lg-8 col-md-8">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
						<li data-target="#myCarousel" data-slide-to="3"></li>
						<li data-target="#myCarousel" data-slide-to="4"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img src="car/carousal1.jpg" alt="1" style="width: 100%" />
						</div>

						<div class="item">
							<img src="car/carousal2.jpg" alt="2" style="width: 100%" />
						</div>

						<div class="item">
							<img src="car/carousal3.jpg" alt="3" style="width: 100%" />
						</div>

						<div class="item">
							<img src="car/carousal4.jpg" alt="4" style="width: 100%" />
						</div>
						<div class="item">
							<img src="car/carousal5.jpg" alt="5" style="width: 100%" />
						</div>
					</div>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>

			<div class="col-lg-4" id="sidebookingbar">
				<form id="SelectForm" method="post" class="form-horizontal"
					action="#">
					<h3 class="quickbook">QUICKBOOK</h3>

					<label for="movie">Select Movie</label>
					<div class="input-group">
						<select class="form-control" required="true" id="movie"
							onchange="moviechange()" onclick="moviechange()">

							<c:forEach var="movie" items="${sessionScope.quickbookmovies}">
								<option data-icon="glyphicon glyphicon-music"
									value="${movie.movieid}">${movie.title}</option>
							</c:forEach>

						</select> <span class="input-group-addon"><span
							class="glyphicon glyphicon-asterisk"></span></span>

					</div>

					<label for="location">Select Location</label>
					<div class="input-group">
						<select class="form-control" required="true" id="location"
							onchange="locationchange()" onclick="locationchange()">

						</select> <span class="input-group-addon"><span
							class="glyphicon glyphicon-asterisk"></span></span>

					</div>

					<label for="theatre">Select Theatre</label>
					<div class="input-group">
						<select class="form-control" required="true" id="theatre"
							onchange="theatrechange()" onclick="theatrechange()">

						</select> <span class="input-group-addon"><span
							class="glyphicon glyphicon-asterisk"></span></span>

					</div>

					<label for="date">Select Date</label>
					<div class="input-group">
						<select class="form-control" required="true" id="date">

						</select> <span class="input-group-addon"><span
							class="glyphicon glyphicon-asterisk"></span></span>

					</div>
					<div class="row">
						<div class="col-lg-6">
							<label for="class">Select Class</label>
							<div class="input-group">
								<select class="form-control" required="true" id="cost">

									<option data-icon="glyphicon glyphicon-usd" value="100">
										$100 Reg</option>
									<option data-icon="glyphicon glyphicon-usd" value="200">
										$200 Silver</option>
									<option data-icon="glyphicon glyphicon-usd" value="300">
										$300 Gold</option>
								</select>


							</div>
						</div>
						<div class="col-lg-6">
							<label for="seat">Number of Seats</label>
							<div class="input-group">
								<select class="form-control" required="true" id="seat">

									<option data-icon="glyphicon glyphicon-bed" value="1">1</option>
									<option data-icon="glyphicon glyphicon-bed" value="2">2</option>
									<option data-icon="glyphicon glyphicon-bed" value="3">3</option>
									<option data-icon="glyphicon glyphicon-bed" value="4">4</option>
								</select> <span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>

							</div>
						</div>
					</div>
					<div>
						<span id="message"></span> <a href="#" class="book"
							onclick="bookTicket()">Book Ticket</a>

					</div>
				</form>
			</div>
		</div>

		<!-- <div class="row" id = "searchmovie">
		<div class="col-lg-2">
			<label for="search">Search Movies By Name</label>
			
		</div >
		<div class="col-lg-2">
		
  		<input class="form-control" id="search" type="text" value = "" onkeyup="searchmoviesbyname(this.value)">
		
		</div>
		</div> -->

		<div class="row" id="custom">
			<div class="col-md-4 col-md-offset-0">
				<h2>Search Movies By Title</h2>

				<form action="" class="search-form">
					<div class="form-group has-feedback">
						<label for="search" class="sr-only">Search</label> <input
							type="text" class="form-control" name="search" id="search"
							onkeyup="searchmoviesbyname(this.value)" placeholder="search">
						<span class="glyphicon glyphicon-search form-control-feedback"></span>
					</div>
				</form>

			</div>
			<div class="col-md-3"></div>

			<div class="col-md-5 col-md-offset-0">
				<!-- <h4>
					Click <a href="#" onclick="callRest()">here</a> to get current
					weather information at Boston
				</h4> -->
				<div id="hidden" style="display: none">
					<img src="" alt="weather" id="icon" /><span id="weather"></span> <strong>
						at Boston </strong><strong><a href ="" id ="forecast" target="_window"> See Forecast</a></strong><br /> <span id="temperature"></span><span
						id="feelslike"></span><br /> <br /> <span id="updated"></span>
				</div>
			</div>

		</div>


		<div class="row" id="row2">
			<div class="col-lg-12">
				<div class="tabs">
					<ul class="list-unstyled" id="tabsul">
						<li><a href="#" class="active"
							onclick="showactive(this,'CurrentListings')">Now Showing</a></li>
						<li><a href="#" onclick="showactive(this,'Coming Soon')">Coming
								Soon</a></li>
						<li><a href="#" onclick="showactive(this,'Special Releases')">Special
								Releases</a></li>
						<li><a href="#" onclick="showactive(this,'Top Movies')">Top
								Movies</a></li>
					</ul>
				</div>
			</div>

		</div>

		<div class="row" id="row3">
			<c:forEach items="${movielist}" var="movie">
				<div class="col-lg-3 col-md-6 col-sm-6" class='rowformat'>
					<img src="${movie.photoName}" alt="5" />
					<div class="col-lg-7"
						style="overflow: hidden; padding-left: 0px; padding-right: 0px">
						<h4 class="font">${movie.title}</h4>
					</div>
					<div class="col-lg-5"
						style="overflow: hidden; padding-left: 0px; padding-right: 0px">
						<h4 class="font">${movie.length}mins</h4>
					</div>
					<div class="col-lg-4"
						style="overflow: hidden; padding-left: 0px; padding-right: 0px">
						<h4 class="font">${movie.genre.name}</h4>
					</div>
					<div class="col-lg-8">
						<button class="button"
							onclick='viewDetail("${movie.movieid}","${customer.firstName}")'>
							<span class="detail"> View Details </span><span
								class="glyphicon glyphicon-music"></span>
						</button>
					</div>
				</div>
			</c:forEach>
			<div class="more" id="showmore">
				<a href="javascript:void(0)" onclick="viewMore('CurrentListings')">View
					More..</a>
			</div>
		</div>


	</div>

	<jsp:directive.include file="footer.jsp" />

	<script>
		function searchmoviesbyname(moviename) {

			$.ajax({
				type : "GET",
				url : "searchmoviesbyname.htm",
				data : {
					moviename : moviename
				},
				dataType : "html",
				success : function(data) {

					$("#row3").empty();
					$("#row3").append(data);

				}

			});

		}

		function moviechange() {

			var movieid = $("#movie").val();
			$.ajax({
				type : "GET",
				url : "displaymoviebasedlocations.htm",
				data : {
					movieid : movieid
				},
				dataType : "html",
				success : function(data) {

					$("#location").html(data);

				}

			});
		}

		function locationchange() {

			var movieid = $("#movie").val();
			var locationid = $("#location").val();

			$.ajax({
				type : "GET",
				url : "displaylocationbasedtheatres.htm",
				data : {
					movieid : movieid,
					locationid : locationid
				},
				dataType : "html",
				success : function(data) {
					$("#theatre").html(data);

				}

			});
		}

		function theatrechange() {

			var movieid = $("#movie").val();
			var locationid = $("#location").val();
			var theatreid = $("#theatre").val();

			$.ajax({
				type : "GET",
				url : "displaytheatrebasedshowtimings.htm",
				data : {
					movieid : movieid,
					locationid : locationid,
					theatreid : theatreid
				},
				dataType : "html",
				success : function(data) {
					$("#date").html(data);

				}

			});
		}

		function bookTicket() {

			var movieid = $("#movie").val();
			var locationid = $("#location").val();
			var theatreid = $("#theatre").val();
			var showTimeId = $("#date").val();

			var cost = $("#cost").val();
			var seat = $("#seat").val();

			if (showTimeId == null) {
				$("#message").text("Incorrect Details.Enter All Fields");
				$("#message").css({
					"color" : "red",
					"font-weight" : "bold"
				});
				return;
			}
			;
			$
					.ajax({
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
							if (data[0] == "true") {
								location.href = "entercarddetails.htm";
							} else if (data[0] == "false"
									&& data[1].trim() == "false") {
								$("#message")
										.html(
												"Please <a href='home.htm?action=login' id = 'log'>Login</a> first");
								$("#message").css({
									"color" : "red",
									"font-weight" : "bold"
								});
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

		function viewDetail(movieid, customerName) {
			if (customerName != "") {
				location.href = "viewdetail.htm?movieid=" + movieid;
			} else {
				location.href = "home.htm?action=login";
			}

		}

		function callRest() {

			$
					.ajax({
						type : "GET",
						url : "abc.htm",
						data : {

						},
						dataType : "html",
						success : function(data) {
							
							if (data.trim() == "false") {
								
								return;
							} else {
								var json = JSON.parse(data);
								var feelslike = json.current_observation.feelslike_string;
								var temperature = json.current_observation.temperature_string;
								var weather = json.current_observation.weather;
								var icon_url = json.current_observation.icon_url;
								var forescast = json.current_observation.forecast_url;
								var time = json.current_observation.observation_time;

								
								$("#temperature").html(
										"<strong>Temperature: " + temperature
												+ "</strong>");
								$("#feelslike").html(
										" <strong>FeelsLike: " + feelslike
												+ "</strong>");
								$("#updated").html(
										"<strong>" + time + "</strong>");
								$("#weather").html(
										"<strong> Weather:- " + weather + "</strong>");
								$("#icon").attr("src", icon_url);
								$("#forecast").attr("href",forescast);
								
								$("#hidden").show();
							}

						}

					});

		}
		$(function() {
		  	callRest();
		});
	</script>


	<style>
#searchmovie {
	margin-top: 20px;
	margin-bottom: 20px;
}

.button {
	height: 32px;
	width: 120px;
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

.font {
	font-style: sourcesanspro;
}

.rowformat {
	margin-botton: 5px;
}

<!--
Footer CSS Start -->#footerrow {
	margin-top: 5px;
	padding-bottom: 100px;
	background-color: black;
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

#weather {
	margin-left: 10px;
}

#feelslike, #temperature {
	margin-left: 8px;
}

#updated {
	margin-left: 8px;
}
#forecast{
	text-decoration: none;
	margin-left:10px;
}
#forecast:hover{
	color: #ffcb05;
}
</style>
</body>
</html>