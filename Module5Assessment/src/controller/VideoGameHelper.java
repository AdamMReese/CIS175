/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Sep 19, 2023
 */

package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import model.VideoGame;

public class VideoGameHelper {

	// Constant representing the name of the persistence unit defined in
	// persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "VideoGameDB";

	// EntityManagerFactory instance to create EntityManager instances for
	// interacting with the database
	private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	// Method to insert a new video game record into the database
	public void insertGame(VideoGame videoGame) {
		// Creating an EntityManager instance to interact with the database
		EntityManager em = emFactory.createEntityManager();
		try {
			// Beginning a new transaction
			em.getTransaction().begin();

			// Persisting the video game object to the database
			em.persist(videoGame);

			// Committing the transaction to save the changes
			em.getTransaction().commit();
		} catch (Exception e) {
			// Rolling back the transaction in case of an error
			em.getTransaction().rollback();

			// Logging the error message
			System.out.println("Error inserting game: " + e.getMessage());
		} finally {
			// Closing the EntityManager to release resources
			em.close();
		}
	}

	// Method to retrieve a list of all video game records from the database
	public List<VideoGame> showAllGames() {
		EntityManager em = emFactory.createEntityManager();
		TypedQuery<VideoGame> typedQuery = em.createQuery("SELECT v FROM VideoGame v", VideoGame.class);
		List<VideoGame> allGames = typedQuery.getResultList();
		em.close();
		return allGames;
	}

	// Method to find a video game record in the database by its ID
	public VideoGame findGameById(Integer gameId) {
		EntityManager em = emFactory.createEntityManager();
		VideoGame foundGame = em.find(VideoGame.class, gameId);
		em.close();
		return foundGame;
	}

	// Method to delete a video game record from the database by its ID
	public void deleteGame(Integer gameId) {
		EntityManager em = emFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			VideoGame foundGame = em.find(VideoGame.class, gameId);
			if (foundGame != null) {
				em.remove(foundGame);
				em.getTransaction().commit();
			} else {
				System.out.println("Game with ID " + gameId + " not found.");
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error deleting game: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	// Method to update the details of an existing video game record in the database
	public void updateGame(VideoGame videoGameToUpdate) {
		EntityManager em = emFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(videoGameToUpdate);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error updating game: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	// Method to retrieve the maximum ID value currently in the database, used for
	// proper numerical organization of records
	public int getMaxId() {
		EntityManager em = emFactory.createEntityManager();
		try {
			TypedQuery<Integer> query = em.createQuery("SELECT MAX(v.id) FROM VideoGame v", Integer.class);
			Integer maxId = query.getSingleResult();
			return (maxId != null) ? maxId : 0;
		} finally {
			em.close();
		}
	}

	// Method to close the EntityManagerFactory, releasing all resources held by it
	public void cleanUp() {
		emFactory.close();
	}
}
