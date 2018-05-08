package Homework11;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


/**
 * User: blangel
 * Date: 11/23/14
 * Time: 4:32 PM
 */
public class NonBlockingChatter implements Chatter {
    
    private static final int READ_BUFFER_SIZE = 1024;

    private static final Charset UTF8 = Charset.forName("UTF-8");
    
    private final ByteBuffer readBuffer = ByteBuffer.allocate(READ_BUFFER_SIZE);
    
    private final Pipe.SourceChannel userInput;
    
    private final SocketChannel chatServerChannel;

    public NonBlockingChatter(SocketChannel chatServerChannel, Pipe.SourceChannel userInput) {
        // TODO
        this.userInput = userInput;
        this.chatServerChannel = chatServerChannel;
    }

    @Override public void run() {
        // TODO
        while(!Thread.currentThread().isInterrupted()){
            readUserInput();
            readFromChannel();
        }
        try {
            chatServerChannel.close();
        } catch (IOException e) {
            System.out.println("run Nonblocking error: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
    
    void readUserInput() {
        readBuffer.clear();
        try {
            while(userInput.read(readBuffer) > 0) {
                if((readBuffer == null) || (readBuffer.position() == 0)) {
                    continue;
                }
                readBuffer.flip();
                chatServerChannel.write(readBuffer);
                readBuffer.clear();
            }
        } catch (IOException e) {
            System.out.println("readUserInput error: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
    
    void readFromChannel() {
        readBuffer.clear();
        try {
            while(chatServerChannel.read(readBuffer) > 0) {
                readBuffer.flip();
                CharsetDecoder decoder = UTF8.newDecoder();
                CharBuffer charBuffer = decoder.decode(readBuffer);
                System.out.printf("%s", charBuffer.toString());
            }
        } catch (IOException e) {
            System.out.println("readFromChannel error: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
