package br.com.alura.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class TarefaHello implements Runnable{

	private Socket socket;
	private String comando;
	private PrintStream print;
	
	public TarefaHello(Socket socket, String comando) {
		this.setSocket(socket);
		this.comando = comando;
		try {
			this.print = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		print.println("Comando Recebido = " + comando);
		print.println("Hello World");
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
