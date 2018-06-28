package br.com.alura.servidor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadFactory;

public class FabricaThread implements ThreadFactory{

	private static FabricaThread fabrica = new FabricaThread();
	private long size = 0;
	private Map<String, Thread> listaThreads;
	
	private FabricaThread() {
		listaThreads  = new HashMap<>();
	}
	
	public static FabricaThread getInstance() {
		return fabrica;
	}
	
	
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, "Thread " + size);
		synchronized (listaThreads) {
			listaThreads.put("Thread "+ size, thread);
		}
		return thread;
	}

	public Map<String, Thread> getMapThread(){
		return this.listaThreads;
	}
	
}
