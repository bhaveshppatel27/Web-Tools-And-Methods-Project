<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Add User Form</title>
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
<script src="resources/addcustomer.js"></script>
<script src="resources/main.js"></script>
<script src="resources/theatre.js"></script>
<script src='dwr/engine.js'></script>
<script src='dwr/interface/RemoteService.js'></script>
</head>
<body>
	<%-- <jsp:directive.include file="header.jsp" /> --%>
	<div class="container">

		<div class="row frm">
			<div id="login-overlay" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Enter Theatre
							Details</h4>
						<span id="theatreerror"></span>

					</div>

					<div class="modal-body">


						<form:form id="#" action="addtheatre.htm" commandName="theatre"
							method="post">
							<!---form--->

							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="name">Enter Theatre Name</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<form:input type="text" class="form-control"
												placeholder="Enter Theatre Name" required="true" id="name"
												path="name" onkeyup="checkTheatreDetail()" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-asterisk"></span>
											</span>
											<form:errors cssStyle="color:red" path="name"></form:errors>
										</div>

										<br>
									</div>
									<div class="col-xs-3"></div>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="location">Select Theatre Location</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">

											<form:select class="form-control" path="locate"
												title="Select Location" required="true" id="location"
												onchange="checkTheatreDetail()">

												<form:option value="Huntington">Huntington</form:option>
												<form:option value="Burlington">Burlington</form:option>
												<form:option value="Longwood">Longwood</form:option>
												<form:option value="Cambridge">Cambridge</form:option>
												<form:option value="Quincy">Quincy</form:option>
												<form:option value="ParkDrive">ParkDrive</form:option>
												<form:option value="ParkStreet">ParkStreet</form:option>
												<form:option value="Symphony">Symphony</form:option>

											</form:select>

											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-asterisk"></span>
											</span>
											<form:errors cssStyle="color:red" path="locate"></form:errors>
										</div>

										<br>
									</div>
									<div class="col-xs-3"></div>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="noOfAudi">Enter No. Of Auditorium</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<form:input type="number" class="form-control"
												path="noOfAudi" placeholder="No. Of Auditorium"
												required="true" min="1" max="10" id="noOfAudi" />
											<span class="input-group-addon"><span
												class="glyphicon glyphicon-asterisk"></span> </span>
											<form:errors cssStyle="color:red" path="noOfAudi"></form:errors>
										</div>

										<br>
									</div>
									<div class="col-xs-3"></div>
								</div>
							</div>

							<div class="row">
							
							<div class="form-group">
									<div class="col-xs-12">
										<h4><label for="xyz">Theatre Admin Details</label></h4>
									</div>
									
								</div>
								

							</div>

							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="username">Username</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<form:input type="text" class="form-control"
												path="theatreAdmin.username" placeholder="Username"
												required="true" id="username" onkeyup="userchange()"/>
											<span class="input-group-addon"><span
												class="glyphicon glyphicon-asterisk"></span> </span>
											<form:errors cssStyle="color:red"
												path="theatreAdmin.username"></form:errors>
										</div>
										<p id="usernameerror"></p>

										<br>
									</div>
									<div class="col-xs-3"></div>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="password">Password</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<form:input type="text" class="form-control"
												path="theatreAdmin.password" placeholder="Password"
												required="true" id="password" />
											<span class="input-group-addon"><span
												class="glyphicon glyphicon-asterisk"></span> </span>
											<form:errors cssStyle="color:red"
												path="theatreAdmin.password"></form:errors>
										</div>

										<br>
									</div>
									<div class="col-xs-3"></div>
								</div>
							</div>


							<div class="form-group">
								<div class="input-group-addon">
									<input type="submit" name="submit" id="submit" value="Submit"
										class="btn btn-success pull-right" />

								</div>
							</div>

						</form:form>
					</div>
					<!---modal-body--->
				</div>
			</div>
		</div>
	</div>
	<script>
	function checkTheatreDetail(){
		var name = $("#name").val();
		var location = $("#location").val();
		
		var result = validator(name);
		if(result.trim() == "true"){
			$("#theatreerror").text("*Invalid Character");
			$("#theatreerror").css("color" , "red");
			$("#submit").attr("disabled",true);
			return;
		}else{
			$("#theatreerror").text("");
			$("#submit").attr("disabled",false);
		}


		RemoteService.checkTheatre(name,location, {
			callback : function(data) {
							
						if(data.trim() == "false"){
							$("#submit").attr("disabled",false);
							$("#theatreerror").text("");

						}else{
							$("#theatreerror").text("*Theatre Already Exists at this location");
							$("#theatreerror").css("color" , "red");
							$("#submit").attr("disabled",true);
						}
					}
					
				});
	}
	
	function userchange(){
		var user = $("#username").val();
		
		var result = validator(user);
		if(result.trim() == "true"){
			$("#usernameerror").text("*Invalid Character");
			$("#usernameerror").css("color" , "red");
			$("#submit").attr("disabled",true);
			return;
		}else{
			$("#usernameerror").text("");
			$("#submit").attr("disabled",false);
		}

		RemoteService.checkTheatreAdminUsername(user, {
			callback : function(data) {
							
						if(data.trim() == "false"){
							$("#submit").attr("disabled",false);
							$("#usernameerror").text("");

						}else{
							$("#usernameerror").text("*Username already in use");
							$("#usernameerror").css("color" , "red");
							$("#submit").attr("disabled",true);
						}
					}
					
				});
	}
	
	function validator(data){
		var myRe = /[^A-Za-z0-9 ]+/g;
		if(myRe.test(data)){
			return "true";
		}else{
			return "false";
		}
	}
	
	

	
	</script>

</body>
</html>