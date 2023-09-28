<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Developer</title>
</head>
<body>
	<h1>Are you sure you want to delete this developer?</h1>
	<form action="developerServlet" method="post">
		<!-- Hidden input to specify the DELETE method -->
		<input type="hidden" name="_method" value="DELETE">
		<!-- Hidden input to pass the ID of the developer to be deleted -->
		<input type="hidden" name="id" value="${param.id}"> <input
			type="submit" value="Delete">
	</form>
	<br>
	<a href="developerServlet">Cancel</a>
</body>
</html>
