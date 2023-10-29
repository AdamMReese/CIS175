package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;

@WebServlet("/categoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManager em;

	@Override
	public void init() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecipeBox");
		em = emf.createEntityManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String id = request.getParameter("id");

		try {
			if ("view".equals(action)) {
				Category category = em.find(Category.class, Integer.parseInt(id));
				request.setAttribute("category", category);
				getServletContext().getRequestDispatcher("/viewCategory.jsp").forward(request, response);
			} else if ("delete".equals(action)) {
				Category categoryToDelete = em.find(Category.class, Integer.parseInt(id));
				em.getTransaction().begin();
				em.remove(categoryToDelete);
				em.getTransaction().commit();
				getServletContext().getRequestDispatcher("/manageCategories.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter("name");

		Category category = new Category(categoryName);

		em.getTransaction().begin();
		if (categoryId == null || categoryId.isEmpty()) {
			em.persist(category);
		} else {
			category.setId(Integer.parseInt(categoryId));
			em.merge(category);
		}
		em.getTransaction().commit();

		getServletContext().getRequestDispatcher("/manageCategories.jsp").forward(request, response);
	}
}