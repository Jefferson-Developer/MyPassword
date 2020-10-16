package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread{
	
	private static ServerSocket server;
	private Socket socket;
	
	public Servidor(Socket socketCliente) {
		this.socket = socketCliente;
	}
	
	public static void main(String[] args) {
		try {

			server = new ServerSocket(4444);
			System.out.println("Servidor no AR!!!");
			
			while (true) {
				
				Socket con = server.accept();
				
				Thread t = new Servidor(con);
				t.start();
			}

		} catch (Exception e) {	e.printStackTrace();}
	}
	
	@Override
	public void run() {
		try {

			//prepara o stream para receber os dados do cliente
			InputStream in;
			InputStreamReader inr;
			BufferedReader bfr = null;
			String mensagemCliente, respostaCliente;
			
			try {
				in = this.socket.getInputStream();
				inr = new InputStreamReader(in);
				bfr = new BufferedReader(inr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//recebe a mensagem do cliente
			while((mensagemCliente = bfr.readLine()) == null) {
				
			}
			
			if(mensagemCliente.equalsIgnoreCase("fhsdfusd")) {
				respostaCliente =  "acertou";
				System.out.println("ALELUIAAA >>>> ACERTOU!!!!!!!!");
			}else {
				respostaCliente =  "errou";
			}
			
			//Envio da mensagem para o respectivo cliente
			OutputStream out = this.socket.getOutputStream();
			Writer ouw = new OutputStreamWriter(out);
			BufferedWriter bfw = new BufferedWriter(ouw);

			bfw.write(respostaCliente + System.lineSeparator());
			bfw.flush();
			
			bfw.close();
			ouw.close();
			out.close();
			this.socket.close();
			
		} catch (

		Exception e) {e.printStackTrace();}

	}

}
