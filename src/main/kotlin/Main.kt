//1. Import Statements
import controllers.ClientAPI
import models.Appointment
import models.Client
import persistence.JSONSerializer
import utils.ScannerInput
import utils.ScannerInput.readNextDouble
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import utils.ValidateInput.readValidPhone
import utils.ValidateInput.readValidTreatment
import java.io.File
import kotlin.system.exitProcess


//2. Variables
/**
 * holds clientAPI class as an object
 */
//private val noteAPI = NoteAPI(XMLSerializer(File("notes.xml")))
private val clientAPI = ClientAPI(JSONSerializer(File("notes.json")))


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
 * ANSI Escape Sequences
 * Underline - Note Keeper App, Client Menu, Search Menu For Clients, Appointments Menu, Search Menu for Appointments
 * Under
 */

//Variables to improve UI of Menu
/**
 * ANSI Escape Sequences
 * reset
 * underline
 * bold
 * light blue
 * pink
 * purple
 */

val reset = "\u001B[0m"  //reset
val underline = "\u001B[4m" //underline
val bold = "\u001B[1m" //bold
val lightBlue = "\u001B[34m" //light blue
val pink = "\u001B[38;5;206m" //pink
val purple = "\u001B[38;5;164m" //purple
val red = "\u001B[31m"

 /** Unicode Escape Sequences
 * Some emojis created using emojipedia.com
 */
val plus = "\u2795" //plus emoji
val questionMark = "\u2753" //question mark emoji
val clipboard = "\uD83D\uDCCB" //clipboard emoji
val numbers = "\uD83D\uDD22" //1234 emoji
val pencil = "\u270F" //pencil emoji
val minus = "\u2796"//minus emoji
val broom = "\uD83E\uDDF9" //broom emoji
val search = "\uD83D\uDD0D" //magnifying glass emoji
val fl = "\uD83D\uDCAE" //floppy disk emoji
val clo = "\uD83D\uDD03" //clockwise emoji
val exit = "\uD83D\uDEAA" //door emoji

fun mainMenu() = ScannerInput.readNextInt(
    """ 
     >$bold $purple ----------------------------------------------------- $reset 
       > $bold $purple            $underline 💅 NOTE KEEPER APP 💅$reset                  
        > $bold $purple-----------------------------------------------------  $reset
       >$lightBlue |$underline $bold  CLIENT MENU $reset            
        > $lightBlue| 1) Add a client $plus ➕
         > | 2) List clients $clipboard
         > | 3) Update a client $pencil ✏️
         > | 4) Delete a client $minus ➖
         > | 5) Clear all clients from the system $broom 🧹
         > | 6) Check if there are clients in the system $questionMark ❓                                                 
         > ----------------------------------------------------- 
         > | $underline $bold $lightBlue SEARCH MENU FOR CLIENTS $reset     
      > $lightBlue| 7) Search for a client by their Id
         > | 8)  Search for a client by their first name $search 🔎
         > | 9)  Search for a client by their last name $search 🔎
         > | 10) Search for a client by their street $search 🔎
         > | 11) Search for a client by their county $search 🔎
         > | 12) Search for a client by their email $search 🔎
         > | 13) Search for a client by their phone number $search 🔎
         > | 14) Search for a client by their allergy $reset 🔎                                                 
         > -----------------------------------------------------
         > $pink|$underline $bold APPOINTMENT MENU $reset                                         
         > $pink| 15) Add an appointment $plus ➕
         > | 16) List confirmed appointments $clipboard     
         > | 17) Update an appointment $pencil ✏️
         > | 18) Delete an appointment $minus ➖
         > -----------------------------------------------------  
         > $pink|  $underline $bold SEARCH MENU FOR APPOINTMENTS $reset  
         > $pink| 19) Search for an appointment by its Id $search 🔎
         > | 20) Search for an appointment by its time $search 🔎
         > | 21) Search for an appointment by its date $search 🔎
         > | 22) Search for an appointment by its treatments $search 🔎
         > | 23) Search for an appointment by its cost $search 🔎
         > | 24) Search for an appointment by its rating $search  🔎                                             
         > ----------------------------------------------------- $reset
         > $red| 0) Exit    $exit 🚪                                    
         > ----------------------------------------------------- $reset
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
    val phone = readValidPhone("Enter the client's phone number: ")
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
                treatment = readValidTreatment("\t Appointment Services: "),
                cost = readNextInt("\t Appointment Cost:"),
                isConfirmed = isConfirmed,
                rating = readNextInt("\t Appointment rating:")
            )))
            println("Add Successful!")
        else println("Add NOT Successful")
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
                  > | $lightBlue 1) View ALL Clients $clipboard 📋 $reset       
                  > |  ${lightBlue}2) View ALL PAID Clients $clipboard 📋
                  > |  3) View ALL UNPAID Clients $clipboard 📋
                  > |  4) List the NUMBER OF ALL Clients $numbers 🔢
                  > |  5) List the NUMBER OF ALL PAID Clients $numbers 🔢
                  > |  6) List the NUMBER OF ALL UNPAID Clients $numbers 🔢 $reset
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
            val phone = readValidPhone("Enter the client's phone number:  ")
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
 *function to update a client's appointment
 */
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
            val newTreatment = readValidTreatment("Enter the services that the new appointment will provide: ")
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

//Persistence Functions
/**
 * 36. save()
 * XML and JSO
 * Method to save notes in persistent storage file
 */
fun save()
{
    try {
        clientAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

/**
 * load()
 * JSON and XML
 * method to load notes to persistent storage file
 */
fun load()
{
    try {
        clientAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}



