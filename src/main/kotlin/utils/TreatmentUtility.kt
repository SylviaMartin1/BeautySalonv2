package utils

object TreatmentUtility
{
    /**
     * 1.
     * Set treatments that can be used
     */
    @JvmStatic
    val categories = setOf ("Facial", "Manicure","Pedicure", "Massage", "Waxing", "Makeup")


    /**
     * 2.
     * Checks if a given category matches any category in a list (case-insensitive)
     * If it's a match, it returns true; otherwise, it returns false
     */
    @JvmStatic
    fun isValidTreatment(categoryToCheck: String?): Boolean
    {
        for (category in categories)
        {
            if (category.equals(categoryToCheck, ignoreCase = true))
            {
                return true
            }
        }
        return false
    }
}