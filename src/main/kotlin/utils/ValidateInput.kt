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
}

