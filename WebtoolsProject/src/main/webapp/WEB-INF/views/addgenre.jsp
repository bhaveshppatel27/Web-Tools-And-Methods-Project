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
<script type="text/javascript" src="resources/genre.js"></script>
<script src='dwr/engine.js'></script>
<script src='dwr/interface/RemoteService.js'></script>
<link rel="stylesheet" type="text/css" href="home.css">
<body>
	<div class="container">
		<div class="row">
			<div id="login-overlay" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Enter Genre to
							Database</h4>
					</div>

					<div class="modal-body">


						<form>

							<div class="row" id="genrerow">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="genrename">Enter Genre Name</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<input type="text" class="form-control"
												placeholder="Enter Genre Name" required id="genrename"
												name="genrename" onkeyup="checkGenre(this.value)"/> <span class="input-group-addon">
												<span class="glyphicon glyphicon-asterisk"></span>
											</span>
										</div>

										<br>
									</div>
									<p id ="genreerror"></p>
									<div class="col-xs-3">
										<p id="genreerror"></p>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<div class="input-group-addon">
										<div class="col-xs-4">

											<button type="button" onclick="addgenre()" id="add"
												class="btn btn-success">Add</button>
											<button type="button" onclick="addgen()" id="addmore"
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

function addgenre(){
	var genre = $("#genrename").val();
	if(genre.trim()==""){
		$("#genreerror").text("*Invalid Genre");
		$("#genreerror").css("color" , "red");
		return;
	}
	RemoteService.checkGenre(genre, {
		callback : function(data) {
			
			if(data == "false"){
				$("#addmore").attr("disabled",false);
				$("#add").attr("disabled",true);
				$("#genreerror").text("*Genre added successfully");
				$("#genreerror").css({"color":"green","font-weight":"bold"});
			}else{
				$("#genreerror").text("*Genre already Exists");
				$("#genreerror").css({"color":"red","font-weight":"bold"});
			}
			
		}
	});
					
					
	
}

function checkGenre(that){
	var result = validator(that);
	if(result.trim() == "true"){
		$("#genreerror").text("*Invalid Character");
		$("#genreerror").css("color" , "red");
		$("#add").attr("disabled",true);
	}else{
		$("#genreerror").text("");
		$("#add").attr("disabled",false);
	}
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