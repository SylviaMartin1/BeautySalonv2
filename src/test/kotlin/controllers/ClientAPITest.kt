// 1. Packages
package controllers

import models.Appointment
import models.Client
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import persistence.XMLSerializer
import java.io.File



// 2. Import Statements

// 3. Class
class ClientAPITest {
    /**
     * Variables of the type 'Client' which contain no data
     */
    private var joan: Client? = null
    private var toni: Client? = null
    private var maya: Client? = null
    private var lynn: Client? = null
    private var maureen: Client? = null
    private var populatedClients: ClientAPI? = ClientAPI(XMLSerializer(File("notes.xml")))
    private var emptyClients: ClientAPI? = ClientAPI(XMLSerializer(File("notes.xml")))

    /**
     * @BeforeEach annotation
     * setup()
     * Initialises environment before testing
     */
    @BeforeEach
    fun setup() {
        val appointmentOne = Appointment(0, 10.00, "15/12/2023", "Manicure", 25, 4, true)
        val appointmentTwo = Appointment(1, 11.00, "15/12/2023", "Pedicure", 30, 5, true)
        val appointmentThree = Appointment(2, 13.00, "15/12/2023", "Facial", 35, 3, false)
        val appointmentFour = Appointment(3, 14.00, "15/12/2023", "Waxing", 40, 2, true)
        val appointmentFive = Appointment(4, 15.00, "15/12/2023", "Massage", 45, 5, true)

        joan = Client(
            0,
            "Joan",
            "Jacob",
            "Red Street",
            "Waterford",
            "Joan@gmail.com",
            123456789,
            "None",
            true,
            mutableSetOf(appointmentOne)
        )
        toni = Client(
            1,
            "Toni",
            "Terry",
            "Orange Street",
            "Cork",
            "Toni@gmail.com",
            112345678,
            "None",
            true,
            mutableSetOf(appointmentTwo)
        )
        maya = Client(
            2,
            "Maya",
            "Matthew",
            "Yellow Street",
            "Dublin",
            "Maya@gmail.com",
            111234567,
            "Sulfates",
            true,
            mutableSetOf(appointmentThree)
        )
        lynn = Client(
            3,
            "Lynn",
            "Liam",
            "Green Street",
            "Carlow",
            "Lynn@gmail.com",
            111123456,
            "None",
            false,
            mutableSetOf(appointmentFour)
        )
        maureen = Client(
            4,
            "Maureen",
            "Matthews",
            "Blue Street",
            "Wexford",
            "Maureen@gmail.com",
            1111112345,
            "None",
            false,
            mutableSetOf(appointmentFive)
        )

        // adding 5 Clients to the client api
        populatedClients!!.addClient(joan!!)
        populatedClients!!.addClient(toni!!)
        populatedClients!!.addClient(maya!!)
        populatedClients!!.addClient(lynn!!)
        populatedClients!!.addClient(maureen!!)
    }

    /**
     * @AfterEach annotation
     * setup()
     * Cleans up environment after testing
     */
    @AfterEach
    fun tearDown() {
        joan = null
        toni = null
        maya = null
        lynn = null
        maureen = null
        populatedClients = null
        emptyClients = null
    }

    // Nested Class where notes are added to the ArrayList
    @Nested
    inner class AddClient {
        // Add Tests
        /**
         * Tests if adding a client to the ArrayList increases the number of clients in the ArrayList
         * Tests if the client is added to the end of the ArrayList
         */
        @Test
        fun `adding a Client to a populated list adds to ArrayList`() {
            val newClient =
                Client(6, "William", "Jacob", "Red Street", "Waterford", "William@gmail.com", 123456789, "None", true)
            val initialNumberOfClients = populatedClients!!.numberOfClients()

            assertEquals(initialNumberOfClients, populatedClients!!.numberOfClients())
            assertTrue(populatedClients!!.addClient(newClient))
            assertEquals(initialNumberOfClients + 1, populatedClients!!.numberOfClients())
            assertEquals(newClient, populatedClients!!.findClient(populatedClients!!.numberOfClients() - 1))
        }

        /**
         * Verifies that adding a client to the ArrayList if its empty increases the number of items in the ArrayList to 1
         * Tests if the single client is added to the ArrayList
         */
        @Test
        fun `adding a Note to an empty list adds to ArrayList`() {
            val newClient =
                Client(6, "William", "Jacob", "Red Street", "Waterford", "William@gmail.com", 123456789, "None", true)
            assertEquals(0, emptyClients!!.numberOfClients())
            assertTrue(emptyClients!!.addClient(newClient))
            assertEquals(1, emptyClients!!.numberOfClients())
            assertEquals(newClient, emptyClients!!.findClient(emptyClients!!.numberOfClients() - 1))
        }
    }

