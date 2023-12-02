import models.Appointment
import models.Client

//1. Packages
/**
 * Holds collection of helpful tools and services
 */

object Utilities
{
    /**
     * Static method means we can call it over a class without creating Utilities object
     * Turns client list into a single string separated by newline characters
     */
    @JvmStatic
    fun formatListString(clientsToFormat: List<Client>): String =
        clientsToFormat
            .joinToString(separator = "\n") { client->  "$client" }

    /**
     * Static method means we can call it over a class without creating Utilities object
     * Turns appointment set into a single string separated by newline characters
     */
    @JvmStatic
    fun formatSetString(appointmentsToFormat: Set<Appointment>): String =
        appointmentsToFormat
            .joinToString(separator = "\n") { appointment ->  "\t$appointment" }


}
