//1. Packages
package controllers

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import persistence.XMLSerializer
import java.io.File
import models.Client

//2. Import Statements

//3. Class
/**
 * Variables of the type 'Client' which contain no data
 */
private var Joan: Client? = null
private var Toni: Client? = null
private var Maya: Client? = null
private var Lynn: Client? = null
private var Maureen: Client? = null
private var populatedClients: ClientAPI? = ClientAPI(XMLSerializer(File("notes.xml")))
private var emptyClients: ClientAPI? = ClientAPI(XMLSerializer(File("notes.xml")))


/**
 * @BeforeEach annotation
 * setup()
 * Initialises environment before testing
 */
@BeforeEach
fun setup()
{
    Joan = Client(0, "Joan", "Jacob", "Red Street", "Waterford", "Joan@gmail.com", 123456789, "None" , true)
    Toni = Client(1, "Toni", "Terry", "Orange Street", "Cork", "Toni@gmail.com", 112345678 , "None", true)
    Maya = Client(2, "Maya", "Matthew", "Yellow Street", "Dublin", "Maya@gmail.com", 111234567, "Sulfates" , true)
    Lynn = Client(3, "Lynn", "Liam", "Green Street", "Carlow", "Lynn@gmail.com", 111123456, "None" , false)
    Maureen = Client(4, "Maureen", "Matthews", "Blue Street", "Wexford", "Maureen@gmail.com",1111112345, "None" , false)


    //adding 5 Clients to the client api
    populatedClients!!.addClient(Joan!!)
    populatedClients!!.addClient(Toni!!)
    populatedClients!!.addClient(Maya!!)
    populatedClients!!.addClient(Lynn!!)
    populatedClients!!.addClient(Maureen!!)
}

/**
 * @AfterEach annotation
 * setup()
 * Cleans up environment after testing
 */
@AfterEach
fun tearDown()
{
    Joan = null
    Toni = null
    Maya = null
    Lynn = null
    Maureen = null
    populatedClients = null
    emptyClients = null
}