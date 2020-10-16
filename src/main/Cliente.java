package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;

public class Cliente extends Thread{

	private Socket socket;
	
	public Cliente(Socket con) {
		this.socket = con;
	}
	
	public static void main(String[] args) {
		
		while(true) {
			
			 try {
				new Cliente(new Socket("localhost", 4444)).start();
				
			} catch (IOException e) {e.printStackTrace();}
			 
		}

	}

	@Override
	public void run() {
		
		OutputStream ou = null;
		Writer ouw = null;
		BufferedWriter bfw = null;
		
		
		try {
			ou = socket.getOutputStream();
			ouw = new OutputStreamWriter(ou);
			bfw = new BufferedWriter(ouw);
			
			String mensagem = gerarString();
			
			bfw.write(mensagem + System.lineSeparator());
			bfw.flush();
			
		} catch (IOException e) {e.printStackTrace();}
		
		InputStream in;
		try {
			in = socket.getInputStream();
			
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader bfr = new BufferedReader(inr);
		
			while (!bfr.ready()) {

			}
			
			String respostaServidor = bfr.readLine();
			System.out.println("A resposta foi: " + respostaServidor);

		} catch (IOException e) {e.printStackTrace();}

		try {
			bfw.close();
			ouw.close();
			ou.close();
			socket.close();
		} catch (IOException e) {e.printStackTrace();}



	}

	private String gerarString() {
		String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuilder sb = new StringBuilder(); 
		Random random = new Random(); 
		
		for (int i = 0; i < 8; i++) { 
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length()))); 
		} 
		
		return sb.toString();
		
	}
	
}
