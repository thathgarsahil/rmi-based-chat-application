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
import java.rmi.RemoteException;
import Server.*;
import java.rmi.Naming;

import java.awt.List;
import java.awt.TextArea;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.FlowLayout;

public class ChatFrame extends Frame {
	private ChatServer server;
	private ChatClient client;
	private String name;
	private TextArea chatArea = new TextArea(20, 70);
	private List clientList = new List(20, true);
	private TextArea entryArea = new TextArea(5, 70);
	private Button sendButton = new Button("Send");
	private Button logoutButton = new Button("Logout");

	public ChatFrame(ChatServer server, String clientName) throws RemoteException {
		super("Chat Client - " + clientName);
		this.server = server;
		this.name = clientName;
		this.client = new ChatClientGUIImpl(clientName, clientList, chatArea);
		String[] clientNames = server.login(client);
		for (String clName : clientNames) {
			clientList.add(clName);
		}
		this.setBounds(0, 0, 900, 500);
		this.setupComponents();
		this.setupEvents();
	}	

	private void setupComponents() {
		this.setLayout(new FlowLayout());
		this.chatArea.setEditable(false);
		this.add(chatArea);
		this.add(clientList);
		this.add(entryArea);
		Panel p = new Panel();
		p.add(sendButton);
		p.add(logoutButton);
		this.add(p);
	}

	private void setupEvents() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				logout();
			}
		});
		this.logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				logout();
			}
		});
		this.sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			String[] clientNames = clientList.getSelectedItems();
			for (String clientName : clientNames) {
				try {
					server.sendMessage(name, clientName, entryArea.getText());
					chatArea.append("Sent message to: " + clientName + "\r\n");
				} catch (RemoteException re) {
					re.printStackTrace();
					chatArea.append("Send message failed for : " + clientName + "\r\n");
				}
			}
			entryArea.setText("");
			}
		});
	}

	private void logout() {
		try{
			this.server.logout(this.name);
		} catch(RemoteException re) {
			re.printStackTrace();
		}
		this.dispose();
		System.exit(0);
	}
		
	public static void main(String[] args) throws Exception {
		String clientName = args[0];
		String host = args[1];
		String chatRoom = args[2];
		ChatServer server = (ChatServer)Naming.lookup("rmi://"+ host + "/" + chatRoom);
		ChatFrame frame = new ChatFrame(server, clientName);
		frame.setVisible(true);  
	}
}
