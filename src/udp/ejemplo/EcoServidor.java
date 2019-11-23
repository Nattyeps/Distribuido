package udp.ejemplo;

import java.net.*;
import java.io.*;

class ServidorUDP {
	private DatagramSocket socketUDP;
	private DatagramPacket recibido;

	public ServidorUDP(int puerto) {
		try {
			this.socketUDP = new DatagramSocket(puerto);
			this.recibido = null;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public String recibirMsg() {
		try {
			byte[] buffer = new byte[1000];
			recibido = new DatagramPacket(buffer, buffer.length);
			socketUDP.receive(recibido);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(recibido.getData()).trim();
	}

	public void enviarMsg(String msg) {

		try {
			DatagramPacket respuesta = new DatagramPacket(msg.getBytes(), msg.length(), recibido.getAddress(),
					recibido.getPort());
			socketUDP.send(respuesta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeServidorUDP() {
		socketUDP.close();
		System.out.println("-> Servidor Terminado");	
	}

}

public class EcoServidor {
	public static void main(String[] args) {
		int puerto = 5555;
		System.out.println("Servidor escucha por el puerto "+puerto);
		ServidorUDP canal = new ServidorUDP(puerto);
		String linea;
		do {
			linea = canal.recibirMsg();
			System.out.println("Cliente: " + linea);
			canal.enviarMsg("Eco - " + linea);
		} while (!linea.equals("Adiós"));
		canal.closeServidorUDP();
	}
}
