package utils

object EmailUtility
{
    fun isEmailValid(emailAddress: String): Boolean
    {
        val emailRegex = Regex("^[A-Za-z0-9._]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}\$")
        return emailAddress.matches(emailRegex)
    }
}