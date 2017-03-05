<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Refresh" content="180;url=home.htm?action=home">
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

<script type="text/javascript" src="resources/actor.js"></script>
<link rel="stylesheet" type="text/css" href="home.css">
<script src="resources/main.js"></script>
<script>

function submitcard(){
		$("#submitcarddetails").attr("disabled",true);
		var cardnumber = $("#cardnumber").val();
		var pin = $("#pin").val();
		if(cardnumber == "" || pin == ""){
			$("#carderror").text("Enter Valid Card Details");
			$("#carderror").css({
				"color" : "red",
				"font-weight" : "bold"
			});
			$("#submitcarddetails").attr("disabled",false);
			return;
		}
        	var xhr = $.ajax({
					type:"POST",
					url:"checkcarddetails.htm",
					data:{cardnumber:cardnumber,pin:pin},
					success:function(data){
						if(data.trim() == "false"){
							$("#carderror").text("Invalid Card Details");
							$("#carderror").css({
								"color" : "red",
								"font-weight" : "bold"
							});
							$("#submitcarddetails").attr("disabled",false);
						}
						else{
							alert("Thank you for booking!! \n Your Tickets have been send to you");
							location.href = "viewticket.htm";
							
						}
						
					}
					
				});
	}
	

</script>
</head>
<body>
<jsp:directive.include file="header.jsp" />
	<div class="container">
		<div class="row">
			<div id="login-overlay" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Payment Details</h4>
					</div>

					<div class="modal-body">
						<form>
							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="cardnumber">Enter Card Number</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<input type="number" class="form-control"
												placeholder="Card Number" required id="cardnumber"
												name="cardnumber" /> <span class="input-group-addon">
												<span class="glyphicon glyphicon-asterisk"></span>
											</span>
										</div>

										<br>
									</div>
									<div class="col-xs-3"></div>
								</div>
							</div>


							<div class="row">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="pin">Enter PIN</label>
									</div>
									<div class="col-xs-5">

										<div class="input-group">
											<input type="number" class="form-control"
												placeholder="PIN Number" required id="pin"
												name="pin" /> <span class="input-group-addon"> <span
												class="glyphicon glyphicon-asterisk"></span></span>
										</div>

										<br>
									</div>
									<div class="col-xs-3"></div>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-6">
									<p id="carderror"></p>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<div class="input-group-addon">
										<div class="col-xs-4">

											<button type="button" onclick="submitcard()" id="submitcarddetails" class="btn btn-success">Submit</button>
											
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

</body>
</html>