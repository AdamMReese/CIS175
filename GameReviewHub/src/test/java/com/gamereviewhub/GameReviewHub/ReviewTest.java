package com.gamereviewhub.gamereviewhub;

import com.gamereviewhub.gamereviewhub.model.Review;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {

	@Test
	public void testReviewAttributes() {
		Review review = new Review();
		review.setContent("Great game!");
		review.setRating(5);

		assertEquals("Great game!", review.getContent());
		assertEquals(5, review.getRating());
	}
}
