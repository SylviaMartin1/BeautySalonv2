package utils

object EmailUtility
{
    fun isEmailValid(emailAddress: String): Boolean
    {
        val emailRegex = Regex("^[A-Za-z0-9._] +@ [A-Za-z0-9.]+\\.[A-Z|a-z] {2,}\$")
        //sylvia_martin @ gmail . com
        //1. Username can contain: A-Z, a-z, 0-9, dot, underscore
        //2. @  + Domain name can contain: A-Z, a-z, 0-9, dot
        //3. //. + Email website name can contain: A-Z, a-z,
        // {2} = minimum length of two characters
        return emailAddress.matches(emailRegex)
    }
}