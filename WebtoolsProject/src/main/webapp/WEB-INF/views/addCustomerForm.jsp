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
<script src='dwr/engine.js'></script>
<script src='dwr/interface/RemoteService.js'></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="container">

		<div class="row frm">
			<div id="login-overlay" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Register with
							cinemadocket.com</h4>
						<p>
							Please Register to watch exclusive movie at <b>Cinema Docket</b>.
							You will instantly receive hundreds of amazing shopping deals, or
							go back to our <a href="home.htm?action=home">main site</a>.
						</p>
					</div>

					<div class="modal-body">


						<form:form id="registerForm" action="addcustomer.htm"
							commandName="customer" method="post">
							<!---form--->
							<div class="form-group">
								<!---input width--->
								<div class="col-xs-6">
									<label for="firstName">First Name</label>
									<div class="input-group">
										<form:input type="text" class="form-control" path="firstName"
											placeholder="Enter First Name" required="true" id="firstName" onkeyup="checkFirstName(this.value)"/>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
											<form:errors cssStyle="color:red" path="firstName"></form:errors>
											
									</div>
									<p id="firstNameerror"></p>

									<br> <label for="user">Username</label>
									<div class="input-group">
										<form:input type="text" class="form-control" path="username"
											placeholder="Enter Username" required="true" 
											onkeyup="userchange()" id="user" />
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
											<form:errors cssStyle="color:red" path="username"></form:errors>

									</div>
									<p id="usernameerror"></p>
									<!--------------------------------------separator--------------------------------------------------------------->
									<hr>
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-6">

									<label for="lastName">Last Name</label>
									<div class="input-group">
										<form:input type="text" class="form-control" path="lastName"
											placeholder="Enter Last Name" required="true" id="lastName" onkeyup = "checkLastName(this.value)"/>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
											<form:errors cssStyle="color:red" path="lastName"></form:errors>
									</div>
									<p id="lastNameerror"></p>

									<br> <label for="password">Enter Password</label>
									<div class="input-group">
										<form:input type="password" class="form-control"
											path="password" placeholder="Enter Password" required="true" />
										<span class="input-group-addon" id="password"><span
											class="glyphicon glyphicon-asterisk"></span></span>
											<form:errors cssStyle="color:red" path="password"></form:errors>
									</div>

									<!--------------------------------------separator--------------------------------------------------------------->
									<hr>
								</div>
							</div>

							<div class="form-group">
								<div class="col-xs-12">
									<label for="email">Enter Email</label>
									<div class="input-group">
										<form:input type="email" class="form-control" path="email"
											placeholder="Enter Email" required="true"
											onkeyup="emailchange()" id="email" />
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
											<form:errors cssStyle="color:red" path="email"></form:errors>
									</div>
									<p id="emailerror"></p>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>
							</div>

							<div class="form-group">
								<div class="col-xs-12">
									<label for="address">Address</label>
									<div class="input-group">
										<form:input type="text" class="form-control" path="address"
											placeholder="Enter Street Name and Number" required="true" id="address" onkeyup = "checkAddress(this.value)"/>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
											<form:errors cssStyle="color:red" path="address"></form:errors>
									</div>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>
							</div>
							<p id="addresserror"></p>

							<div class="form-group">
								<div class="col-xs-12">
									<label for="InputCity">City</label>
									<div class="input-group">
										<form:input type="text" class="form-control" path="city" onkeyup = "checkCity(this.value)"
											placeholder="Enter City" required="true" />
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
											<form:errors cssStyle="color:red" path="city"></form:errors>
									</div>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>
							</div>
							<p id="cityerror"></p>

							<div class="form-group">
								<div class="col-xs-12">
									<label for="province">Province</label>
									<div class="input-group">
										<form:input type="text" class="form-control" path="province"
											placeholder="Enter Province" required="true" id="province" onkeyup = "checkProvince(this.value)"/>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
											<form:errors cssStyle="color:red" path="province"></form:errors>
									</div>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>
							</div>
							<p id="provinceerror"></p>

							<div class="form-group">
								<div class="input-group-addon">
									<input type="submit" name="submit" id="submit" value="Submit"
										class="btn btn-success pull-right" />
									<form:hidden path="credit" value="50" />
									<form:hidden path="role" value="user" />
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

	function userchange(){
		var user = $("#user").val();
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

		RemoteService.checkUsername(user, {
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
	
	function emailchange(){
		var email = $("#email").val();

		var result = emailvalidator(email);
		if(result.trim() == "true"){
			$("#emailerror").text("*Invalid Character");
			$("#emailerror").css("color" , "red");
			$("#submit").attr("disabled",true);
			return;
		}else{
			$("#emailerror").text("");
			$("#submit").attr("disabled",false);
		}
		
		RemoteService.checkEmail(email, {
			callback : function(data) {
							
						if(data.trim() == "false"){
							$("#submit").attr("disabled",false);
							$("#emailerror").text("");

						}else{
							$("#emailerror").text("*Email already in use");
							$("#emailerror").css("color" , "red");
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
	function emailvalidator(data){
		var myRe = /[^A-Za-z0-9@. ]+/g;
		if(myRe.test(data)){
			return "true";
		}else{
			return "false";
		}
	}

	
	function checkFirstName(that){
		var result = validator(that);
		if(result.trim() == "true"){
			$("#firstNameerror").text("*Invalid Character");
			$("#firstNameerror").css("color" , "red");
			$("#submit").attr("disabled",true);
		}else{
			$("#firstNameerror").text("");
			$("#submit").attr("disabled",false);
		}
	}
	
	function checkLastName(that){
		var result = validator(that);
		if(result.trim() == "true"){
			$("#lastNameerror").text("*Invalid Character");
			$("#lastNameerror").css("color" , "red");
			$("#submit").attr("disabled",true);
		}else{
			$("#lastNameerror").text("");
			$("#submit").attr("disabled",false);
		}
	}
	
	function checkAddress(that){
		var result = validator(that);
		if(result.trim() == "true"){
			$("#addresserror").text("*Invalid Character");
			$("#addresserror").css("color" , "red");
			$("#submit").attr("disabled",true);
		}else{
			$("#addresserror").text("");
			$("#submit").attr("disabled",false);
		}
	}
	
	function checkCity(that){
		var result = validator(that);
		if(result.trim() == "true"){
			$("#cityerror").text("*Invalid Character");
			$("#cityerror").css("color" , "red");
			$("#submit").attr("disabled",true);
		}else{
			$("#cityerror").text("");
			$("#submit").attr("disabled",false);
		}
	}
	
	function checkProvince(that){
		var result = validator(that);
		if(result.trim() == "true"){
			$("#provinceerror").text("*Invalid Character");
			$("#provinceerror").css("color" , "red");
			$("#submit").attr("disabled",true);
		}else{
			$("#provinceerror").text("");
			$("#submit").attr("disabled",false);
		}
	}
	
	
	
	
	
	
		
</script>

</body>
</html>