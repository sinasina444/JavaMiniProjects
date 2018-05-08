package Homework11;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * User: blangel
 * Date: 11/23/14
 * Time: 4:31 PM
 */
public class BlockingChatter implements Chatter {
    
    //read user input and write to server
    private static class UserInputThread extends Thread {
        
        private static final Charset UTF8 = Charset.forName("UTF-8");
        
        private final OutputStream chatServerOutput;
        
        private final InputStream userInput;

        private UserInputThread(InputStream userInput, OutputStream chatServerOutput) {
            this.userInput = userInput;
            this.chatServerOutput = chatServerOutput;
        }

        @Override public void run() {
            String line = null;
            try {
                BufferedReader bufferedRead = new BufferedReader(new InputStreamReader(userInput));            
                while((line = bufferedRead.readLine()) != null) {
                    writeIntoOutputStream(chatServerOutput,line.getBytes(UTF8));
                }
                bufferedRead.close();
            }catch(IOException ioe) {
                System.out.printf("Failed to run userInputThread - %s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
    
    //read InputStream from socket and print
    private static class ServerOutputThread extends Thread {
        
        private static final Charset UTF8 = Charset.forName("UTF-8");
        private final String FilePath;
        private List<String> strsFile;
        private final InputStream chatServerInput;
        private final OutputStream chatServerOutput;

        private ServerOutputThread(InputStream chatServerInput, OutputStream chatServerOutput) {
            this.chatServerInput = chatServerInput;
            this.chatServerOutput = chatServerOutput;
            FilePath = "resources/Moby Dick.txt";
            strsFile = new ArrayList<String>();
        }

        @Override public void run() {
            prepareEastEgg(FilePath);
            String line = null;
            //autoCloseable
            try (BufferedReader bufferedRead = new BufferedReader(new InputStreamReader(chatServerInput))){     
                while((line = bufferedRead.readLine()) != null) {
                    System.out.println(line);
                    //if line contains "java", return a sentence from the book
                    if(line.contains("java")) {
                        String strTemp = new String();
                        do {
                            strTemp = writeEasterEgg();
                        } while(strTemp == null);
                        writeIntoOutputStream(chatServerOutput,strTemp.getBytes(UTF8));
                    }
                }
                //bufferedRead.close();	//Auto closed
            }catch(IOException ioe) {
                System.out.printf("Failed to run serverOutputThread - %s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        
        private void prepareEastEgg(String filePath) {
            if(filePath == null) {
                return;
            }
            FileInputStream fin = null;
            BufferedReader buffReader = null;
            try {
                fin = new FileInputStream(filePath);
                buffReader = new BufferedReader(new InputStreamReader(fin));
                String strTmp = "";
                while((strTmp = buffReader.readLine()) != null) {
                	if(!strTmp.equals("")) {
                        strsFile.add(strTmp);
                	}
                }
                Collections.shuffle(strsFile);    //shuffle the order of the file lines
            } catch(FileNotFoundException ioe){
            	System.out.println("File Not Found:" + ioe.getMessage());
            } catch(IOException ioe) {
                System.out.printf("Exception - %s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            } finally {
                try {
    				buffReader.close();
    	            if(fin != null) {
    	            	fin.close();
    	            }
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
            }
        }
        
        private String writeEasterEgg() {
            int len = strsFile.size();
            if(len == 0) {
                return new String();
            }
            Random random = new Random();
            int index = random.nextInt(len);
            return strsFile.get(index);
        }
    }

    private final UserInputThread userInputThread;
    
    private final ServerOutputThread serverOutputThread;

    public BlockingChatter(InputStream chatServerInput, OutputStream chatServerOutput, InputStream userInput) {
        // TODO
        userInputThread = new UserInputThread(userInput,chatServerOutput);
        serverOutputThread = new ServerOutputThread(chatServerInput,chatServerOutput);
    }

    @Override public void run() {
        // TODO
        if(!Thread.currentThread().isInterrupted()) {
            this.userInputThread.start();
            this.serverOutputThread.start();
        }
    }
    
    public static void writeIntoOutputStream(OutputStream stream, byte[] into) {
        if(into == null || into.length == 0) {
            return;
        }
        try {
            stream.write(into,0,into.length);
            stream.flush();
        }catch (IOException ioe) {
            System.out.printf("Failed to write -- %s%n", ioe.getMessage());
        }
    }
}
