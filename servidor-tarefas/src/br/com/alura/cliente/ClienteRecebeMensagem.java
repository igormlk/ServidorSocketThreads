package br.com.alura.cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteRecebeMensagem implements Runnable{

	private Scanner input;
	private Socket socket;
	
	public ClienteRecebeMensagem(Socket socket) {
		synchronized (socket) {
			try {
				this.setSocket(socket);
				this.input = new Scanner(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		while(input.hasNextLine()) {
			synchronized (System.out) {
				System.out.println(input.nextLine());
			}
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
