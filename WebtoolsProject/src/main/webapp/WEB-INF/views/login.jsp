
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="home.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<script src="resources/main.js"></script>
<script>
		function sub(){
			var user = $("#user").val();
			var pass = $("#pass").val();
			var remember = "false";
			if($("#remember").prop('checked') == true){
				remember = "true";
			}
	        	$.ajax({
						type:"POST",
						url:"logincheck.htm",
						data:{username:user,password:pass,remember:remember},
						success:function(data){
							var data = data.split(",");
							
							if(data[0].trim() == "true"){
								if(data[2].trim() == "admin"){
									
									location.href = "home.htm?action=home";
								}else if(data[2].trim() == "user"){
									location.href = "home.htm?action=home";
								}
								

							}else{
								$("#login").text("*Incorrect Username or Password");
								$("#login").css("color" , "red");
								
							}
						}
						
					});
		}
		</script>

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="container">
		<div class="row frm">
			<div id="login-overlay" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Login to
							cinemadocket.com</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-6">
								<div class="well">
									<form id="loginForm" novalidate="novalidate">
										<div class="form-group">
											<label for="username" class="control-label" required>Username</label>
											<input type="text" class="form-control" id="user"
												name="username" value="${cookie.uname.value}" required
												title="Please enter you username" placeholder="username">
											<span class="help-block"></span>
										</div>
										<div class="form-group">
											<label for="password" class="control-label" required>Password</label>
											<input type="password" class="form-control" id="pass"
												name="password" value="${cookie.upass.value}" required
												title="Please enter your password"> <span
												class="help-block"></span>
										</div>
										<div id="loginErrorMsg" class="alert alert-error hide">Wrong
											username or password</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="remember"
												id="remember"> Remember login
											</label>
											<p class="help-block">(if this is a private computer)</p>
										</div>
										<p id="login"></p>

										<button type="button" class="btn btn-success btn-block"
											id="submitlogin" onclick="sub()">Login</button>
										<a href="#" class="btn btn-default btn-block">Help to
											login</a>
									</form>
								</div>
							</div>
							<div class="col-xs-6">
								<p class="lead">
									Register now for <span class="text-success">FREE</span>
								</p>
								<ul class="list-unstyled" style="line-height: 2">
									<li><span class="fa fa-check text-success"></span> See all
										your orders</li>
									<li><span class="fa fa-check text-success"></span> Fast
										ticket booking</li>
									<li><span class="fa fa-check text-success"></span> Save
										your favorites</li>
									<li><span class="fa fa-check text-success"></span> Fast
										checkout</li>
									<li><span class="fa fa-check text-success"></span> Get 50
										wallet credit gift <small>(only new customers)</small></li>
									<li><a href="#"><u>Read more</u></a></li>
								</ul>
								<p>
									<a href="addcustomer.htm" class="btn btn-info btn-block">Yes
										please, register now!</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>

