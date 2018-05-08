package lesson11;

import java.io.OutputStream;
import java.io.IOException;

public class OutputStreams {
	//can write only one byte
	public void write(OutputStream stream){
		try{
			stream.write(0x01);
		}catch(IOException ioe){
			System.out.printf("Failed to write - %s%n",ioe.getMessage());
		}
		
		//write bytes from an array and then flush
		try {
			byte[] from = new byte[] {0x01,0x02,0x03,0x04};
			stream.write(from,0,from.length);
			stream.flush();
		}catch (IOException ioe){
			System.out.printf("Failed to write -- %s%n", ioe.getMessage());
		}
	}
}
