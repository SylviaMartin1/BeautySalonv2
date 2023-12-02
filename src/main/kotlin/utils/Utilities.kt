package utils
import models.Appointment
import models.Client

object Utilities
{
    /**
     *  function to
     */
    @JvmStatic
    fun formatListString(clientsToFormat: List<Client>): String =
        clientsToFormat
            .joinToString(separator = "\n") { client->  "$client" }

    @JvmStatic
    fun formatSetString(appointmentsToFormat: Set<Appointment>): String =
        appointmentsToFormat
            .joinToString(separator = "\n") { appointment ->  "\t$appointment" }

}
