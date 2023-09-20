<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Video Games</title>
</head>
<body>
	<h1>Video Game List</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Genre</th>
			<th>Platform</th>
			<th>Release Year</th>
			<th>Actions</th>
		</tr>
		<!-- Loop through the list of video games and display each one in a table row -->
		<c:forEach var="videoGame" items="${videoGames}">
			<tr>
				<td>${videoGame.id}</td>
				<td>${videoGame.title}</td>
				<td>${videoGame.genre}</td>
				<td>${videoGame.platform}</td>
				<td>${videoGame.releaseYear}</td>
				<!-- Links to edit or delete each game -->
				<td><a href="VideoGameServlet?action=edit&id=${videoGame.id}">Edit</a>
					| <a href="delete.jsp?id=${videoGame.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="index.jsp">Back to Home</a>
</body>
</html>
