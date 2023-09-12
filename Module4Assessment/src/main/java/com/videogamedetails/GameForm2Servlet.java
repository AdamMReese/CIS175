package com.videogamedetails;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet to handle the submission of the second form
@WebServlet("/GameForm2Servlet")
public class GameForm2Servlet extends HttpServlet {

	// doPost method to handle POST requests from the second form
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve form parameters
		String title = request.getParameter("title");
		String platform = request.getParameter("platform");

		// Create an instance of VideoGameDetails and process the form data
		VideoGameDetails gameDetails = new VideoGameDetails();
		String result = gameDetails.processGame2Details(title, platform);

		// Set the processed data as a request attribute and forward to the result page
		request.setAttribute("result", result);
		request.getRequestDispatcher("result2.jsp").forward(request, response);
	}
}
