package br.com.alura.cliente;

import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 12345);
		System.out.println("Conexão Estabelecida");
		Scanner teclado = new Scanner(System.in);
		
		Thread entrada = new Thread(new ClienteRecebeMensagem(socket));
		Thread saida = new Thread(new ClienteDigitaMensagem(socket, teclado, entrada));
		
		saida.start();
		entrada.start();
		
		saida.join();
		socket.close();
	}

}
