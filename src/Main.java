import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;
import java.util.List;

import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) {

		System.out.println(10);
		Main.operacoes();

	}
	
	public static void operacoes()
	{
		System.out.println("Escolha a opção desejada:");
		System.out.println("1- Compactar um arquivo.");
		System.out.println("2- Descompactar um arquivo.");

		try {
			int opt = System.in.read();

			if (opt == 49)
			{
				Compactador c = new Compactador();
			} else if(opt == 50) {
				Descompactador d = new Descompactador();
			} else {
				System.out.println("Tchau");
				System.exit(0);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
