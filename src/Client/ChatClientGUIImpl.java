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
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.awt.List;
import java.awt.TextArea;

//Implementation to ChatClient to let the server interact with user, access those methods
public class ChatClientGUIImpl extends UnicastRemoteObject implements ChatClient {
	private String name;
	private List clientList;
	private TextArea chatArea;

	public ChatClientGUIImpl(String n, List l, TextArea ta) throws RemoteException {
		this.name = n;
		this.clientList = l;
		this.chatArea = ta;
	}       //Constructor to intialize the variables
	
	public String getName() {
		return this.name;
	}       //returns name of user

	public void joined(String name) {
		this.clientList.add(name);
	}       //Add name to List
	
	public void left(String name) {
		this.clientList.remove(name);
	}       //Remove name from List
	
	public void showMessage(String from, String message) {
		this.chatArea.append("Message from : " + from + " : " + message + "\r\n");
	}       //Append message to the textArea
}
