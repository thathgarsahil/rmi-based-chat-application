/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author HP
 */
import java.rmi.Remote;                     //Interface implements Remote (Remote Interface)
import java.rmi.RemoteException;            //All the methods in the Remote Interface have to throw RemoteException

//Interaction needed by the Server from the Client side.
public interface ChatClient extends Remote {
	String getName() throws RemoteException;
            //Client method to return name to the Server.
	void joined(String name) throws RemoteException;
            //Server conveys the name of the new client who has joined.
	void left(String name) throws RemoteException;
            //Server conveys the name of the client who has left.
	void showMessage(String from, String message) throws RemoteException;
            //Server shows the Sender name and Sender message.
}
