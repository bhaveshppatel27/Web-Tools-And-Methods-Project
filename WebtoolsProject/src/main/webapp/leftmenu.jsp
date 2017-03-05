<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Post Comments</title>
<link rel="stylesheet" type="text/css" href="leftmenu.css">
</head>
<div>
	<c:choose>
		<c:when test="${!empty sessionScope.customer}">
			<a href="adminhome.htm?action=genre" class="book" target="contents">Add
				Genre</a>
			<a href="adminhome.htm?action=actor" class="book" target="contents">Add
				Actor</a>
			<a href="adminhome.htm?action=actress" class="book" target="contents">Add
				Actress</a>
			<a href="adminhome.htm?action=director" class="book"
				target="contents">Add Director</a>
			<a href="addmovie.htm" class="book" target="contents">Add Movie</a>
			<a href="addtheatre.htm" class="book" target="contents">Add
				Theatre</a>
			<a href="addshowtimings.htm" class="book" target="contents">Add
				Movie ShowTimings</a>
			<a href="home.htm?action=home" class="book" target="_parent">Back
				to Home Page</a>
			<a href="home.htm?action=logout" class="book" target="_parent">Logout</a>
		</c:when>

		<c:otherwise>
			<a href="addshowtimings.htm" class="book" target="contents">Add
				Movie ShowTimings</a>
			<a href="home.htm?action=logout" class="book" target="_parent">Logout</a>
		</c:otherwise>
	</c:choose>

</div>
</html>