package lesson11;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class BufferedInputStreams {
	public void read(InputStream input){
		try(BufferedInputStream stream = new BufferedInputStream(input)){
			int availableAmount = stream.available();
			byte[] into = new byte[availableAmount];
			int read = stream.read(into,0,into.length);
			if(read == -1){
				System.out.printf("Stream is closed%n");
			}else{
				System.out.printf("Read %d bytes into buffer%n", read);
			}
		}catch(IOException ioe){
			System.out.printf("Failed to read - %s%n", ioe.getMessage());
		}
	}
}
