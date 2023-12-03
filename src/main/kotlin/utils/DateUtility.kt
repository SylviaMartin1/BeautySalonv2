package utils

/**
 * 1.
 * Imports java time package
 */
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateUtility
{
    /**
     * 2.
     * Checks if a given date matches the format (d/m/y)"
     * If it's a match, it returns true; otherwise, it returns false
     */
    @JvmStatic
    fun isDateValid(dateToCheck: String?): Boolean
    {
        //val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dateFormatter1 = DateTimeFormatter.ofPattern("d/M/yyyy")
        val dateFormatter2 = DateTimeFormatter.ofPattern("dd/M/yyyy")
        val dateFormatter3 = DateTimeFormatter.ofPattern("d/MM/yyyy")
        val dateFormatter4 = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        /*
        return try
        {
            LocalDate.parse(dateToCheck, dateFormatter1)
            LocalDate.parse(dateToCheck, dateFormatter2)
            LocalDate.parse(dateToCheck, dateFormatter3)
            LocalDate.parse(dateToCheck, dateFormatter4)
            true
        }
        catch (e: DateTimeParseException)
        {
            false
        }

         */
        //Parsing Overwriting Issue so make them one object
        val formatters = listOf(dateFormatter1, dateFormatter2, dateFormatter3, dateFormatter4)

        for (formatter in formatters) {
            try {
                LocalDate.parse(dateToCheck, formatter)
                return true
            } catch (e: DateTimeParseException) {
                // Continue trying other formats if this one fails
            }
        }

        return false
    }


}