    // Nested Class where clients in the ArrayList are listed
    @Nested
    inner class ListClients {
        // List all clients
        /**
         * Verifies that if the ArrayList is not empty, calling 'ListAllClients' lists all clients stored in the ArrayList
         */
        @Test
        fun `listAllClients returns Clients when ArrayList has clients stored`() {
           assertEquals(5, populatedClients!!.numberOfClients())
            val notesString = populatedClients!!.listAllClients().lowercase()
            assertTrue(notesString.contains("joan"))
            assertTrue(notesString.contains("toni"))
            assertTrue(notesString.contains("maya"))
            assertTrue(notesString.contains("lynn"))
            assertTrue(notesString.contains("maureen"))
        }

        /**
         * Verifies that if the ArrayList is empty, calling 'ListAllClients' returns the message "no clients"
         */
        @Test
        fun `listAllClients returns No Clients Stored message when ArrayList is empty`() {
            assertEquals(0, emptyClients!!.numberOfClients())
            assertTrue(emptyClients!!.listAllClients().lowercase().contains("no clients"))
        }

        // List paid clients
        /**
         * Verifies that if the ArrayList is not empty, calling 'ListPaidClients' lists all Paid Clients
         */
        @Test
        fun `listPaidClients returns paid clients when ArrayList has paid clients stored`() {
           assertEquals(3, populatedClients!!.numberOfPaidClients())
            val activeClientsString = populatedClients!!.listPaidClients().lowercase()
            assertTrue(activeClientsString.contains("joan"))
            assertTrue(activeClientsString.contains("toni"))
            assertTrue(activeClientsString.contains("maya"))
            assertFalse(activeClientsString.contains("lynn"))
            assertFalse(activeClientsString.contains("maureen"))
        }

        /**
         * Verifies that if the ArrayList is empty, calling 'ListPaidClients' returns the message no paid clients
         */
        @Test
        fun `listPaidClients returns no paid clients stored when ArrayList is empty`() {
           assertEquals(0, emptyClients!!.numberOfPaidClients())
            assertTrue(emptyClients!!.listPaidClients().lowercase().contains("no paid clients"))
        }

        // List paid clients
        /**
         * Verifies that if the ArrayList is not empty, calling 'ListUnPaidClients' lists all unPaid Clients
         */
        @Test
        fun `listUnPaidClients returns paid clients when ArrayList has unpaid clients stored`() {
            assertEquals(2, populatedClients!!.numberOfUnPaidClients())
            val activeClientsString = populatedClients!!.listUnpaidClients().lowercase()
            assertFalse(activeClientsString.contains("joan"))
            assertFalse(activeClientsString.contains("toni"))
            assertFalse(activeClientsString.contains("maya"))
            assertTrue(activeClientsString.contains("lynn"))
            assertTrue(activeClientsString.contains("maureen"))
        }

        /**
         * Verifies that if the ArrayList is empty, calling 'ListPaidClients' returns the message no paid clients
         */
        @Test
        fun `listUnPaidClients returns no paid clients stored when ArrayList is empty`() {
            assertEquals(0, emptyClients!!.numberOfUnPaidClients())
            assertTrue(emptyClients!!.listUnpaidClients().lowercase().contains("no unpaid clients"))
        }

        /**
         * Verifies that the listConfirmedAppointments() function is working
         */
        @Test
        fun `listConfirmedAppointments returns correct appointments`() {
            val expected =
                "Joan Jacob: 15/12/2023Manicure\nToni Terry: 15/12/2023Pedicure\nLynn Liam: 15/12/2023Waxing\nMaureen Matthews: 15/12/2023Massage\n"

            val actual = populatedClients!!.listConfirmedAppointments()

            assertEquals(expected, actual)
        }
    }

    // Nested Class where clients in the ArrayList are counted
    @Nested
    inner class CountClients {
        /**
         * verifies that the number of clients is calculated correctly
         */
        @Test
        fun numberOfClientsCalculatedCorrectly() {
           assertEquals(5, populatedClients!!.numberOfClients())
            assertEquals(0, emptyClients!!.numberOfClients())
        }

        /**
         * verifies that the number of paid clients is calculated correctly
         */
        @Test
        fun numberOfPaidClientsCalculatedCorrectly() {
           assertEquals(3, populatedClients!!.numberOfPaidClients())
            assertEquals(0, emptyClients!!.numberOfPaidClients())
        }

        /**
         * verifies that the number of unpaid clients is calculated correctly
         */
        @Test
        fun numberOfUnPaidClientsCalculatedCorrectly() {
            assertEquals(2, populatedClients!!.numberOfUnPaidClients())
           assertEquals(0, emptyClients!!.numberOfUnPaidClients())
        }

        /**
         * Verifies that listNumberOfConfirmedAppointments works properly
         */
        @Test
        fun `listNumberOfConfirmedAppointments returns correct count of confirmed appointments`() {
            val confirmedAppointmentsCount = populatedClients!!.listNumberOfConfirmedAppointments()
            val expectedCount = 4
            assertEquals(expectedCount, confirmedAppointmentsCount)
        }
    }

    // Nested Class where notes in the arrayList are updated
    @Nested
    inner class UpdateNotes {
        /**
         * verifies that if you update a note that doesn't exist, false is returned
         */
        @Test
        fun `updating a client that does not exist returns false`() {
            assertFalse(
                populatedClients!!.updateClient(
                    7,
                    Client(
                        2,
                        "Bertha",
                        "John",
                        "Arbour Street",
                        "Donegal",
                        "bertha@gmail.com",
                        202233459,
                        "None",
                        false
                    )
                )
            )
        }
        /**
         * verifies that if
         */
    }

