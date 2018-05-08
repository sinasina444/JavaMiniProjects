package Homework11;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * User: blangel
 * Date: 11/23/14
 * Time: 4:31 PM
 */
public class Chat {

    private static final String USAGE = "Usage: x where x is either 'blocking' or 'nonblocking'";

    public static void main(String[] args) throws IOException {
        if ((args == null) || (args.length != 1) ||
                (!"blocking".equals(args[0]) && !"nonblocking".equals(args[0]))) {
            System.out.printf("%s%n", USAGE);
            return;
        }
        Chatter chatter;
        if ("blocking".equals(args[0])) {
            @SuppressWarnings("resource")
			Socket serverConnection = new Socket(ChatServer.SERVER_HOST, ChatServer.SERVER_PORT);
            chatter = new BlockingChatter(serverConnection.getInputStream(), serverConnection.getOutputStream(), System.in);
        } else {
            InputSelectableChannel inputChannel = new InputSelectableChannel(System.in);
            SocketChannel serverChannel = SocketChannel.open(new InetSocketAddress(ChatServer.SERVER_HOST, ChatServer.SERVER_PORT));
            serverChannel.configureBlocking(false);
            inputChannel.start();
            chatter = new NonBlockingChatter(serverChannel, inputChannel.getChannel());
        }
        chatter.run();

    }

}
