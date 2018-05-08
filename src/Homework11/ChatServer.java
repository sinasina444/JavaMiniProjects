package Homework11;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * User: blangel
 * Date: 11/23/14
 * Time: 4:43 PM
 */
public class ChatServer implements Runnable {

    public static final String SERVER_HOST = "localhost";

    public static final int SERVER_PORT = 8001;

    private static final int READ_BUFFER_SIZE = 1024;

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer();
        server.run();
    }

    private final ServerSocketChannel channel;

    private final Selector selector;

    private final ByteBuffer readBuffer;

    private final Map<SocketChannel, ByteBuffer> writeBuffers;

    public ChatServer() throws IOException {
        channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new java.net.InetSocketAddress(SERVER_HOST, SERVER_PORT));
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        readBuffer = ByteBuffer.allocate(READ_BUFFER_SIZE);
        this.writeBuffers = new HashMap<>();
    }

    @Override public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                process();
            } catch (IOException ioe) {
                System.out.printf("Exception - %s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void process() throws IOException {
        int events = selector.select();
        if (events < 1) {
            return;
        }
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = keys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();

            try {
                if (key.isAcceptable()) {
                    SocketChannel client = channel.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    writeBuffers.put(client, ByteBuffer.allocate(READ_BUFFER_SIZE));
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    readBuffer.clear();
                    int result = client.read(readBuffer);
                    if (result == -1) {
                        writeBuffers.remove(client);
                        key.cancel();
                        continue;
                    }
                    for (Map.Entry<SocketChannel, ByteBuffer> entry : writeBuffers.entrySet()) {
                        SocketChannel otherClient = entry.getKey();
                        if (client != otherClient) {
                            ByteBuffer writeBuffer = entry.getValue();
                            readBuffer.flip();
                            writeBuffer.put(String.format("[%s] ", client.getRemoteAddress().toString()).getBytes());
                            writeBuffer.put(readBuffer);
                            writeBuffer.put((byte) '\n');
                        }
                    }
                    readBuffer.flip();
                    CharsetDecoder decoder = UTF8.newDecoder();
                    CharBuffer charBuffer = decoder.decode(readBuffer);
                    System.out.printf("[%s] %s%n", client.getRemoteAddress().toString(), charBuffer.toString());
                } else if (key.isWritable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer writer = writeBuffers.get(client);
                    if ((writer == null) || (writer.position() == 0)) {
                        continue;
                    }
                    writer.flip();
                    client.write(writer);
                    writer.clear();
                }
            } finally {
                iterator.remove();
            }

        }
    }
}
