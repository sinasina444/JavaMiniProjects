package lesson11;

import java.io.InputStream;
import java.io.IOException;

public class InputExample {
	private static final int DEFAULT_LENGTH = 1024;
	public byte[] read(InputStream stream){
		byte[] readData = new byte[DEFAULT_LENGTH];
		int read, index = 0;
		try{
			while((read = stream.read(readData,index,(readData.length - index))) != -1){
				index += read;
				if(index == readData.length){
					//all readData space is full
					byte[] resized = new byte[readData.length*2];
					System.arraycopy(readData, 0, resized, 0, index);//index!
					readData = resized;
				}
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		byte[] compressed = new byte[index];
		System.arraycopy(readData, 0, compressed, 0, index);
		return compressed;
	}
}
