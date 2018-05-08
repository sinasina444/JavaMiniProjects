package lesson11;

import java.nio.ByteBuffer;

public class BufferRead {
	public void read(ByteBuffer buffer){
		//moves the position pointer to the start of the underlying buffer
		//flip就是写模式切换到读模式，同时position = 0, limit = previous position
		buffer.flip();
		//byte[] a = new byte[3];
		//buffer.put(a, 0, 3);//写到buffer里去
		while(buffer.hasRemaining()){
			buffer.get();
		}
		
		buffer.clear();
	}
	
}
