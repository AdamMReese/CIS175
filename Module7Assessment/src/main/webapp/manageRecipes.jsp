<!DOCTYPE html>
<html>
<head>
<title>Manage Recipes</title>
</head>
<body>
	<h1>Manage Recipes</h1>
	<form action="RecipeServlet" method="post">
		<!-- Simplified fields -->
		Recipe Name: <input type="text" name="name"><br>
		Ingredients: <input type="text" name="ingredients"><br>
		Category: <input type="text" name="category"><br> <input
			type="submit" value="Add Recipe">
	</form>
	<!-- Add buttons for edit and delete -->
	<a href="index.jsp">Back to Home</a>
</body>
</html>
