package br.com.alura.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteDigitaMensagem implements Runnable{

	private PrintStream print;
	private Scanner teclado;
	private Socket socket;
	private Thread entrada;
	
	public ClienteDigitaMensagem(Socket socket, Scanner teclado, Thread entrada) {
		try {
			this.socket = socket;
			this.print = new PrintStream(socket.getOutputStream());
			this.teclado = teclado;	
			this.entrada = entrada;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		String msg = null;
		while(teclado.hasNextLine()) {
			msg = teclado.nextLine();
			if(msg.trim().equals("fim")) {
				break;
			}
			synchronized(entrada) {
				if(!entrada.isInterrupted())
					entrada.interrupt();
			}
			print.println(msg);
		}
	}

}
