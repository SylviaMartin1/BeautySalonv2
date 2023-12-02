//1. Import Statements
/**
 * 1. Imports clientApi class from controllers package
 * 2. Imports client data model from models package
 * 3. Imports scanner input class from utils package
 * 4. Imports readNextInt method from scanner input class in utils package
 * 5. Imports readNextLine method from scanner input class in utils package
 * 6. Imports function to exit the system
 */
import controllers.ClientAPI
import models.Client
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import kotlin.system.exitProcess


//2. Variables
private val clientAPI = ClientAPI()


//3. Methods
/**
 * 1. main()
 * Starting point of the program
 * Where the program starts running
 * Calls runMenu()
 */
fun main() = runMenu()

/**
 * 2. mainMenu()
 * Displays menu
 * Allows user input
 */
fun mainMenu() = readNextInt(""" 
        > ----------------------------------
        > |     BEAUTY SALON APP
        > |----------------------------------
        > |     CLIENT MENU
        > | 1)  Add a client    
        > |----------------------------------
        > |     APPOINTMENT MENU
        > | 1)  Add an appointment
        > |----------------------------------
        > | 0) Exit App
        > ==>> """.trimMargin(">"))

/**
 * 3. runMenu()
 * Loops mainMenu()
 * Processes user's choice
 */
fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> addClient()
            0 -> exitApp()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}


/**
 * 4. addClient()
 * Method to add a note
 **/
fun addClient() {
    val clientId = readNextInt("Enter the client's Id: ")
    val firstName = readNextLine("Enter the client's first name: ")
    val lastName = readNextLine("Enter the client's last name: ")
    val street = readNextLine("Enter the client's street: ")
    val county = readNextLine("Enter the client's county: ")
    val email = readNextLine("Enter the client's email address: ")
    val phone = readNextInt("Enter the client's phone number: ")
    val allergy = readNextLine("Enter the client's allergies: ")
    val isAdded = clientAPI.addClient(Client(clientId = clientId, firstName = firstName, lastName = lastName, street = street, county = county, email = email, phone = phone, allergy = allergy))
    if (isAdded) {
        println("Client $clientId Added Successfully")
    } else {
        println("Add Failed")
    }
}


/**
 * exitApp()
 * Function to exit the app
 */
fun exitApp() {
    println("Exiting...bye")
    exitProcess(0)
}
