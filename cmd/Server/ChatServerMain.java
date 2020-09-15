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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class ChatServerMain {
	public static void main(String[] args) throws RemoteException {
		String chatRoom = args[0];
		Registry r = LocateRegistry.createRegistry(1099);
		ChatServer server = new ChatServerImpl();
		r.rebind(chatRoom, server);
	}
}
