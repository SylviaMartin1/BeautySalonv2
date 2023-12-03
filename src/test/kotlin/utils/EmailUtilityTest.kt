import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import utils.EmailUtility.isEmailValid

class EmailValidatorTest {

    @Test
    fun `valid email address should return true`() {
        val emailAddress = "sylviamartin@gmail.com"
        val isValid = isEmailValid(emailAddress)
        assertTrue(isValid)
    }

    @Test
    fun `invalid email address should return false`() {
        val invalidEmailAddress = "sylvia_gmail.com" //no @
        val isValid = isEmailValid(invalidEmailAddress)
        assertFalse(isValid)
    }
    }
