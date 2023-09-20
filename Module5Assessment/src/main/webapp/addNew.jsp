<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Video Game</title>
</head>
<body>
	<h1>Add New Video Game</h1>
	<form action="VideoGameServlet" method="post">
		<!-- Input fields to capture details of the new game -->
		Title: <input type="text" name="title" required><br>
		Genre: <input type="text" name="genre" required><br>
		Platform: <input type="text" name="platform" required><br>
		Release Year: <input type="number" name="releaseYear" required><br>
		<input type="submit" value="Add Video Game">
	</form>
	<br>
	<a href="index.jsp">Back to Home</a>
</body>
</html>
