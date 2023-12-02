//1. Packages
/**
 * Groups related data structures
 */
package models

import utils.Utilities

//2. Classes
/**
 * Represents single appointment data classes in the application
 */
data class Client(var clientId: Int = 0,
                  var firstName: String,
                  var lastName: String,
                  var street: String,
                  var county: String,
                  var email: String,
                  var phone: Int,
                  var allergy: String,
                  var hasPaid: Boolean,
                  var appointments : MutableSet<Appointment> = mutableSetOf())
{
    //Create functions
    /**
     * addAppointment()
     * function to add an appointment to the mutable set
     */
    fun addAppointment(appointment: Appointment) : Boolean
    {
        appointment.appointmentId = getAppointmentId()
        return appointments.add(appointment)
    }


    //Read functions
    /**
     * numberOfAppointments()
     * function to count the number of appointments in the mutable set
     */
    fun numberOfAppointments() = appointments.size

    /**
     * listAppointments()
     * function to list all of the appointments in the mutable set
     */
    fun listAppointments() =
        if (appointments.isEmpty())  "\tNO APPOINTMENTS ADDED"
        else  Utilities.formatSetString(appointments)


    //Update functions
    /**
     * updateAppointment()
     * function to update an appointment
     */
    fun updateAppointment(id: Int, newAppointment: Appointment): Boolean
    {
        val foundAppointment = findAppointmentById(id)

        //if the object exists, use the details passed in the newAppointment parameter to
        //update the found object in the Set
        if (foundAppointment != null){
            foundAppointment.time = newAppointment.time
            foundAppointment.date = newAppointment.date
            foundAppointment.treatment = newAppointment.treatment
            foundAppointment.cost = newAppointment.cost
            foundAppointment.rating = newAppointment.rating
            return true
        }
        //if the object was not found, return false, indicating that the update was not successful
        return false
    }


    //Delete functions
    /**
     * deleteAppointment()
     * function to delete an appointment
     */
    fun deleteAppointment(id: Int): Boolean
    {
        return appointments.removeIf { appointment -> appointment.appointmentId == id}
    }


    //Search functions
    /**
     * findAppointmentById()
     * function to find an appointment by their id
     */
    fun findAppointmentById(id: Int): Appointment?
    {
        return appointments.find{ appointment -> appointment.appointmentId == id }
    }


    //Other functions
    /**
     * Function to check an appointment's confirmation status
     */
    fun checkAppointmentConfirmationStatus(): Boolean
    {
        if (appointments.isNotEmpty())
        {
            for (appointment in appointments)
            {
                if (!appointment.isConfirmed)
                {

                    return false
                }
            }
        }
        return true
    }


    //Helper functions
    /**
     * getAppointmentId()
     * function to get an appointment's id
     */
    private var lastAppointmentId = 0
    private fun getAppointmentId() = lastAppointmentId++


    /**
     * toString()
     * function to write client in certain form
     */
    override fun toString(): String {
        val hasPaid = if (hasPaid) "Yes" else "No"
        return "$clientId: $firstName $lastName, Street($street), County($county), Email($email), Phone($phone), Allergy($allergy), HasPaid($hasPaid) \n${listAppointments()}"
    }

}
