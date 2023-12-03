package utils

/**
 * 1.
 * Imports JUnit5
 * Imports validRange() function from Utilities object
 */
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.Utilities.validRange

class UtilitiesTest
{
    /**
     * 2.
     * Verifies that the function returns true for different input values and ranges
     * Positive Test Data
     */
    @Test
    fun validRangeWorksWithPositiveTestData()
    {
        Assertions.assertTrue(validRange(1, 1, 1))
        Assertions.assertTrue(validRange(1, 1, 2))
        Assertions.assertTrue(validRange(1, 0, 1))
        Assertions.assertTrue(validRange(1, 0, 2))
        Assertions.assertTrue(validRange(-1, -2, -1))
    }

    /**
     * 3.
     * Verifies  that the function returns false for different input values and ranges
     * Negative Test Data
     */
    @Test
    fun validRangeWorksWithNegativeTestData()
    {
        Assertions.assertFalse(validRange(1, 0, 0))
        Assertions.assertFalse(validRange(1, 1, 0))
        Assertions.assertFalse(validRange(1, 2, 1))
        Assertions.assertFalse(validRange(-1, -1, -2))
    }
}
