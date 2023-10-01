
/*
* @author Adam Reese - amreese3
* CIS175 - Fall 2023
* Sep 29, 2023
*/

package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Developer;
import model.VideoGame;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

@WebServlet("/videoGameServlet")
public class VideoGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Creating EntityManagerFactory and EntityManager to interact with the
	// persistence unit
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoGameDB");
	EntityManager em = emf.createEntityManager();

	// Handling GET requests to view all entities in the database or to edit a
	// specific entity
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");

		if (action != null && action.equals("edit")) {
			// Retrieve the ID parameter from the request
			String idParam = request.getParameter("id");
			if (idParam != null && !idParam.isEmpty()) {
				// Parse the ID parameter to an integer
				int id = Integer.parseInt(idParam);

				// Find the VideoGame object by ID in the database
				VideoGame videoGame = em.find(VideoGame.class, id);

				// Set the VideoGame object as a request attribute
				request.setAttribute("videoGame", videoGame);
			}

			// Fetch all developers from the database
			List<Developer> developers = em.createQuery("SELECT d FROM Developer d", Developer.class).getResultList();

			// Set the developers list as a request attribute
			request.setAttribute("developers", developers);

			// Forward the request to the editGames.jsp page
			try {
				request.getRequestDispatcher("editGames.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action != null && action.equals("viewAllGames")) {
			// Fetching all video game records from the database
			List<VideoGame> videoGames = em.createQuery("SELECT v FROM VideoGame v ORDER BY v.title", VideoGame.class).getResultList();

			// Setting the list of video games as a request attribute to be accessed in the
			// JSP
			request.setAttribute("videoGames", videoGames);

			// Forwarding the request to the viewAllGames.jsp page to display the list of
			// video games
			try {
				request.getRequestDispatcher("viewAllGames.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action != null && action.equals("addNewGames")) {
			// Fetch all developers from the database
			List<Developer> developers = em.createQuery("SELECT d FROM Developer d", Developer.class).getResultList();
			System.out.println("Number of developers: " + developers.size()); // Debug print

			// Set the developers list as a request attribute
			request.setAttribute("developers", developers);

			// Forward to the addNewGames.jsp page
			try {
				request.getRequestDispatcher("addNewGames.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// Redirect to the "view all games" page
			try {
				response.sendRedirect("videoGameServlet?action=viewAllGames");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Handling POST requests to add a new entity to the database
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Retrieve the _method parameter, which is used to override the HTTP method
		String methodOverride = request.getParameter("_method");

		// If the _method parameter is present, check its value and forward the request
		// to the appropriate method
		if (methodOverride != null) {
			// If _method parameter value is PUT, forward the request to the doPut method
			if (methodOverride.equalsIgnoreCase("PUT")) {
				doPut(request, response);
				return;
			}
			// If _method parameter value is DELETE, forward the request to the doDelete
			// method
			else if (methodOverride.equalsIgnoreCase("DELETE")) {
				doDelete(request, response);
				return;
			}
		}

		// Retrieving parameters from the request to create a new VideoGame object
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String platform = request.getParameter("platform");
		String releaseYearParam = request.getParameter("releaseYear");

		// Retrieve the developer ID from the request
		String developerIdParam = request.getParameter("developerId");

		// Debugging: Print the values of the parameters
		System.out.println("Title: " + title);
		System.out.println("Genre: " + genre);
		System.out.println("Platform: " + platform);
		System.out.println("Release Year: " + releaseYearParam);
		System.out.println("Received Developer ID: " + developerIdParam);

		if (title != null && genre != null && platform != null && releaseYearParam != null
				&& !releaseYearParam.isEmpty()) {
			int releaseYear = Integer.parseInt(releaseYearParam);

			VideoGame videoGame = new VideoGame(title, genre, platform, releaseYear);

			// Set the Developer object for the VideoGame
			if (developerIdParam != null && !developerIdParam.isEmpty()) {
				int developerId = Integer.parseInt(developerIdParam);

				// Find the Developer object by ID
				Developer developer = em.find(Developer.class, developerId);
				System.out.println("Found Developer: " + developer); // Debug print

				// Associate the Developer with the VideoGame
				videoGame.setDeveloper(developer);
				System.out.println("Video Game after setting Developer: " + videoGame); // Debug print
			}

			// Beginning a transaction to persist the new VideoGame object to the database
			em.getTransaction().begin();
			em.persist(videoGame);
			em.getTransaction().commit();

			// Redirecting to the videoGameServlet to refresh the list of video games
			response.sendRedirect("videoGameServlet?action=viewAllGames");
		} else {
			// Handle the case where one or more parameters are null or empty, perhaps by
			// sending an error response
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
		}
	}

	// Handling PUT requests to edit an existing entity in the database
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		// Retrieving parameters from the request to update an existing VideoGame object
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String platform = request.getParameter("platform");
		int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
		String developerIdParam = request.getParameter("developerId"); // New line to get developer ID

		// Finding the VideoGame object by ID and updating its fields
		VideoGame videoGame = em.find(VideoGame.class, id);
		if (videoGame != null) {
			em.getTransaction().begin();
			videoGame.setTitle(title);
			videoGame.setGenre(genre);
			videoGame.setPlatform(platform);
			videoGame.setReleaseYear(releaseYear);

			// New block to update the Developer
			if (developerIdParam != null && !developerIdParam.isEmpty()) {
				int developerId = Integer.parseInt(developerIdParam);
				Developer developer = em.find(Developer.class, developerId);
				videoGame.setDeveloper(developer);
			}

			em.getTransaction().commit();

			// Redirecting to the videoGameServlet to refresh the list of video games
			try {
				response.sendRedirect("videoGameServlet");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Handling DELETE requests to remove an entity from the database
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		// Retrieve the ID parameter from the request, which indicates the ID of the
		// video game to be deleted
		String idParam = request.getParameter("id");

		// Check if the ID parameter is not null and not empty
		if (idParam != null && !idParam.isEmpty()) {
			// Parse the ID parameter to an integer
			int id = Integer.parseInt(idParam);

			// Find the VideoGame object by ID in the database
			VideoGame videoGame = em.find(VideoGame.class, id);

			// If the VideoGame object is found, begin a transaction to remove it from the
			// database
			if (videoGame != null) {
				em.getTransaction().begin();
				em.remove(videoGame);
				em.getTransaction().commit();

				// Redirect to the videoGameServlet to refresh the list of video games
				try {
					response.sendRedirect("videoGameServlet");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			// If the ID parameter is null or empty, send an error response indicating bad
			// request
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID parameter");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Closing the EntityManager and EntityManagerFactory when the servlet is
	// destroyed
	@Override
	public void destroy() {
		em.close();
		emf.close();
		super.destroy();
	}
}
