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

import model.Ingredient;

@WebServlet("/ingredientServlet")
public class IngredientServlet extends HttpServlet {
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
				Ingredient ingredient = em.find(Ingredient.class, Integer.parseInt(id));
				request.setAttribute("ingredient", ingredient);
				getServletContext().getRequestDispatcher("/viewIngredient.jsp").forward(request, response);
			} else if ("delete".equals(action)) {
				Ingredient ingredientToDelete = em.find(Ingredient.class, Integer.parseInt(id));
				em.getTransaction().begin();
				em.remove(ingredientToDelete);
				em.getTransaction().commit();
				getServletContext().getRequestDispatcher("/manageIngredients.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ingredientId = request.getParameter("ingredientId");
		String ingredientName = request.getParameter("name");

		Ingredient ingredient = new Ingredient(ingredientName);

		em.getTransaction().begin();
		if (ingredientId == null || ingredientId.isEmpty()) {
			em.persist(ingredient);
		} else {
			ingredient.setId(Integer.parseInt(ingredientId));
			em.merge(ingredient);
		}
		em.getTransaction().commit();

		getServletContext().getRequestDispatcher("/manageIngredients.jsp").forward(request, response);
	}
}