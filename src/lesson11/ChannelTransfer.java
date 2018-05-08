package lesson11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTransfer {
	public void channelTransfer(File from, File to) {
		try(FileInputStream fromStream = new FileInputStream(from);
				FileOutputStream toStream = new FileOutputStream(to)){
			//每个stream都绑定了一个channel?
			FileChannel fromChannel = fromStream.getChannel();
			FileChannel toChannel = toStream.getChannel();
			//JVM will attempt to do this with native I/O methods
			fromChannel.transferTo(0, fromChannel.size(), toChannel);
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
	
	public void write(File to,byte[] values) throws IOException{
		//FileInputStream 指的是要写到文件File里的stream，这里不是OutputStream，不要混淆
		try(FileChannel channel = new FileInputStream(to).getChannel()){
			//需要先从byte写进到buffer,再从buffer写到channel里面去
			//wrap 实现了buffer与byte[] 数组之间的绑定
			ByteBuffer buffer = ByteBuffer.wrap(values);
			//然后直接操作channel就可以实现写入了？
			channel.write(buffer);
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
}
