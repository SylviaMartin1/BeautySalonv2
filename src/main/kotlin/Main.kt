//1. Import Statements
import controllers.ClientAPI
import models.Client
import utils.ScannerInput

//2. Variables
private val clientAPI = ClientAPI()

//3. Methods
/**
 * 1. main()
 * Starting point of the program
 * Where the program starts running
 * Calls runMenu()
 */
fun main()
{
    runMenu()

}

/**
 * 2. mainMenu()
 * Displays menu
 * Allows user input
 */
fun mainMenu() : Int
{
    return ScannerInput.readNextInt(""" 
        > ----------------------------------
        > |     BEAUTY SALON APP
        > |----------------------------------
        > |     CLIENT MENU
        > | 1)  Add a client    
        > |----------------------------------
        > |     APPOINTMENT MENU
        > | 1)  Add an appointment
        > |----------------------------------
        > ==>> """.trimMargin(">"))
}
          


/**
 * 3. runMenu()
 * Loops mainMenu()
 * Processes user's choice
 */
fun runMenu()
{


}

/**
 * 4. addClient()
 * Method to add a note
 **/
fun addClient() {
    val clientId = ScannerInput.readNextInt("Enter the client's Id: ")
    val firstName = ScannerInput.readNextLine("Enter the client's first name: ")
    val lastName = ScannerInput.readNextLine("Enter the client's last name: ")
    val street = ScannerInput.readNextLine("Enter the client's street: ")
    val county = ScannerInput.readNextLine("Enter the client's county: ")
    val email = ScannerInput.readNextLine("Enter the client's email address: ")
    val phone = ScannerInput.readNextInt("Enter the client's phone number: ")
    val allergy = ScannerInput.readNextLine("Enter the client's allergies: ")
    val isAdded = clientAPI.addClient(Client(clientId = clientId, firstName = firstName, lastName = lastName, street = street, county = county, email = email, phone = phone, allergy = allergy))
    if (isAdded) {
        println("Client $clientId Added Successfully")
    } else {
        println("Add Failed")
    }
}

