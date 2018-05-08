package Homework11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.WritableByteChannel;

/**
 * User: blangel
 * Date: 11/23/14
 * Time: 4:52 PM
 */
public class InputSelectableChannel {

    private static class ChannelThread extends Thread {

        private static final int DEFAULT_BUFFER = 1024;

        private final ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER);

        private final BufferedReader input;

        private final WritableByteChannel output;

        private ChannelThread(InputStream input, WritableByteChannel output) {
            this.input = new BufferedReader(new InputStreamReader(input));
            this.output = output;
            this.setDaemon(true);
        }

        @Override public void run() {
            try {
                while (!isInterrupted()) {
                    String line = input.readLine();
                    if (line == null) {
                        continue;
                    }
                    buffer.clear();
                    buffer.put(line.getBytes());
                    buffer.flip();
                    output.write(buffer);
                    buffer.clear();
                }
                output.close();
            } catch (IOException e) {
                System.out.printf("Exception - %s%n", e.getMessage());
            }
        }
    }

    private final Pipe pipe;

    private final ChannelThread channel;

    public InputSelectableChannel(InputStream input) throws IOException {
        this.pipe = Pipe.open();
        this.channel = new ChannelThread(input, pipe.sink());
    }

    public void start() {
        this.channel.start();
    }

    public Pipe.SourceChannel getChannel() throws IOException {
        Pipe.SourceChannel channel = pipe.source();
        channel.configureBlocking(false);
        return channel;
    }

}
