import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket welcomeSocket = new ServerSocket(5000);
		while(true) {
				Socket connectionSocket = welcomeSocket.accept();
		    byte[] resultBuffer = new byte[0];
		    byte[] tempBuffer = new byte[800];
		    int var = -1;
		    while((var = connectionSocket.getInputStream().read(tempBuffer, 0, tempBuffer.length)) > -1) {
						int len = resultBuffer.length;
		        byte[] tbuff = new byte[len + var];
		        System.arraycopy(resultBuffer, 0, tbuff, 0, len);
		        System.arraycopy(tempBuffer, 0, tbuff, len, var);
		        resultBuffer = tbuff;
		    }
		    connectionSocket.close();

		    String fileName = "index.html";
		    File myFile = new File (fileName);
		    OutputStream outStream = new FileOutputStream(myFile);

	      outStream.write(resultBuffer);
	      String bucketName = "jqu559lab4";
	      AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2").build();
	     	PutObjectRequest request = new PutObjectRequest(bucketName, fileName, myFile);
        s3Client.putObject(request);
        if (outStream != null) outStream.close();
		}
	}
}
