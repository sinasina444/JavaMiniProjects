package lesson11;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreams {
	public void read(String filePath){
		read(new File(filePath));
	}
	
	public void read(File file){
		FileInputStream stream = null;
		try{
			stream = new FileInputStream(file);
			int availableAmount = stream.available();
			byte[] into = new byte[availableAmount];
			int read = stream.read(into,0,into.length);
			if(read == -1){
				System.out.printf("stream is closed%n");
			}else{
				System.out.printf("read %d bytes into buffer", read);
			}
		}catch(IOException ioe){
			System.out.printf("failed to read - %s%n", ioe.getMessage());
		}finally{
			if(stream != null){
				try{
					stream.close();
				}catch (IOException ioe){
					System.out.println(ioe.getMessage());
				}
			}
		}
	}
}
