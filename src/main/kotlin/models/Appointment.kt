//1. Packages
/**
 * Groups related data structures
 */
package models

//2. Classes
/**
 * Represents single appointment data classes in the application
 */
data class Appointment(var appointmentId: Int = 0,
                       var time: Int,
                       var date: String,
                       var treatment: String,
                       var cost: Int,
                       var rating: Int,
                       var isConfirmed: Boolean)