//1. Import Statements
import controllers.ClientAPI
import models.Appointment
import models.Client
import utils.ScannerInput
import utils.ScannerInput.readNextDouble
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import kotlin.system.exitProcess


//2. Variables
/**
 * holds clientAPI class as an object
 */
private val clientAPI = ClientAPI()


//3. Methods
/**
 * main()
 * Starting point of the program
 * Where the program starts running
 * Calls runMenu()
 */
fun main() = runMenu()

/**
 * mainMenu()
 * Displays menu
 * Allows user input
 */
fun mainMenu() = ScannerInput.readNextInt(
    """ 
         > -----------------------------------------------------  
         > |                  NOTE KEEPER APP                  |
         > -----------------------------------------------------  
         > | CLIENT MENU             
         > | 1) Add a client  
         > | 2) List clients
         > | 3) Update a client
         > | 4) Delete a client
         > | 5) Clear all clients from the system
         > | 6) Check if there are clients in the system                                                      
         > ----------------------------------------------------- 
         > | REPORT MENU FOR CLIENTS       
         > | 7) Search for a client by their Id
         > | 8) Search for a client by their first name
         > | 9) Search for a client by their last name
         > | 10) Search for a client by their street
         > | 11) Search for a client by their county
         > | 12) Search for a client by their email
         > | 13) Search for a client by their phone number
         > | 14) Search for a client by their allergy                                                     
         > -----------------------------------------------------
         > | APPOINTMENT MENU                                            
         > | 15) Add an appointment
         > | 16) List confirmed appointments      
         > | 17) Update an appointment
         > | 18) Delete an appointment
         > -----------------------------------------------------  
         > | REPORT MENU FOR APPOINTMENTS     
         > | 19) Search for an appointment by its Id
         > | 20) Search for an appointment by its time
         > | 21) Search for an appointment by its date
         > | 22) Search for an appointment by its treatments
         > | 23) Search for an appointment by its cost
         > | 24) Search for an appointment by its rating                                              
         > -----------------------------------------------------
         > | 0) Exit                                         
         > -----------------------------------------------------  
         > ==>> """.trimMargin(">")
)

/**
 * runMenu()
 * Loops mainMenu()
 * Processes user's choice
 */
