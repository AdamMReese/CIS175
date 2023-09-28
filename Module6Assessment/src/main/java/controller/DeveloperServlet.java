/*
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Sep 27, 2023
 */

package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Developer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

@WebServlet("/developerServlet")
public class DeveloperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Initialize EntityManager and EntityManagerFactory
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoGameDB");
	EntityManager em = emf.createEntityManager();

	// Handle GET requests
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get the 'action' parameter from the request
		String action = request.getParameter("action");

		// Handle 'edit' action
		if ("edit".equals(action)) {
			// Retrieve and validate the ID parameter from the request
			String idParam = request.getParameter("id");
			if (idParam != null && !idParam.isEmpty()) {
				int id = Integer.parseInt(idParam);
				Developer developer = em.find(Developer.class, id);
				request.setAttribute("developerToEdit", developer);
			}
			// Forward to the edit page
			try {
				request.getRequestDispatcher("editDeveloper.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Handle 'viewAll' action
		else if ("viewAll".equals(action)) {
			List<Developer> developers = em.createQuery("SELECT d FROM Developer d", Developer.class).getResultList();
			request.setAttribute("developers", developers);
			try {
				request.getRequestDispatcher("viewAllDevelopers.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Handle 'delete' action
		else if ("delete".equals(action)) {
			// Retrieve and validate the ID parameter from the request
			String idParam = request.getParameter("id");
			if (idParam != null && !idParam.isEmpty()) {
				int id = Integer.parseInt(idParam);
				Developer developer = em.find(Developer.class, id);
				request.setAttribute("developerToDelete", developer);
			}
			// Forward to the delete page
			try {
				request.getRequestDispatcher("deleteDeveloper.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Handle 'deleteConfirm' action
		else if ("deleteConfirm".equals(action)) {
			String idParam = request.getParameter("id");
			if (idParam != null && !idParam.isEmpty()) {
				int id = Integer.parseInt(idParam);
				Developer developer = em.find(Developer.class, id);
				request.setAttribute("developerToDelete", developer);
			}
			try {
				request.getRequestDispatcher("deleteDeveloper.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Default behavior: Redirect to 'viewAll'
		else {
			try {
				response.sendRedirect("developerServlet?action=viewAll");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Handle POST requests
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Check for DELETE method override
		String methodOverride = request.getParameter("_method");
		if ("DELETE".equals(methodOverride)) {
			doDelete(request, response);
			return;
		}

		// Retrieve the 'update' parameter to check if the update button was clicked
		String action = request.getParameter("update");

		// Retrieve the new name for the developer from the form
		String name = request.getParameter("name");

		// Retrieve the ID of the developer to be updated
		String idParam = request.getParameter("id");

		// Check if the 'update' button was clicked
		if (action != null && action.equals("Update")) {
			// Handle updates

			// Validate the ID parameter
			if (idParam != null && !idParam.isEmpty()) {
				// Convert the ID to an integer
				int id = Integer.parseInt(idParam);

				// Find the existing Developer object by ID
				Developer developer = em.find(Developer.class, id);

				// Check if the developer exists
				if (developer != null) {
					// Update the name of the developer
					developer.setName(name);

					// Begin the transaction
					em.getTransaction().begin();

					// Update the developer in the database
					em.merge(developer);

					// Commit the transaction
					em.getTransaction().commit();
				}
			}

			// Redirect back to the developer list
			response.sendRedirect("developerServlet");
		}
		// Check if the name is not null and not empty for adding a new developer
		else if (name != null && !name.isEmpty()) {
			// Handle adding new developers

			// Create a new Developer object with the provided name
			Developer developer = new Developer(name);

			// Begin the transaction
			em.getTransaction().begin();

			// Add the new developer to the database
			em.persist(developer);

			// Commit the transaction
			em.getTransaction().commit();

			// Redirect back to the developer list
			response.sendRedirect("developerServlet");
		}
		// Handle invalid input parameters
		else {
			// Send a 400 Bad Request error
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
		}
	}

	// Handle PUT requests for updating an existing Developer
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		int devId = Integer.parseInt(request.getParameter("devId"));
		String name = request.getParameter("name");
		Developer developer = em.find(Developer.class, devId);
		if (developer != null) {
			em.getTransaction().begin();
			developer.setName(name);
			em.getTransaction().commit();
			try {
				response.sendRedirect("developerServlet");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Handle DELETE requests for deleting a Developer
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		String devIdParam = request.getParameter("id");
		if (devIdParam != null && !devIdParam.isEmpty()) {
			int devId = Integer.parseInt(devIdParam);
			Developer developer = em.find(Developer.class, devId);
			if (developer != null) {
				em.getTransaction().begin();
				em.remove(developer);
				em.getTransaction().commit();
				try {
					response.sendRedirect("developerServlet?action=viewAll");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID parameter");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Close EntityManager and EntityManagerFactory when the servlet is destroyed
	@Override
	public void destroy() {
		em.close();
		emf.close();
		super.destroy();
	}
}
