package persistence

/**
 * 1. Imports XStream and XML library
 */
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver
import models.Appointment
import models.Client
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class XMLSerializer(private val file: File) : Serializer
{
    /**
     * 2. Used for reading objects previously serialized and stored in a file
     * Specifies that function may throw an exception - function may encounter an error or unexpected condition
     * that would disrupt its normal execution
     */
    @Throws(Exception::class)
    override fun read(): Any {
        val xStream = XStream(DomDriver())
        xStream.allowTypes(arrayOf(Client::class.java))
        xStream.allowTypes(arrayOf(Appointment::class.java))
        val inputStream = xStream.createObjectInputStream(FileReader(file))
        val obj = inputStream.readObject() as Any
        inputStream.close()
        return obj
    }

    /**
     * 3. Used to serialize and save an object to the specified file
     * Specifies that function may throw an exception - function may encounter an error or unexpected condition
     * that would disrupt its normal execution
     */
    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val xStream = XStream(DomDriver())
        val outputStream = xStream.createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }


}
