package utils
import models.Appointment
import models.Client

object Utilities
{
    /**
     *  function to write client list in string form
     */
    @JvmStatic
    fun formatListString(clientsToFormat: List<Client>): String =
        clientsToFormat
            .joinToString(separator = "\n") { client->  "$client" }

    /**
     * function to write appointment list in string form
     */
    @JvmStatic
    fun formatSetString(appointmentsToFormat: Set<Appointment>): String =
        appointmentsToFormat
            .joinToString(separator = "\n") { appointment ->  "\t$appointment" }



    /**
     * Checks if a given index is greater than or equal to 0 (because lists start at 0) and less than the total number of items in the list
     * If these conditions are met, the function returns true, indicating the index is valid; otherwise, it returns false, indicating the index is not valid
     */
    @JvmStatic
    fun isValidListIndex(index: Int, list: List<Any>): Boolean
    {
        return (index >= 0 && index < list.size)
    }

    /**
     * Checks if a given number is within a specified range
     * If numberToCheck is greater than or equal to min and less than or equal to max, the function returns true, indicating that the number is within the range. Otherwise, it returns false, indicating that the number is outside the range
     */
    @JvmStatic
    fun validRange(numberToCheck: Int, min: Int, max: Int): Boolean {
        return numberToCheck in min..max
    }

}
