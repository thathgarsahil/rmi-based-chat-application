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

public class ChatClientGUIImpl extends UnicastRemoteObject implements ChatClient {
	private String name;
	private List clientList;
	private TextArea chatArea;

	public ChatClientGUIImpl(String n, List l, TextArea ta) throws RemoteException {
		this.name = n;
		this.clientList = l;
		this.chatArea = ta;
	}
	
	public String getName() {
		return this.name;
	}

	public void joined(String name) {
		this.clientList.add(name);
	}
	
	public void left(String name) {
		this.clientList.remove(name);
	}
	
	public void showMessage(String from, String message) {
		this.chatArea.append("Message from : " + from + " : " + message + "\r\n");
	}
}
