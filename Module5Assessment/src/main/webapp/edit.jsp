<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Video Game</title>
</head>
<body>
	<h1>Edit Video Game</h1>
	<form action="VideoGameServlet" method="post">
		<input type="hidden" name="_method" value="PUT"> <input
			type="hidden" name="id" value="${videoGame.id}"> Title: <input
			type="text" name="title" value="${videoGame.title}" required><br>
		Genre: <input type="text" name="genre" value="${videoGame.genre}"
			required><br> Platform: <input type="text"
			name="platform" value="${videoGame.platform}" required><br>
		Release Year: <input type="number" name="releaseYear"
			value="${videoGame.releaseYear}" required><br> <input
			type="submit" value="Update Video Game">
	</form>
	<br>
	<a href="VideoGameServlet">Back to List</a>
</body>
</html>
