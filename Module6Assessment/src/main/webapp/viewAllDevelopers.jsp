<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Developers</title>
</head>
<body>
	<h1>Developer List</h1>
	<table border=1>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="developer" items="${developers}">
			<tr>
				<td>${developer.id}</td>
				<td>${developer.name}</td>
				<td><a href="developerServlet?action=edit&id=${developer.id}">Edit</a>
					| <a href="developerServlet?action=delete&id=${developer.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="addDeveloper.jsp">Add a new developer</a>
	<br>
	<br>
	<a href="index.jsp">Back to Home</a>
</body>
</html>
