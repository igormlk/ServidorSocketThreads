package br.com.alura.servidor;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {

	public static void main(String[] args) throws Exception {
		System.out.println("---- Iniciando Servidor ----");
		ServerSocket servidor = new ServerSocket(12345);
		ExecutorService threadPool = Executors.newCachedThreadPool(FabricaThread.getInstance());
		threadPool.execute(new AceitarConexoes(servidor,threadPool));
	}

}
