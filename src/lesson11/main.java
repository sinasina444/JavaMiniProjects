package lesson11;

import java.io.FileReader;
import java.nio.charset.Charset;
import java.io.IOException;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String charsetName = "UTF-8";
		Charset defaultCharset = Charset.defaultCharset();
		Charset charset = Charset.forName(charsetName);
		System.out.printf("%s %s %n",defaultCharset,charset);
		//Reader!
		try{
			FileReader reader = new FileReader("aaa");
			int read = reader.read();
			System.out.println(read);
		}catch(IOException ioe){
			System.out.printf("%s", ioe.getMessage());
		}
	}

}
