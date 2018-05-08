package lesson11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class SelectorHandle {
	public void handle(SelectableChannel... channels) throws IOException {
		Selector selector = Selector.open();
		
		for(SelectableChannel channel: channels){
			channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		}
		
		while(!Thread.currentThread().isInterrupted()){
			int readyChannels = selector.select();
			if(readyChannels == 0){
				continue;
			}
			
			Set<SelectionKey> selectKeys = selector.selectedKeys();//keys只能减不能加
			Iterator<SelectionKey> keyIterator = selectKeys.iterator();
			
			while(keyIterator.hasNext()){
				SelectionKey key = keyIterator.next();
				//这句重点
				SelectableChannel channel = key.channel();
				if(key.isReadable()){
					//TODO-- read from channel
					System.out.println("Key is readable now!");
				}else if(key.isWritable()){
					//TODO-- write from channel
					System.out.println("Key is writeable now!");
				}
				keyIterator.remove();
			}
		}
	}
}
