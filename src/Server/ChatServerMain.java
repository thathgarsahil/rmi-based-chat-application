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
import java.rmi.registry.LocateRegistry;        //Not dependent on some user to start the server.
import java.rmi.registry.Registry;              //For Registry
import java.rmi.RemoteException;
import java.util.Scanner;

public class ChatServerMain {
	public static void main(String[] args) throws RemoteException {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter ChatRoom Name - ");
		String chatRoom = sc.nextLine();
		Registry r = LocateRegistry.createRegistry(1099);   //Create Registry on ort number 1099
		ChatServer server = new ChatServerImpl();           //Object of Impl references to Server
		r.rebind(chatRoom, server);                         //Rebind the ChatRoom name & server object
	}
}
