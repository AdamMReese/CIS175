<jsp:useBean id="videoGame" scope="request" type="model.VideoGame" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Video Game</title>
</head>
<body>
	<h1>Edit Video Game</h1>
	<form action="videoGameServlet" method="post">
		<input type="hidden" name="_method" value="PUT"> <input
			type="hidden" name="id" value="${videoGame.id}"> Title: <label>
			<input type="text" name="title" value="${videoGame.title}" required>
		</label><br> Genre: <label> <input type="text" name="genre"
			value="${videoGame.genre}" required>
		</label><br> Platform: <label> <input type="text" name="platform"
			value="${videoGame.platform}" required>
		</label><br> Release Year: <label> <input type="number"
			name="releaseYear" value="${videoGame.releaseYear}" required>
		</label><br> Developer: <label> <select name="developerId">
				<option value="" disabled selected>Select a Developer</option>
				<c:forEach var="dev" items="${developers}">
					<option value="${dev.id}"
						${dev.id == videoGame.developer.id ? 'selected' : ''}>${dev.name}</option>
				</c:forEach>
		</select>
		</label><br> <input type="submit" value="Update Video Game">
	</form>
	<br>
	<a href="videoGameServlet">Back to List</a>
</body>
</html>
