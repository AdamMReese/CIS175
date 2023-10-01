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
		Name: <label> <input type="text" name="name" required>
		</label><br> <input type="hidden" name="action" value="add"><br>
		<input type="submit" value="Add Developer">
	</form>
	<br>
	<a href="index.jsp">Back to Home</a>
</body>
</html>
