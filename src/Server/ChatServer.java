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
import java.rmi.Remote;                     //Interface implements Remote (Remote Interface)
import java.rmi.RemoteException;            //All the methods in the Remote Interface have to throw RemoteException
import Client.*;                            //Import, as ChatServer is dependent on ChatClient

//Interaction needed by the Client from the server side.
public interface ChatServer extends Remote {
	String[] login(ChatClient client) throws RemoteException;
            //Allows client to login into ChatServer. ChatClient is the Remote Object coming from the Client side.
	String[] list() throws RemoteException;
            //List of clients with whom user can chat.
	void sendMessage(String from, String to, String message) throws RemoteException;
            //Method with which user can send message to a particular user.
	void sendMessage(String from, String message) throws RemoteException;
            //Overload of sendMessage to send to all the users.
	void logout(String name) throws RemoteException;
            //The name of the client who wants to logout.
}
