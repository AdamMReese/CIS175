<!DOCTYPE html>
<html>
<head>
    <title>Edit Developer</title>
</head>
<body>
    <!-- Form to edit the developer -->
    <form action="developerServlet" method="post">
        <!-- Text input pre-filled with the developer's current name -->
        <input type="text" name="newDeveloperName" value="${developerToEdit.name}">
        
        <!-- Hidden input to store the developer's ID -->
        <input type="hidden" name="id" value="${developerToEdit.id}">
        
        <!-- Submit button to update the developer -->
        <input type="submit" name="update" value="Update">
    </form>
</body>
</html>
