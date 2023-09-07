/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Sep 6, 2023
 */

package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import model.VideoGame;

public class VideoGameHelper {

	// Creating an EntityManagerFactory object to interact with the persistence unit
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("VideoGameDB");

	// Method to insert a new video game into the database
	public void insertGame(VideoGame videoGame) {
		EntityManager em = emfactory.createEntityManager(); // Creating an EntityManager object
		em.getTransaction().begin(); // Beginning a transaction
		try {
			em.persist(videoGame); // Persisting the video game object
			em.getTransaction().commit(); // Committing the transaction
		} catch (Exception e) {
			em.getTransaction().rollback(); // Rolling back the transaction in case of an exception
			System.out.println("Error inserting game: " + e.getMessage()); // Printing the error message
		} finally {
			em.close(); // Closing the EntityManager
		}
	}

	// Method to retrieve a list of all video games from the database
	public List<VideoGame> showAllGames() {
		EntityManager em = emfactory.createEntityManager(); // Creating an EntityManager object
		TypedQuery<VideoGame> typedQuery = em.createQuery("SELECT v FROM VideoGame v", VideoGame.class); // Creating a query to select all video games
		List<VideoGame> allGames = typedQuery.getResultList(); // Getting the result list from the query
		em.close(); // Closing the EntityManager
		return allGames; // Returning the list of all video games
	}

	// Method to find a video game by its ID
	public VideoGame findGameById(Integer gameId) {
		EntityManager em = emfactory.createEntityManager(); // Creating an EntityManager object
		VideoGame foundGame = em.find(VideoGame.class, gameId); // Finding the video game by its ID
		em.close(); // Closing the EntityManager
		return foundGame; // Returning the found video game
	}

	// Method to delete a video game by its ID
	public void deleteGame(Integer gameId) {
		EntityManager em = emfactory.createEntityManager(); // Creating an EntityManager object
		em.getTransaction().begin(); // Beginning a transaction
		try {
			VideoGame foundGame = em.find(VideoGame.class, gameId); // Finding the video game by its ID
			if (foundGame != null) {
				em.remove(foundGame); // Removing the found video game
				em.getTransaction().commit(); // Committing the transaction
			} else {
				System.out.println("Game with ID " + gameId + " not found."); // Printing a message if the game was not found
				em.getTransaction().rollback(); // Rolling back the transaction
			}
		} catch (Exception e) {
			em.getTransaction().rollback(); // Rolling back the transaction in case of an exception
			System.out.println("Error deleting game: " + e.getMessage()); // Printing the error message
		} finally {
			em.close(); // Closing the EntityManager
		}
	}

	// Method to update the details of a video game
	public void updateGame(VideoGame videoGameToUpdate) {
		EntityManager em = emfactory.createEntityManager(); // Creating an EntityManager object
		em.getTransaction().begin(); // Beginning a transaction
		try {
			em.merge(videoGameToUpdate); // Merging the updated video game object
			em.getTransaction().commit(); // Committing the transaction
		} catch (Exception e) {
			em.getTransaction().rollback(); // Rolling back the transaction in case of an exception
			System.out.println("Error updating game: " + e.getMessage()); // Printing the error message
		} finally {
			em.close(); // Closing the EntityManager
		}
	}

	// Method to close the EntityManagerFactory
	public void cleanUp() {
		emfactory.close();
	}
}
