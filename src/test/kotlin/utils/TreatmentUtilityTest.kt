package utils

/**
 * 1.
 * Imports JUnit5
 * Imports Category utility
 */
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.TreatmentUtility.categories
import utils.TreatmentUtility.isValidTreatment
internal class CategoryUtilityTest
{
    /**
     * 2.
     *Verifies that the set contains 6 elements: facials, manicure, pedicure, massage, waxing, makeup
     * Verifies that the set doesn't contain threading
     */
    @Test
    fun categoriesReturnsFullCategoriesSet()
    {
        Assertions.assertEquals(6, categories.size)
        Assertions.assertTrue(categories.contains("Facial"))
        Assertions.assertTrue(categories.contains("Manicure"))
        Assertions.assertTrue(categories.contains("Pedicure"))
        Assertions.assertTrue(categories.contains("Massage"))
        Assertions.assertTrue(categories.contains("Waxing"))
        Assertions.assertTrue(categories.contains("Makeup"))
        Assertions.assertFalse(categories.contains("Threading"))
    }

    /**
     * 2.
     * Verifies that the set contains 6 elements: facials, manicure, pedicure, massage, waxing, makeup, threading
     * Verifies that the set doesn't contain "threading."
     * Verifies that the set is case-insensitive
     */

    @Test
    fun isValidCategoryTrueWhenTreatmentExists()
    {
        Assertions.assertTrue(isValidTreatment("facial"))
        Assertions.assertTrue(isValidTreatment("manicure"))
        Assertions.assertTrue(isValidTreatment("pedicure"))
        Assertions.assertTrue(isValidTreatment("massage"))
        Assertions.assertTrue(isValidTreatment("waxing"))
        Assertions.assertTrue(isValidTreatment("makeup"))
        Assertions.assertFalse(isValidTreatment("threading"))

    }

    /**
     * 3.
     * Verifies that the set contains 7 elements: facials, manicure, pedicure, massage, waxing, makeup, threading
     * Verifies that the set doesn't contain "Dogs."
     * Verifies that the set doesn't include misspelled code
     */
    @Test
    fun isValidCategoryFalseWhenCategoryDoesNotExist(){
        Assertions.assertFalse(isValidTreatment("Fac"))
        Assertions.assertFalse(isValidTreatment("Man"))
        Assertions.assertFalse(isValidTreatment("Ped"))
        Assertions.assertFalse(isValidTreatment("Mas"))
        Assertions.assertFalse(isValidTreatment("Waxeng"))
        Assertions.assertFalse(isValidTreatment("Massege"))
        Assertions.assertFalse(isValidTreatment("Threeding"))

    }
}

