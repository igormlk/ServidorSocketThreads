package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class DistribuirTarefas implements Runnable {

	private Socket socket;
	private ExecutorService threadPool;
	private ServerSocket servidor;
	private AceitarConexoes aceitarConexoes;

	public DistribuirTarefas(ServerSocket servidor, Socket socket, ExecutorService threadPool, AceitarConexoes aceitarConexoes) {
		this.servidor = servidor;
		this.socket = socket;
		this.threadPool = threadPool;
		this.aceitarConexoes = aceitarConexoes;
	}

	@Override
	public void run() {
		try {
			System.out.println("Distribuindo as tarefas para o cliente " + socket);
			Scanner scan = new Scanner(socket.getInputStream());
			String comando = null;
			while(scan.hasNextLine()) {
				comando = scan.nextLine();
				System.out.println(comando);
				switch(comando) {
				case "c1":
					threadPool.execute(new TarefaHello(socket, comando));
				break;
				case "fim":
					threadPool.shutdownNow();
					servidor.close();
					aceitarConexoes.stopRunning();
				break;
				}
			}
			scan.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			try {
				threadPool.shutdownNow();
				servidor.close();
				socket.close();
				aceitarConexoes.stopRunning();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
