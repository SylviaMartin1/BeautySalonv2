//1. Packages
/**
 * Groups related data structures
 */
package models

//2. Classes
/**
 * Groups all appointment data models in the application
 */
data class Appointment(
    var time: Int,
    var date: String,
    var treatment: String,
    var cost: Int,
    var isPaid: Boolean,
    var rating: Int //in stars out of five -- categoryUtility
)

