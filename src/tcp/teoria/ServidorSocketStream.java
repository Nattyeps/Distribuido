package tcp.teoria;

import java.io.*;
import java.net.*;

public class ServidorSocketStream {
	public static void main(String[] args) {
		try {
			System.out.println("Creando el socket servidor");
			ServerSocket serverSocket = new ServerSocket(5555);
//			ServerSocket serverSocket = new ServerSocket();
//			System.out.println("Realizando el bind");
//			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
//			serverSocket.bind(addr);
			System.out.println("Aceptando conexiones");
			Socket newSocket = serverSocket.accept();
			System.out.println("Conexión recibida");
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();
			byte[] mensaje = new byte[25];
			is.read(mensaje);
			System.out.println("Mensaje recibido: " + new String(mensaje));
			System.out.println("Cerrando el nuevo socket");
			newSocket.close();
			System.out.println("Cerrando el socket servidor");
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
