import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TestClient {

	public static String PublicEC2Host="";
	public static int PublicEC2Port=0;
	public static String FileToTransfer="";
	
	public static void main(String[] args) throws IOException {
		
		//process command line argument to get PublicEC2Host and PublicEC2Port
		if(args.length<3){
			System.out.println("You must provide Host and Port of the public host and the file to transfer");
			return;
		}
		PublicEC2Host=args[0];
		PublicEC2Port=Integer.parseInt(args[1]);
		FileToTransfer=args[2];
		
		//connect to PublicEC2Host
		Socket socket=null;
		try {
			socket = new Socket(PublicEC2Host,PublicEC2Port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println("Connecting...");
		
	    //transfer file to PublicEC2Host
	    File myFile = new File (FileToTransfer);
        byte [] myByteArray  = new byte [(int)myFile.length()];
        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(myByteArray,0,myByteArray.length);
        OutputStream os = socket.getOutputStream();
        System.out.println("Sending " + 
        		FileToTransfer + 
        		"(" + myByteArray.length + " bytes)");
        os.write(myByteArray,0,myByteArray.length);
        os.flush();
        System.out.println("Done.");
        
        //wrap up
        if (os != null) os.close();
        if (bis != null) bis.close();
        if (socket != null) socket.close();
	}

}
