//1. Packages
/**
 * Groups related data structures
 */
package models

//2. Classes
/**
 * Represents single appointment data classes in the application
 */
data class Client(var ClientId: Int = 0,
                  var firstName: Int,
                  var lastName: String,
                  var street: String,
                  var county: String,
                  var email: String,
                  var phone: Int,
                  var allergy: String,
                  var hasPaid: Boolean)
