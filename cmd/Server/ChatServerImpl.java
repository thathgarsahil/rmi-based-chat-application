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

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
	private Map<String, ChatClient> clientMap = new HashMap<>();
	
	public ChatServerImpl() throws RemoteException {
	}	

	public String[] login(ChatClient client) throws RemoteException {
		String name =client.getName();
		if (clientMap.containsKey(name)){
			throw new RuntimeException("Name already in use.");
		}
		String[] clientNames = list();
		clientMap.put(name, client);
		for (String clientName : clientNames) {
			clientMap.get(clientName).joined(name);
		}
		return clientNames;
	}

	public String[] list() {
		return clientMap.keySet().toArray(new String[clientMap.size()]);
	}

	public void sendMessage(String from, String to, String message) throws RemoteException {
		clientMap.get(to).showMessage(from, message);
	}

	public void sendMessage(String from, String message) throws RemoteException {
		String[] clientNames = list();
		for (String clientName : clientNames) {
			sendMessage(from, clientName, message);
		}
	}

	public void logout(String name) throws RemoteException {
		clientMap.remove(name);
		String[] clientNames = list();
		for (String clientName : clientNames) {
			clientMap.get(clientName).left(name);
		}
	}

}
