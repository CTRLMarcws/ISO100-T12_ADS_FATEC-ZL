package view;

import java.util.concurrent.Semaphore;

import controller.Processamento;

public class Bilheteria {

	public static void main(String[] args)
	{
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for (int idComprador = 1; idComprador < 301; idComprador++)
		{
			Thread t = new Processamento(idComprador, semaforo);
			t.start();
		}

	}

}
