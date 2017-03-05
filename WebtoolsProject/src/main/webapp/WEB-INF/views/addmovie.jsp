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
<script src="resources/movie.js"></script>
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
						<h4 class="modal-title" id="myModalLabel">Enter Movie Details</h4>
						<span id="movietitleerror"></span>
						

					</div>

					<div class="modal-body">


						<form:form id="#" action="addmovie.htm" commandName="movie"
							method="post" enctype="multipart/form-data">
							<!---form--->
							<div class="form-group">
								<!---input width--->
								<div class="col-xs-6">
									<label for="title">Movie Title</label>
									<div class="input-group">
										<form:input type="text" class="form-control" path="title" maxlength="16"
											placeholder="Enter Movie Name" required="true" id="title" onkeyup="checkMovieTitle()"/>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="title"></form:errors>
										
									</div>
									
										

									<br> 
									
									<label for="length">Length(minutes)</label>
									<div class="input-group">
										<form:input type="number" class="form-control" path="length"
											placeholder="Enter Length" required="true" min="60" max="240"
											id="length" />
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="length"></form:errors>

									</div>
									<!--------------------------------------separator--------------------------------------------------------------->
									<hr>
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-6">

									<label for="year">Release Year</label>
									<div class="input-group">
										<form:input type="number" class="form-control" path="year"
											placeholder="Enter Release Year" required="true" min="1970"
											max="2016" id="year" />
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="year"></form:errors>
									</div>

									<br> <label for="genre">Select Genre</label>
									<div class="input-group">
										<form:select class="form-control" path="gen"
											title="Select Genre" required="true" id="genre">
											<c:forEach var="genre" items="${genreList}">
												<form:option value="${genre.genreid}">${genre.name}</form:option>
											</c:forEach>
										</form:select>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="gen"></form:errors>
									</div>

									<!--------------------------------------separator--------------------------------------------------------------->
									<hr>
								</div>
							</div>

							<div class="form-group">
								<div class="col-xs-6">
									<label for="photo">Select Movie Image</label>
									<div class="input-group">
										<form:input type="file" class="form-control" path="photo"
											placeholder="Select File" required="true" id="photo" />
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="photo"></form:errors>
									</div>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>
								<div class="col-xs-6">
									<label for="category">Categorize Movie</label>
									<div class="input-group">
										<form:select class="form-control" path="category"
											title="Select Category" required="true" id="category">
											
												<form:option value="CurrentListings">CurrentListings</form:option>
												<form:option value="Coming Soon">Coming Soon</form:option>
												<form:option value="Special Releases">Special Releases</form:option>
												<form:option value="Top Movies">Top Movies</form:option>
											
										</form:select>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="category"></form:errors>
									</div>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>

							</div>

							<div class="form-group">
								<div class="col-xs-12">
									<label for="makingcost">Making Cost($)</label>
									<div class="input-group">
										<form:input type="number" class="form-control"
											path="budget.makingcost" placeholder="Enter Making Cost"
											required="true" min="10000" max="50000" id="makingcost" />
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="budget.makingcost"></form:errors>

									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-12">
									<label for="description">Enter Description</label>
									<div class="input-group">
										<form:textarea class="form-control" path="description" maxlength ="250"
											placeholder="Enter Description" rows="2" cols="30"
											required="true" id="description" />
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="description"></form:errors>
									</div>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>
							</div>

							<div class="form-group">
								<div class="col-xs-12">
									<label for="actor">Select Actors</label>
									<div class="input-group">
										<form:select class="form-control" path="act"
											title="Select Actors" required="true" id="actor" size="3">
											<c:forEach var="actor" items="${actorsList}">
												<form:option value="${actor.personid}">${actor.firstName} ${actor.lastName}</form:option>
											</c:forEach>
										</form:select>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="act"></form:errors>
									</div>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>
							</div>

							<div class="form-group">
								<div class="col-xs-12">
									<label for="actress">Select Actress</label>
									<div class="input-group">
										<form:select class="form-control" path="actr"
											title="Select Actress" required="true" id="actress" size="3">
											<c:forEach var="actress" items="${actressList}">
												<form:option value="${actress.personid}">${actress.firstName} ${actress.lastName}</form:option>
											</c:forEach>
										</form:select>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="actr"></form:errors>
									</div>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>
							</div>

							<div class="form-group">
								<div class="col-xs-12">
									<label for="director">Select Directors</label>
									<div class="input-group">
										<form:select class="form-control" path="dir"
											title="Select Director" required="true" id="director" size="3">
											<c:forEach var="director" items="${directorsList}">
												<form:option value="${director.personid}">${director.firstName} ${director.lastName}</form:option>
											</c:forEach>
										</form:select>
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-asterisk"></span></span>
										<form:errors cssStyle="color:red" path="dir"></form:errors>
									</div>
									<!----------------------------break------------------------------------------------------------->
									<br>
								</div>
							</div>


							<div class="form-group">
								<div class="input-group-addon">
									<input type="submit" name="submit" id="submit" value="Submit"
										class="btn btn-success pull-right" />

								</div>
							</div>
							<form:hidden path="budget.totalsales" value="0" />
						</form:form>
					</div>
					<!---modal-body--->
				</div>
			</div>
		</div>
	</div>
	<script>
	
	function checkMovieTitle(){
		var title = $("#title").val();
		
		var result = validator(title);
		if(result.trim() == "true"){
			$("#movietitleerror").text("*Invalid Character");
			$("#movietitleerror").css("color" , "red");
			$("#submit").attr("disabled",true);
			return;
		}else{
			$("#movietitleerror").text("");
			$("#submit").attr("disabled",false);
		}
		
		
		RemoteService.checkMovieTitle(title, {
			callback : function(data) {
							
						if(data.trim() == "false"){
							$("#submit").attr("disabled",false);
							$("#movietitleerror").text("");

						}else{
							$("#movietitleerror").text("*Movie already in Database");
							$("#movietitleerror").css("color" , "red");
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