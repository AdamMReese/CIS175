<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Developer</title>
</head>
<body>
	<h1>Add New Developer</h1>
	<form action="developerServlet" method="post">
		<!-- Input fields to capture details of the new developer -->
		Name: <input type="text" name="name" required><br> <input
			type="hidden" name="action" value="add"><br> <input type="submit"
			value="Add Developer">
	</form>
	<br>
	<a href="addDeveloper.jsp">Add a new developer</a>
	<br>
	<a href="index.jsp">Back to Home</a>
</body>
</html>
