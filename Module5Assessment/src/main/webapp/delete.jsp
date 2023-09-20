<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Video Game</title>
</head>
<body>
	<h1>Are you sure you want to delete this video game?</h1>
	<form action="VideoGameServlet" method="post">
		<!-- Hidden input to specify the DELETE method -->
		<input type="hidden" name="_method" value="DELETE">
		<!-- Hidden input to pass the ID of the game to be deleted -->
		<input type="hidden" name="id" value="${param.id}"> <input
			type="submit" value="Delete">
	</form>
	<br>
	<a href="VideoGameServlet">Cancel</a>
</body>
</html>
