import java.io.*;
import java.net.*;

class TCPClient {
	public static void main(String argv[]) {
		if (argv.length < 2) {
			System.err.println("ERROR:\n 	Insert IP and PORT\n 	java TCPClient localhost 9000");
			return;
		}
		// Declara as mensagens da aplicação
		String requisicao;
		String resposta;

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		// Declara o socket
		Socket clientSocket;
		try {
			// Instancia o socket
			clientSocket = new Socket(argv[0], Integer.parseInt(argv[1]));
			// Instancia objeto que escreve no buffer de saída
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			// Instancia objeto que lê buffer de entrada
			DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
			showOptions();
			// Ler entrada do teclado
			requisicao = inFromUser.readLine();
			// Enviar mensagem digitada para o servidor
			outToServer.writeUTF(requisicao + '\n');
			// Esperar resposta do servidor
			resposta = inFromServer.readUTF();

			clientSocket.close();

			if (requisicao.equalsIgnoreCase("\\CLOSE"))
				return;

			System.out.println("FROM SERVER: " + resposta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void showOptions() {
		System.out.println("\\UPTIME - Server execution time");
		System.out.println("\\REQNUM - Quantity of the server requests");
		System.out.println("\\CLOSE - Client stop");
		System.out.println("Write your choice: ");
	}
}