    // Nested Class where notes are deleted from the arrayList
    @Nested
    inner class DeleteClients {
        /**
         * Verifies that if you delete a client that does not exist, null is returned
         */
        @Test
        fun `deleting a Note that does not exist, returns null`() {
            assertFalse(emptyClients!!.deleteClient(0))
            assertFalse(populatedClients!!.deleteClient(-1))
            assertFalse(populatedClients!!.deleteClient(8))
        }

        /**
         * verifies that clearAllClients() works
         */
        @Test
        fun `clearAllClients should remove all clients`() {
            val initialSize = populatedClients!!.numberOfClients()

            populatedClients!!.clearAllClients()

            val finalSize = populatedClients!!.numberOfClients()

            assertEquals(0, finalSize)
            assertEquals(
                initialSize,
                finalSize + 5
            ) // Check if the size reduced by the number of clients initially added
        }
    }

    // Nested class to search clients
    @Nested
    inner class SearchClients {

        /**
         * to ensure findClientById() is correct
         */
        @Test
        fun `findClientById should return correct client when ID exists`() {
            val foundClient = populatedClients!!.findClientById(1) // Searching for client with ID 1

            assertEquals("Toni", foundClient?.firstName)
            assertEquals("Terry", foundClient?.lastName)
            assertEquals("Orange Street", foundClient?.street)
            assertEquals("Cork", foundClient?.county)
            assertEquals("Toni@gmail.com", foundClient?.email)
            assertEquals(112345678, foundClient?.phone)
        }

        @Test
        fun `findClientById should return null when ID doesn't exist`() {
            val foundClient = populatedClients!!.findClientById(10) // Searching for a non-existent client with ID 10

            assertEquals(null, foundClient)
        }
    }

    /**
     * Verifies that if notes are searched for by first name, no clients are returned when no clients with that first name exist
     */
    @Test
    fun `search clients by first name returns no clients when no clients with that first name exist`() {
        // searching a populated collection for a title that doesn't exist
       assertEquals(5, populatedClients!!.numberOfClients())
        val searchResults = populatedClients!!.searchClientByFirstName("no results expected")
        assertTrue(searchResults.isEmpty())

        // searching an empty collection
        assertEquals(0, emptyClients!!.numberOfClients())
        assertTrue(emptyClients!!.searchClientByFirstName("").isEmpty())
    }

    /**
     * Verifies that if clients are searched for by first name, clients are returned when clients with that first name exist
     */
    @Test
    fun `search clients by first name returns clients when clients with that first name exist`() {
        assertEquals(5, populatedClients!!.numberOfClients())
        val searchResults = populatedClients!!.searchClientByFirstName("Joan")
        assertTrue(searchResults.contains("Joan"))
        assertFalse(searchResults.contains("Toni"))
    }

    /**
     * Verifies that if clients are searched for by last name, clients are returned when clients with that last name exist
     */
    @Test
    fun `search clients by last name returns clients when clients with that last name exist`() {
        assertEquals(5, populatedClients!!.numberOfClients())
        val searchResults = populatedClients!!.searchClientByLastName("Jacob")
        assertTrue(searchResults.contains("Jacob"))
        assertFalse(searchResults.contains("Terry"))
    }

    /**
     * Verifies that if clients are searched for by street, clients are returned when clients with that street exist
     */
    @Test
    fun `search clients by street returns clients when clients with that street exist`() {
        assertEquals(5, populatedClients!!.numberOfClients())
        val searchResults = populatedClients!!.searchClientByStreet("Red Street")
        assertTrue(searchResults.contains("Red Street"))
        assertFalse(searchResults.contains("Orange Street"))
    }

    /**
     * Verifies that if clients are searched for by county, clients are returned when clients with that county exist
     */
    @Test
    fun `search clients by county returns clients when clients with that county exist`() {
       assertEquals(5, populatedClients!!.numberOfClients())
        val searchResults = populatedClients!!.searchClientByCounty("Waterford")
        assertTrue(searchResults.contains("Waterford"))
        assertFalse(searchResults.contains("Cork"))
    }

    /**
     * Verifies that if clients are searched for by email, clients are returned when clients with that email exist
     */
    @Test
    fun `search clients by email returns clients when clients with that email exist`() {
        assertEquals(5, populatedClients!!.numberOfClients())
        val searchResults = populatedClients!!.searchClientByEmail("Joan@gmail.com")
        assertTrue(searchResults.contains("Joan@gmail.com"))
        assertFalse(searchResults.contains("Toni@gmail.com"))
    }

    /**
     * Verifies that if clients are searched for by allergy, clients are returned when clients with that allergy exist
     */
    @Test
    fun `search clients by allergy returns clients when clients with that allergy exist`() {
       assertEquals(5, populatedClients!!.numberOfClients())
        val searchResults = populatedClients!!.searchClientByAllergy("None")
        assertTrue(searchResults.contains("None"))
        assertFalse(searchResults.contains("Sulfates"))
    }
}
