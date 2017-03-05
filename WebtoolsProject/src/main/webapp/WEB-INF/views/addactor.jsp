<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
</head>
<script type="text/javascript" src="resources/actor.js"></script>
<link rel="stylesheet" type="text/css" href="home.css">
<script src='dwr/engine.js'></script>
<script src='dwr/interface/RemoteService.js'></script>
<body>
	<div class="container">
		<div class="row">
			<div id="login-overlay" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Enter Actor to
							Database</h4>
					</div>

					<div class="modal-body">
						<form>
							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="firstname">Enter First Name</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<input type="text" class="form-control"
												placeholder="Enter First Name" required id="firstname"
												name="firstname" onkeyup="checkFirstName(this.value)"/> <span class="input-group-addon">
												<span class="glyphicon glyphicon-asterisk"></span>
											</span>
										</div>

										<br>
									</div>
									<p id ="firstNameerror"></p>
									<div class="col-xs-3"></div>
								</div>
							</div>


							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="lastname">Enter Last Name</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<input type="text" class="form-control"
												placeholder="Enter Last Name" required id="lastname"
												name="lastname" onkeyup="checkLastName(this.value)"/> <span class="input-group-addon"> <span
												class="glyphicon glyphicon-asterisk"></span></span>
										</div>

										<br>
									</div>
									<p id ="lastNameerror"></p>
									<div class="col-xs-3"></div>
								</div>
							</div>


							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="skills">Enter skills</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<input type="text" class="form-control"
												placeholder="Enter Skills" required id="skills"
												name="skills" onkeyup="checkSkills(this.value)"/> <span class="input-group-addon"> <span
												class="glyphicon glyphicon-asterisk"></span></span>
										</div>

										<br>
									</div>
									<p id ="skillserror"></p>
									<div class="col-xs-3"></div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6">
									<p id="actorerror"></p>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<div class="input-group-addon">
										<div class="col-xs-4">

											<button type="button" onclick="addactor()" id="add"
												class="btn btn-success">Add</button>
											<button type="button" onclick="addact()" id="addmore"
												disabled=true class="btn btn-success">Addmore</button>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
					<!---modal-body--->
				</div>
			</div>
		</div>
	</div>
<script>

function addactor(){
	var firstname = $("#firstname").val();
	var lastname = $("#lastname").val();
	var skills = $("#skills").val();
	if(firstname.trim()=="" || lastname.trim()=="" || skills.trim()==""){
		$("#actorerror").text("*Invalid Details");
		$("#actorerror").css("color" , "red");
		return;
	}
	RemoteService.checkActor(firstname,lastname,skills, {
		callback : function(data) {
					
					if(data == "false"){
						$("#addmore").attr("disabled",false);
						$("#add").attr("disabled",true);
						$("#actorerror").text("*Actor added successfully");
						$("#actorerror").css({"color":"green","font-weight":"bold"});
					}else{
						$("#actorerror").text("*Actor already Exists");
						$("#actorerror").css({"color":"red","font-weight":"bold"});
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

function checkFirstName(that){
	var result = validator(that);
	if(result.trim() == "true"){
		$("#firstNameerror").text("*Invalid Character");
		$("#firstNameerror").css("color" , "red");
		$("#add").attr("disabled",true);
	}else{
		$("#firstNameerror").text("");
		$("#add").attr("disabled",false);
	}
}

function checkLastName(that){
	var result = validator(that);
	if(result.trim() == "true"){
		$("#lastNameerror").text("*Invalid Character");
		$("#lastNameerror").css("color" , "red");
		$("#add").attr("disabled",true);
	}else{
		$("#lastNameerror").text("");
		$("#add").attr("disabled",false);
	}
}

function checkSkills(that){
	var result = validator(that);
	if(result.trim() == "true"){
		$("#skillserror").text("*Invalid Character");
		$("#skillserror").css("color" , "red");
		$("#add").attr("disabled",true);
	}else{
		$("#skillserror").text("");
		$("#add").attr("disabled",false);
	}
}


</script>
</body>
</html>