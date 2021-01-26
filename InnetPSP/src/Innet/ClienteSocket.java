package Innet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;

import java.net.Socket;
import java.util.Scanner;
public class ClienteSocket
{
	static final String HOST = "192.168.0.20";
	static final int PUERTO = 6000;
	public static void main(String[] arg)
	{
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.println("Iniciando programa cliente..");
			Socket cliente = new Socket(HOST, PUERTO);
			// Creo el flujo de salida al servidor
			DataOutputStream flujoSalida = new
					DataOutputStream(cliente.getOutputStream());
			// Creo el flujo de entrada desde servidor
			InputStream entrada = cliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(entrada);
			System.out.println("Escriba un mensaje..");
			String cadena=sc.nextLine();
			while(cadena!=null)
			{
				flujoSalida.writeUTF(cadena);
				String eco=flujoEntrada.readUTF();
				System.out.println("=>Eco:"+eco);
				System.out.println("Escriba un mensaje..");
				cadena=sc.nextLine();
			}
			System.out.println("Fin programa cliente..");
			// Cerramos streams y sockets
			entrada.close();
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
			sc.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
