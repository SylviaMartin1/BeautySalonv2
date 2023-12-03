package utils

/**
 * 1.
 * Imports JUnit5
 */
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class DateUtilityTest {
    /**
     * 2.
     * Verify that if dates are in the form d/m/yyyy with no dashes, they are valid
     */
    @Test
    fun dayMonthYearNoDashes() {
        assertTrue(DateUtility.isDateValid("5/5/2022"))
        assertFalse(DateUtility.isDateValid("5-5-2022"))
        assertTrue(DateUtility.isDateValid("15/5/2022"))
        assertFalse(DateUtility.isDateValid("15-5-2022"))
        assertTrue(DateUtility.isDateValid("31/12/2022"))
        assertFalse(DateUtility.isDateValid("31-12-2022"))
    }

    /**
     * 3.
     * Verify that if dates are in the form m/d/y with or without dashes, they are not valid
     */
    @Test
    fun monthDayYear() {
        assertFalse(DateUtility.isDateValid("2/18/2022"))
        assertFalse(DateUtility.isDateValid("2-18-2022"))
    }

    /**
     * 4.
     * Verify that if dates are in the form y/m/d or y/d/m with or without dashes, they are not valid
     */
    @Test
    fun yearMonthDay() {
        assertFalse(DateUtility.isDateValid("2022/5/5"))
        assertFalse(DateUtility.isDateValid("2022-5-5"))
        assertFalse(DateUtility.isDateValid("2022/5/15"))
        assertFalse(DateUtility.isDateValid("2022-5-15"))
        assertFalse(DateUtility.isDateValid("2022/12/31"))
        assertFalse(DateUtility.isDateValid("2022-12-31"))
        assertFalse(DateUtility.isDateValid("2022/2/18"))
        assertFalse(DateUtility.isDateValid("2022-2-18"))
    }
}









