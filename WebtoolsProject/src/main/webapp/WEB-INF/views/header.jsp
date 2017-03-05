
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header" id = "header">
      <a class="navbar-brand head" href="home.htm?action=home" target="_parent">Cinema Docket</a>
    </div>
    <ul class="nav navbar-nav" id = "navbar">
      <li class="active"><a href="home.htm?action=home" target="_parent">Home</a></li>
      <li><a href="#">Magazines</a></li>
      <li><a href="#">Deals</a></li>
      <li><a href="#">Gift Cards</a></li>
      <li><a href="viewticket.htm">My Tickets</a></li>
      <li ><a href="#" id = "welcomename" style="color:#ffcb05;font-weight:bold">${customer.firstName} ${customer.lastName }</a></li>
      
    </ul>
    <c:choose>
	      <c:when test = "${!empty sessionScope.customer}">
      		<button type="button" class="btn btn-primary navbar-btn" class="headerbutton" onclick="logout()">Logout</button>
      		<c:if test="${sessionScope.customer.role eq 'admin'}">
      			<button type="button" class="btn btn-primary navbar-btn" class="headerbutton" onclick="adminpanel()">Admin Panel</button>
      		</c:if>
	      </c:when>
	      <c:otherwise>
	      <!--
	      	<li><a href="home.htm?action=login" id = "log">Login</a></li>
      		<li><a href="addcustomer.htm">Register</a></li>
      	-->
      		<button type="button" class="btn btn-primary navbar-btn" class="headerbutton" onclick="login()">Login</button>
      		<button type="button" class="btn btn-primary navbar-btn" class="headerbutton" onclick="theatreAdminlogin()">Theatre Admin Login</button>
      		<button type="button" class="btn btn-primary navbar-btn" class="headerbutton" onclick="register()">Register</button>
	      </c:otherwise>
      </c:choose>
  </div>
  <script>
  
  function theatreAdminlogin(){
		
		location.href = "home.htm?action=theatreAdminLogin";
		
	}
  
  </script>
</nav>