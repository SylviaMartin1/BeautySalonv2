//1. Packages
/**
 * Groups related data structures
 */
package models

//2. Classes
/**
 * Groups all client data models in the application
 */
data class Client(
    var clientId: Int,
    var firstName: String,
    var lastName: String,
    var street: String,
    var county: String,
    var email: String,
    var phone: Int,
    var allergy: String, // -- list of Strings
    var appointments : MutableSet<Appointment> = mutableSetOf())
{
    //3. Functions to manipulate the set of Appointments
    //Create Functions
    /**
     * 1. Add an appointment
     */
    fun addAppointment(appointment: Appointment) : Boolean
    {
        appointment.appointmentId = getAppointmentId()
        return appointments.add(appointment)
    }


    //Read Functions
    /**
     * 2. List all appointments
     */
    fun listAppointments() =
        if (appointments.isEmpty())  "\tNO APPOINTMENTS ADDED"
        else  Utilities.formatSetString(appointments)



    //Count Functions
    /**
     * 3. Count the number of appointments
     */
    fun numberOfAppointments() = appointments.size



    //Update Functions
    /**
     * 4. Update an appointment
     */
    fun update(id: Int, newItem : Appointment): Boolean
    {
        val foundItem = findAppointmentById(id)

        //if the object exists, use the details passed in the newAppointment parameter to
        //update the found object in the Set
        if (foundItem != null)
        {
            foundItem.time = newItem.time
            foundItem.date = newItem.date
            foundItem.treatment = newItem.treatment
            foundItem.cost = newItem.cost
            foundItem.isPaid = newItem.isPaid
            foundItem.rating = newItem.rating
            return true
        }
        //if the object was not found, return false, indicating that the update was not successful
        return false
    }


    //Delete Functions
    /**
     * 5. Delete an appointment
     */
    fun deleteAppointment(id: Int): Boolean
    {
        return appointments.removeIf { appointment -> appointment.appointmentId == id}
    }


    //Find Functions
    /**
     * 6. Find an appointment by their Id
     */
    fun findAppointmentById(id: Int): Appointment?
    {
        return appointments.find{ appointment -> appointment.appointmentId == id }
    }


    //Check Functions
    /**
     * 7. Check an appointment's payment status
     */
    fun checkClientPaymentStatus(): Boolean
    {
        if (appointments.isNotEmpty()) {
            for (appointment in appointments) {
                if (!appointment.isPaid) {
                    return false
                }
            }
        }
        return true
    }


    //Helper Functions
    /**
     * 1. Helper function to get an appointment's id
     */
    private var lastAppointmentId = 0
    private fun getAppointmentId() = lastAppointmentId++

    /**
     * 2. Helper function to write appointment variables as strings
     */
    override fun toString(): String {
        return "$firstName $lastName: Street($street), County($county), Email($email), Phone($allergy) \n${listAppointments()}"
    }


}



