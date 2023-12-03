package utils

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
}

