package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.Ingredient;
import model.Recipe;

@WebServlet("/recipeServlet")
public class RecipeServlet extends HttpServlet {
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
				Recipe recipe = em.find(Recipe.class, Integer.parseInt(id));
				request.setAttribute("recipe", recipe);
				getServletContext().getRequestDispatcher("/viewRecipe.jsp").forward(request, response);
			} else if ("delete".equals(action)) {
				Recipe recipeToDelete = em.find(Recipe.class, Integer.parseInt(id));
				em.getTransaction().begin();
				em.remove(recipeToDelete);
				em.getTransaction().commit();
				getServletContext().getRequestDispatcher("/manageRecipes.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String recipeId = request.getParameter("recipeId");
		String recipeName = request.getParameter("name");
		int servings = Integer.parseInt(request.getParameter("servings"));
		int prepTime = Integer.parseInt(request.getParameter("preparationTime"));
		String category = request.getParameter("category");
		String[] ingredientItems = request.getParameterValues("ingredients");
		String[] instructions = request.getParameterValues("instructions");

		Category cat = em.find(Category.class, Integer.parseInt(category));
		List<Ingredient> ingredients = new ArrayList<>();
		for (String ingredientId : ingredientItems) {
			Ingredient ingredient = em.find(Ingredient.class, Integer.parseInt(ingredientId));
			ingredients.add(ingredient);
		}

		String instructionText = String.join("\n", instructions);

		Recipe recipe = new Recipe(recipeName, servings, prepTime, cat, instructionText);
		recipe.setIngredients(ingredients);

		em.getTransaction().begin();
		if (recipeId == null || recipeId.isEmpty()) {
			em.persist(recipe);
		} else {
			recipe.setId(Integer.parseInt(recipeId));
			em.merge(recipe);
		}
		em.getTransaction().commit();

		getServletContext().getRequestDispatcher("/manageRecipes.jsp").forward(request, response);
	}
}