//1. Packages
/**
 * Groups related classes which process user input
 */
package controllers



//2. Import Statements
/**
 * Imports 'Client' so that it can be used in this file
 */
import models.Client
import utils.Utilities
import java.util.ArrayList



//3. Variables
/**
 * ArrayList to hold collection of clients
 */
private var clients = ArrayList<Client>()



//4. Functions
//Create functions
/**
 * addClient()
 * function to add a client to the arraylist
 */
fun addClient(client: Client): Boolean
{
    client.clientId = getClientId()
    return clients.add(client)
}


//Read functions
/**
 * listAllClients()
 * function to list all clients
 */
fun listAllClients() =
    if (clients.isEmpty()) "No clients stored"
    else Utilities.formatListString(clients)

/**
 * listConfirmedClients()
 * function to list all confirmed clients
 */
fun listPaidClients() =
    if (numberOfUnPaidClients() == 0) "No paid clients stored"
    else Utilities.formatListString(clients.filter { client -> client.hasPaid })


/**
 * listUnpaidClients
 */
fun listUnpaidClients() =
    if (numberOfUnPaidClients() == 0) "No unpaid clients stored"
    else Utilities.formatListString(clients.filter { client -> !client.hasPaid })

/**
 * numberOfClients()
 * function to count the number of clients
 */
fun numberOfClients() = clients.size

/**
 * numberOfPaidClients()
 * function to count the number of confirmed clients
 */
fun numberOfPaidClients(): Int = clients.count { client: Client -> client.hasPaid }

/**
 * numberOfUnPaidClients
 */
fun numberOfUnPaidClients(): Int = clients.count { client: Client -> !client.hasPaid }


//Update functions
fun updateClient(id: Int, client: Client?): Boolean
{
    // find the note object by the index number
    val foundClient = findClientById(id)

    // if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
    if ((foundClient != null) && (client != null))
    {
        foundClient.firstName = client.firstName
        foundClient.lastName = client.lastName
        foundClient.street = client.street
        foundClient.county = client.county
        foundClient.email = client.email
        foundClient.phone = client.phone
        foundClient.allergy = client.allergy
        return true
    }
    // if the client was not found, return false, indicating that the update was not successful
    return false
}


//Delete functions
/**
 * deleteClient()
 * function to delete a client from the arraylist
 */
fun deleteClient(id: Int) = clients.removeIf { client -> client.clientId == id }


//Search functions
/**
 * findClientById()
 * function to find a client by their id
 */
fun findClientById(clientId: Int) = clients.find { client -> client.clientId == clientId }

/**
 * searchClientByFirstName()
 * function to search a client by their first name
 */
fun searchClientByFirstName(searchString: String) =
    Utilities.formatListString(
        clients.filter { client -> client.firstName.contains(searchString, ignoreCase = true) }
    )

/**
 * searchClientByLastName()
 * function to search for client by their last name
 */
fun searchClientByLastName(searchString: String) =
    Utilities.formatListString(
        clients.filter { client -> client.lastName.contains(searchString, ignoreCase = true) }
    )

/**
 * searchClientByStreet()
 * function to search for client by their street
 */
fun searchClientByStreet(searchString: String) =
    Utilities.formatListString(
        clients.filter { client -> client.street.contains(searchString, ignoreCase = true) }
    )

/**
 * searchClientByCounty()
 * function to search for client by their county
 */
fun searchClientByCounty(searchString: String) =
    Utilities.formatListString(
        clients.filter { client -> client.county.contains(searchString, ignoreCase = true) }
    )

/**
 * searchClientByEmail()
 * function to search for client by their email
 */
fun searchClientByEmail(searchString: String) =
    Utilities.formatListString(
        clients.filter { client -> client.email.contains(searchString, ignoreCase = true) }
    )

/**
 * searchClientByAllergy()
 * function to search for client by their allergy
 */
fun searchClientByAllergy(searchString: String) =
    Utilities.formatListString(
        clients.filter { client -> client.allergy.contains(searchString, ignoreCase = true) }
    )

/**
 *searchAppointmentByTreatments()
 * function to search for an appointment by its treatments
 */
fun searchAppointmentByTreatments(searchString: String): String
{
    return if (numberOfClients() == 0) "No clients stored"
    else {
        var listOfNotes = ""
        for (client in clients) {
            for (appointment in client.appointments) {
                if (appointment.treatment.contains(searchString)) {
                    listOfNotes += "${client.clientId}: ${client.firstName} ${client.lastName} \n\t${appointment}\n"
                }
            }
        }
        if (listOfNotes == "") "No items found for: $searchString"
        else listOfNotes
    }
}


//Helper functions
/**
 * getClientId()
 * function to get client's id
 */
private var lastClientId = 0
private fun getClientId() = lastClientId++


