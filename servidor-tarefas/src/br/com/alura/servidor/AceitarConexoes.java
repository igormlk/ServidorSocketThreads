package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class AceitarConexoes implements Runnable{
	
	private ServerSocket servidor;
	private ExecutorService threadPool;
	private volatile boolean isRunning = true;
	public AceitarConexoes(ServerSocket servidor, ExecutorService threadPool) {
		this.servidor = servidor;
		this.threadPool = threadPool;
	}

	@Override
	public void run() {
		Socket socket;
		while (isRunning) {
			try {
				socket = servidor.accept();
				System.out.println("Aceitando novo cliente na porta " + socket.getPort());
				DistribuirTarefas distribuirTarefas = new DistribuirTarefas(servidor,socket, threadPool, this);
				threadPool.execute(distribuirTarefas);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopRunning() {
		this.isRunning = false;
	}
	
}
