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
import Client.*;

public interface ChatServer extends Remote {
	String[] login(ChatClient client) throws RemoteException;
	String[] list() throws RemoteException;
	void sendMessage(String from, String to, String message) throws RemoteException;
	void sendMessage(String from, String message) throws RemoteException;
	void logout(String name) throws RemoteException;
}
