//1. Packages
/**
 * Groups related classes which process user input
 */
package controllers

import models.Client
import java.util.ArrayList


class ClientAPI
{
    //2. Variables
    /**
     * ArrayList to store list of client objects
     */
    private var clients = ArrayList<Client>()

    //3. Functions to manage client arrayList
    //Create functions
    /**
     * 1. addClient()
     * Function to add a client
     */
    fun addClient(client: Client): Boolean
    {
        client.clientId = getClientId()
        return clients.add(client)
    }

    //Helper functions
    /**
     * 1.getClientId()
     * Helper function to get a client's ID
     */
    private var lastId = 0
    private fun getClientId() = lastId++



 }