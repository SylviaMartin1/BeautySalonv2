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
    //3. Functions to manage appointments set



}

