package persistence

interface Serializer
{
    /**
     * 1. Used for writing an object to a form of storage e.g. file
     * Specifies that function may throw an exception - function may encounter an error or unexpected condition
     * that would disrupt its normal execution
     */
    @Throws(Exception::class)
    fun write(obj: Any?)

    /**
     * 2. Used for reading an object from a form of storage e.g. file
     * Specifies that function may throw an exception - function may encounter an error or unexpected condition
     * that would disrupt its normal execution
     */
    @Throws(Exception::class)
    fun read(): Any?
}