fun runMenu()
{
    do
    {
        when (val option = mainMenu())
        {
            //Clients
            1 -> {
                val hasPaidUserInput = readBooleanFromUserInputOfClientPaymentStatus()
                addClient(hasPaidUserInput)
            }
            2 -> listClients()
            3 -> {
                val hasPaidUserInput = readBooleanFromUserInputOfClientPaymentStatus()
                updateClient(hasPaidUserInput)
            }
            4 -> deleteClient()
            5 -> clearAllClients()
            6 -> checkIfThereAreClients()
            7 -> listClientsbyId()
            8 -> searchClientsByFirstName()
            9 -> searchClientsByLastName()
            10 -> searchClientsByStreet()
            11 -> searchClientsByCounty()
            12 -> searchClientsByEmail()
            13 -> searchClientsByPhone()
            14 -> searchClientsByAllergy()
            //Appointments
            15 -> {
                val isConfirmedUserInput = readBooleanFromUserInputOfAppointmentConfirmationStatus()
                addAppointmentForClient(isConfirmedUserInput)
            }

            16 -> listConfirmedAppointments()

            17  -> {
                val isConfirmedUserInput = readBooleanFromUserInputOfAppointmentConfirmationStatus()
                updateAppointmentForClient(isConfirmedUserInput)
            }
            18  -> deleteAnAppointmentForAClient()
            19  -> searchAppointmentsById()
            20 -> searchAppointmentsByTime()
            21 -> searchAppointmentsByDate()
            22 -> searchAppointmentsByTreatment()
            23 -> searchAppointmentsByCost()
            24 -> searchAppointmentsByRating()
            0 -> exitApp()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

//Create functions
/**
 * addClient()
 * function to allow users to add clients
 * explicitly state data type of hasPaid
 */
fun addClient(hasPaid: Boolean)
{
    val firstName = readNextLine("Enter the client's first name: ")
    val lastName = readNextLine("Enter the client's last name: ")
    val street = readNextLine("Enter the client's street: ")
    val county = readNextLine("Enter the client's county: ")
    val email = readNextLine("Enter the client's email: ")
    val phone = readNextInt("Enter the client's phone: ")
    val allergy = readNextLine("Enter the client's allergies: ")
    val isAdded = clientAPI.addClient(Client(firstName = firstName, lastName = lastName, street = street, county = county, email = email, phone = phone, allergy = allergy, hasPaid = hasPaid))
    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}

/**
 *
 * add an appointment for a client
 */
 fun addAppointmentForClient(isConfirmed: Boolean)
 {
    val client: Client? = askUserToChoosePaidClient()
    if (client != null) {
        if (client.addAppointment(Appointment(
                time = readNextDouble("\t Appointment Time: "),
                date = readNextLine("\t Appointment Date: "),
                treatment = readNextLine("\t Appointment Services: "),
                cost = readNextInt("\t Appointment Cost:"),
                isConfirmed = isConfirmed,
                rating = readNextInt("\t Appointment rating:")
            )))
            println("Add Successful!")
        else println("Add NOT Successful")
    }
}

fun updateAppointmentForClient(isConfirmed: Boolean)
{
  val client: Client? = askUserToChoosePaidClient()
  if (client != null)
  {
      val appointment: Appointment? = askUserToChooseAppointment(client)
      if (appointment != null)
      {
          val newTime = readNextDouble("Enter a new appointment time: ")
          val newDate = readNextLine("Enter a new appointment date: ")
          val newTreatment = readNextLine("Enter the services that the new appointment will provide: ")
          val newCost = readNextInt("Enter a new appointment cost: ")
          val isConfirmed = isConfirmed
          val newRating = readNextInt("Enter a new rating: ")
          if (client.updateAppointment(appointment.appointmentId, Appointment(time = newTime, date = newDate,
                                      treatment = newTreatment, cost = newCost, isConfirmed = isConfirmed, rating = newRating)))

                  else
                  {
                      println("Appointment Details not updated")
                  }
     }
              else
              {
                  println("Invalid Appointment Id")
              }
  }
}


 //Read methods
fun listClients()
{
    if (clientAPI.numberOfClients() > 0)
    {
        val option = ScannerInput.readNextInt(
            """
                  > --------------------------------
                  > |   1) View ALL Clients       
                  > |   2) View ALL CONFIRMED Clients      
                  > |   3) View ALL UNCONFIRMED Clients    
                  > |   4) List the NUMBER OF ALL Clients
                  > |   5) List the NUMBER OF ALL CONFIRMED Clients
                  > |   6) List the NUMBER OF ALL UNCONFIRMED Clients
                  > --------------------------------
         > ==>> """.trimMargin(">")
        )

        when (option)
        {

            1 -> listAllClients()
            2 -> listAllPaidClients()
            3 -> listAllUnpaidClients()
            4 -> listNumberOfAllClients()
            5 -> listNumberOfPaidClients()
            6 -> listNumberOfUnpaidClients()
            else -> println("Invalid option entered: $option")
        }
    } else {
        println("Option Invalid - No notes stored")
    }
}

/**
 * listAllClients()
 * Method to allow a user to list all clients
 **/
fun listAllClients() = println(clientAPI.listAllClients())

/**
 * listAllPaidClients()
 * Method to allow a user to list all clients
 **/
fun listAllPaidClients() = println(clientAPI.listPaidClients())

/**
 * listAllUnPaidClients()
 * Method to allow a user to list all unpaid clients
 **/
fun listAllUnpaidClients() = println(clientAPI.listUnpaidClients())

/**
 * listTheNumberOfAllClients()
 * Method to allow a user to list the number of all clients
 */
fun listNumberOfAllClients() = println(clientAPI.numberOfClients())

/**
 * listTheNumberOfPaidClients()
 * Method to allow a user to list the number of paid clients
 */
fun listNumberOfPaidClients() = println(clientAPI.numberOfPaidClients())

/**
 * listTheNumberOfUnpaidClients()
 * method to allow a user to list the number of unpaid clients
 */
fun listNumberOfUnpaidClients() = println(clientAPI.numberOfUnPaidClients())


/**
 * listConfirmedAppointments()
 * method to allow a user to list the confirmed appointments
 */
fun listConfirmedAppointments(){
    if (clientAPI.listNumberOfConfirmedAppointments() > 0) {
        println("Total Confirmed Appointments: ${clientAPI.listNumberOfConfirmedAppointments()}")
    }
    println(clientAPI.listConfirmedAppointments())
}


//Update methods
/**
 * updateClient()
 * function to allow a user to update a client
 */
//Update functions
fun updateClient(hasPaid: Boolean)
{
    listClients()
    if (clientAPI.numberOfClients() > 0) {
        // only ask the user to choose the note if notes exist
        val id = ScannerInput.readNextInt("Enter the id of the note to update: ")
        if (clientAPI.findClientById(id) != null) {
            val firstName = ScannerInput.readNextLine("Enter the client's first name:  ")
            val lastName = ScannerInput.readNextLine("Enter the client's last name:  ")
            val street = ScannerInput.readNextLine("Enter the client's street:  ")
            val county = ScannerInput.readNextLine("Enter the client's county:  ")
            val email = ScannerInput.readNextLine("Enter the client's email:  ")
            val phone = ScannerInput.readNextInt("Enter the client's phone number:  ")
            val allergy = ScannerInput.readNextLine("Enter the client's allergies: ")

            // pass the index of the note and the new note details to NoteAPI for updating and check for success.
            if (clientAPI.updateClient(id, Client(0, firstName, lastName, street, county, email, phone, allergy, hasPaid))){
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no clients for this index number")
        }
    }
}

/**
 *
 */



//Delete methods
/**
 * deleteClient()
 * function to allow user to delete a client
 */
fun deleteClient()
{
    listClients()
    if (clientAPI.numberOfClients() > 0) {
        // only ask the user to choose the client to delete if clients exist
        val id = ScannerInput.readNextInt("Enter the id of the client to delete: ")
        // pass the index of the note to NoteAPI for deleting and check for success.
        val clientToDelete = clientAPI.deleteClient(id)
        if (clientToDelete) {
            println("Delete Successful!")
        } else {
            println("Delete NOT Successful")
        }
    }
}

/**
 * deleteAnAppointmentForAClient
 * function to delete an appointment for a client
 */
fun deleteAnAppointmentForAClient() {
    val client: Client? = askUserToChoosePaidClient()
    if (client != null) {
        val appointment: Appointment? = askUserToChooseAppointment(client)
        if (appointment != null) {
            val isDeleted = client.deleteAppointment(appointment.appointmentId)
            if (isDeleted) {
                println("Delete Successful!")
            } else {
                println("Delete NOT Successful")
            }
        }
    }
}


/**
 * clearAllClients()
 * function to allow a user to clear all clients
 */
fun clearAllClients()
{
    clientAPI.clearAllClients()
    println( " All clients have been cleared from the system")
}


//SEARCH METHODS
/**
 * listClientsById
 * search for notes by their Id
 */
fun listClientsbyId()
{
    val searchResults = readNextInt("Enter the client's Id: ")
    println(clientAPI.findClientById(searchResults))
}

/**
 *searchClientsByFirstName
 * allow a user to search for a client by their first name
 */
fun searchClientsByFirstName()
{
    val searchQuery = readNextLine("Enter the client's first name: ")
    val searchResults = clientAPI.searchClientByFirstName(searchQuery)
    if (searchResults.isEmpty())
    {
        println("No clients found")
    } else {
        println(searchResults)
    }
}

/**
 *searchClientsByLastName
 * allow a user to search for a client by their last name
 */
fun searchClientsByLastName()
{
    val searchQuery = readNextLine("Enter the client's last name: ")
    val searchResults = clientAPI.searchClientByLastName(searchQuery)
    if (searchResults.isEmpty())
    {
        println("No clients found")
    } else {
        println(searchResults)
    }
}

/**
 *searchClientsByStreet
 * allow a user to search for a client by their street
 */
fun searchClientsByStreet()
{
    val searchQuery = readNextLine("Enter the client's street: ")
    val searchResults = clientAPI.searchClientByStreet(searchQuery)
    if (searchResults.isEmpty())
    {
        println("No clients found")
    } else {
        println(searchResults)
    }
}

/**
 *searchClientsByCounty
 * allow a user to search for a client by their county
 */
fun searchClientsByCounty()
{
    val searchQuery = readNextLine("Enter the client's county: ")
    val searchResults = clientAPI.searchClientByCounty(searchQuery)
    if (searchResults.isEmpty())
    {
        println("No clients found")
    } else {
        println(searchResults)
    }
}


/**
 *searchClientsByEmail
 * allow a user to search for a client by their email
 */
fun searchClientsByEmail()
{
    val searchQuery = readNextLine("Enter the client's email: ")
    val searchResults = clientAPI.searchClientByEmail(searchQuery)
    if (searchResults.isEmpty())
    {
        println("No clients found")
    } else {
        println(searchResults)
    }
}


/**
 *searchClientsByPhone
 * allow a user to search for a client by their email
 */
fun searchClientsByPhone()
{
    val searchQuery = readNextInt("Enter the client's phone number: ")
    val searchResults = clientAPI.searchClientByPhone(searchQuery)
    if (searchResults.isEmpty())
    {
        println("No clients found")
    } else {
        println(searchResults)
    }
}


/**
 *searchClientsByAllergy
 * allow a user to search for a client by their allergy
 */
fun searchClientsByAllergy()
{
    val searchQuery = readNextLine("Enter the client's allergy: ")
    val searchResults = clientAPI.searchClientByAllergy(searchQuery)
    if (searchResults.isEmpty())
    {
        println("No clients found")
    } else {
        println(searchResults)
    }
}

/**
 * listAppointmentsById
 * search for appointments by their Id
 */
fun searchAppointmentsById()
{
    val searchQuery = readNextInt("Enter the appointment's id: ")
    val searchResults = clientAPI.searchAppointmentById(searchQuery)
    if (searchResults.isEmpty())
    {
        println("No appointments found")
    } else {
        println(searchResults)
    }
}


/**
 *searchAppointmentsByTime
 * function to search for an appointment by their time
 */
fun searchAppointmentsByTime()
{
    val searchQuery = readNextDouble("Enter the time to search by: ")
    val searchResults = clientAPI.searchAppointmentByTime(searchQuery)
    if (searchResults.isEmpty()) {
        println("No appointments found")
    } else {
        println(searchResults)
    }
}


/**
 *searchAppointmentsByDate
 * function to search for an appointment by their date
 */
fun searchAppointmentsByDate()
{
    val searchQuery = readNextLine("Enter the date to search by: ")
    val searchResults = clientAPI.searchAppointmentByDate(searchQuery)
    if (searchResults.isEmpty()) {
        println("No appointments found")
    } else {
        println(searchResults)
    }
}


/**
 *searchAppointmentsByDate
 * function to search for an appointment by their treatment
 */
fun searchAppointmentsByTreatment()
{
    val searchQuery = readNextLine("Enter the treatment to search by: ")
    val searchResults = clientAPI.searchAppointmentByTreatments(searchQuery)
    if (searchResults.isEmpty()) {
        println("No appointments found")
    } else {
        println(searchResults)
    }
}



/**
 *searchAppointmentsByDate
 * function to search for an appointment by their cost
 */
fun searchAppointmentsByCost()
{
    val searchQuery = readNextInt("Enter the price to search by: ")
    val searchResults = clientAPI.searchAppointmentByCost(searchQuery)
    if (searchResults.isEmpty()) {
        println("No appointments found")
    } else {
        println(searchResults)
    }
}


/**
 *searchAppointmentsByRating
 * function to search for an appointment by its rating
 */
fun searchAppointmentsByRating()
{
    val searchQuery = readNextInt("Enter the rating to search by: ")
    val searchResults = clientAPI.searchAppointmentByRating(searchQuery)
    if (searchResults.isEmpty()) {
        println("No appointments found")
    } else {
        println(searchResults)
    }
}


//Other methods
/**
 * checkIfThereAreClients()
 * Method to check if there are clients
 */
fun checkIfThereAreClients() = println(clientAPI.checkIfThereAreClients())


/**
 * exitApp()
 * function to allow user to exit the app
 */
fun exitApp()
{
    println("Exiting...bye")
    exitProcess(0)
}


//Helper functions
/**
 * readNextBoolean
 * helper function to read Boolean values from user input
 */
fun readBooleanFromUserInputOfClientPaymentStatus(): Boolean
{
    while (true)
    {
        val input = ScannerInput.readNextLine("Enter true or false to indicate whether the client has paid or not: ")
        if (input.equals("true", ignoreCase = true)) {
            return true
        } else if (input.equals("false", ignoreCase = true)) {
            return false
        } else
        {
            println("Invalid input. Please enter 'true' or 'false'.")
        }
    }
}


/**
 * readNextBoolean
 * helper function to read Boolean values from user input
 */
fun readBooleanFromUserInputOfAppointmentConfirmationStatus(): Boolean
{
    while (true)
    {
        val input = ScannerInput.readNextLine("Enter true or false to indicate whether the appointment is confirmed or not: ")
        if (input.equals("true", ignoreCase = true)) {
            return true
        } else if (input.equals("false", ignoreCase = true)) {
            return false
        } else
        {
            println("Invalid input. Please enter 'true' or 'false'.")
        }
    }
}



/**
 * helper function to ask user chooses paid client
 */
private fun askUserToChoosePaidClient(): Client?
{
    listAllPaidClients()
    if (clientAPI.numberOfPaidClients() > 0)
    {
        val client = clientAPI.findClientById(readNextInt("\nEnter the client's id: "))
        if (client != null) {
            if (!client.hasPaid)
            {
                println("Client has not paid")
            } else
            {
                return client //chosen note is active
            }
        } else {
            println("Client id is not valid")
        }
    }
    return null
}

/**
 * askUserToChooseAppointment()
 * helper function to ask users to choose appointments
 */
private fun askUserToChooseAppointment(Client: Client): Appointment?
{
    if (Client.numberOfAppointments() > 0) {
        print(Client.listAppointments())
        return Client.findAppointmentById(readNextInt("\nEnter the id of the item: "))
    } else {
        println("No items for chosen note")
        return null
    }
}



