<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Video Game</title>
</head>
<body>
	<h1>Add New Video Game</h1>
	<form action="videoGameServlet" method="post">
		<!-- Input fields to capture details of the new game -->
		Title: <label> <input type="text" name="title" required>
		</label><br> Genre: <label> <input type="text" name="genre"
			required>
		</label><br> Platform: <label> <input type="text" name="platform"
			required>
		</label><br> Release Year: <label> <input type="number"
			name="releaseYear" required>
		</label><br> Developer: <label> <select name="developerId">
				<option value="" disabled selected>Select a Developer</option>
				<c:forEach var="dev" items="${developers}">
					<option value="${dev.id}">${dev.name}</option>
				</c:forEach>
		</select>
		</label><br> <input type="submit" value="Add Video Game">
	</form>
	<br>
	<a href="index.jsp">Back to Home</a>
</body>
</html>
