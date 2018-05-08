package lesson11;

import java.io.IOException;
import java.io.InputStream;

public class InputStreams {
	public void read(InputStream stream){
		//can read only one byte
		try {
			int read = stream.read();
			if(read == -1){
				System.out.printf("Stream is closed");
			} else {
				// read is a single byte
				byte valueRead = (byte)read;
				System.out.printf("Read byte value %s%n",Byte.toString(valueRead));
			}
		} catch(IOException ioe){
			System.out.printf("Failed to read - %s%n", ioe.getMessage());
		}
		
		//check for availability and read into an array
		try {
			int availableAmount = stream.available();
			byte[] into = new byte[availableAmount];
			int read = stream.read(into,0,into.length);
			if(read == -1){
				System.out.printf("Stream is closed%n");
			}else{
				System.out.printf("Read %d bytes into buffer%n", read);
			}
		} catch(IOException ioe){
			System.out.printf("Failed to read - %s%n", ioe.getMessage());
		} finally{
			
		}
	}
}
