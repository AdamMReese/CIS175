/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Aug 28, 2023
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import model.Calculator;
import org.junit.Test;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(3, 2));
    }

    @Test
    public void testSubtract() {
        assertTrue(calculator.subtract(5, 2) == 3);
    }

    @Test
    public void testMultiply() {
        assertNotEquals(5, calculator.multiply(2, 2));
    }
}
