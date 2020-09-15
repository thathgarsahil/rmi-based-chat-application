/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author HP
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Client.*;
import java.util.Map;
import java.util.HashMap;

//ChatClient objects to be available to the Implementation. Whenever new user login, to have access to methods.
//UnicastRemoteObject for the objects.
public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    
	private Map<String, ChatClient> clientMap = new HashMap<>();
            //Chat Client objects stored in a Map, Namewise.
            //Map of Name and ChatClient Object
        
	public ChatServerImpl() throws RemoteException {
	}   //For any remote implementation, we need public constructor for such an item.
            

	public String[] login(ChatClient client) throws RemoteException {
		String name =client.getName();          //Fetches the name of Object
		if (clientMap.containsKey(name)){
			throw new RuntimeException("Name already in use.");
		}                                       //Verifies, if any other user is having same name
		String[] clientNames = list();          //Fetches all the clientnames from the list, into the array
		clientMap.put(name, client);            //Put the new name & object into Map Object
		for (String clientName : clientNames) {
			clientMap.get(clientName).joined(name);
		}                                       //Call the function joined, for all the objects present in the map.
		return clientNames;                     //New User gets the List of Users.
	}

	public String[] list() {
		return clientMap.keySet().toArray(new String[clientMap.size()]);
	}
            //KeySet() returns set of keys, coverted into array using toArray
            //Parameter of Array of String, and size to be object size.

	public void sendMessage(String from, String to, String message) throws RemoteException {
		clientMap.get(to).showMessage(from, message);
	}       //Find destinations and invokes the showMessage for that user.

	public void sendMessage(String from, String message) throws RemoteException {
		String[] clientNames = list();              //fetches entire list of client
		for (String clientName : clientNames) {
			sendMessage(from, clientName, message);
		}       //Each of the client is communicated, by the sendMessage 
	}

	public void logout(String name) throws RemoteException {
		clientMap.remove(name);             //Removes the Client Name from the List
		String[] clientNames = list();      //Fetches the list of clients into an array
		for (String clientName : clientNames) {
			clientMap.get(clientName).left(name);
		}       //For all the users, left method is invoked
	}

}
