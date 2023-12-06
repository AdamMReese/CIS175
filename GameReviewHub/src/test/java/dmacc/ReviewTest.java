package dmacc;

import org.junit.jupiter.api.Test;

import dmacc.model.Review;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {

    @Test
    public void testReviewAttributes() {
        Review review = new Review();
        review.setReviewText("Great game!");
        review.setStarRating(5);

        assertEquals("Great game!", review.getReviewText());
        assertEquals(5, review.getStarRating());
    }
}
