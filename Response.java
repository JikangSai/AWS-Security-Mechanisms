import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;

public class Response {
	public static void main(String[] args) throws IOException {
		ServerSocket welcomeSocket = new ServerSocket(4000);
		while(true) {
			Socket connectionSocket = welcomeSocket.accept();
			DataInputStream inputStream = new DataInputStream(connectionSocket.getInputStream());
			DataOutputStream outputStream = new DataOutputStream(connectionSocket.getOutputStream());
			System.out.println("Receive Data Successfully!!");
			outputStream.writeByte(0);
			connectionSocket.close();
		}
	}

}
