package utils

import java.util.*

object ValidateInput
{
    /**
     * Repeatedly prompts the user for a valid phone input and returns the valid phone
     */
    @JvmStatic
    fun readValidPhone(prompt: String?): Int {
        var input = ScannerInput.readNextInt(prompt)
        do {
            if (Utilities.validRange(input, 1 ,10))
                return input
            else {
                print("Invalid phone number $input.")
                input = ScannerInput.readNextInt(prompt)
            }
        } while (true)
    }

    /**
     * Repeatedly prompts the user for a valid time input and returns the valid time
     */
    @JvmStatic
    fun readValidTime(prompt: String?): Double
    {
        var input = ScannerInput.readNextDouble(prompt)
        do {
            if (Utilities.validRangeDouble(input, 0.00 ,23.00))
                return input
            else {
                print("Invalid time $input. Time should be in the range 0.00 t0 23.00")
                input = ScannerInput.readNextDouble(prompt)
            }
        } while (true)
    }


    /**
     * Repeatedly prompts the user for a valid treatment input and returns the valid category when provided
     */
    @JvmStatic
    fun readValidTreatment(prompt: String?): String
    {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (TreatmentUtility.isValidTreatment(input))
                return input
            else {
                print("Invalid category $input.  Please try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }


    /**
     * Repeatedly prompts the user for a valid email input and returns the valid email when provided
     */
    @JvmStatic
    fun readValidEmail(prompt: String?): String
    {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (EmailUtility.isEmailValid(input))
                return input
            else {
                print("Invalid email $input. ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }
}


