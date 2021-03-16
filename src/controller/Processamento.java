package controller;

import java.util.concurrent.Semaphore;

public class Processamento extends Thread
{
	static private int ingressos = 100;
	private int qntIngressos;
	private Semaphore semaforo;
	private int idComprador;

	public Processamento(int idComprador, Semaphore semaforo)
	{
		this.idComprador = idComprador;
		this.semaforo = semaforo;
	}

	@Override
	public void run()
	{
		login();
	}

	private void login()
	{
		qntIngressos = (int) ((Math.random() * 4) + 1);

		int tempo = (int) ((Math.random() * 1951) + 50);		

		try
		{
			sleep(tempo);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		if (tempo > 1000)
		{
			System.out.println(timeout(1, tempo));
		}
		else
		{
			compra();
		}

	}

	private String timeout(int tipo, int tempo)
	{
		String msg = "#" + idComprador +" - Timeout  - Processo: ";
		if (tipo == 1)
		{
			msg += "Login";
		}
		else
		{
			msg += "Compra";
		}
		msg += " - Tempo: " + (tempo / Math.pow(10, 3)) + "s.";
		return msg;			
	}

	private void compra()
	{
		int tempo = (int) ((Math.random() * 2001) + 1000);

		try
		{
			sleep(tempo);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		if (tempo > 2500)
		{
			System.out.println(timeout(0, tempo));
		}
		else
		{
			try
			{
				semaforo.acquire();
				finalizacao();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				semaforo.release();
			}			
		}
	}

	private void finalizacao()
	{
		if (qntIngressos > ingressos)
		{
			System.out.println("#" + idComprador +" - tentou comprar: "+ qntIngressos
					+ ", quantidade não disponivel.");
		}
		else
		{
			ingressos -= qntIngressos;
			System.out.println("#" + idComprador + " - " + qntIngressos + " ingresso(s) adquirido(s)!"
					+ " Ingressos restantes: " + ingressos);
		}
	}
}
