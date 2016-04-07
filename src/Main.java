import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

	public static void main(String[] args) {
		
		// Tamanho do alfabeto [ASCII]
		int[] frequencias = new int[256];

		// Huffman
		Huffman huf = new Huffman();
		
		// Caminho do arquivo a ser lido
		String file = "/home/leonardo/workspace/Huffman/Util/arquivo.txt";
		
		try {
			Path p    = FileSystems.getDefault().getPath("", file);
			byte[] fb = Files.readAllBytes(p);
			
			for (byte fl : fb)
			{
				// Adiciona no array de frequencias, utilizando o código ASCII como chave
				frequencias[ fl & 0xff ] += 1; 
			}
			
			// Constrói árvore para compactar
			No arvoreHuffman = huf.montaArvore(frequencias);
			
			FileOutputStream fos = new FileOutputStream("/home/leonardo/workspace/Huffman/Util/arquivo.ilm");
			String[] s = huf.compacta(arvoreHuffman);

			int contador = 0;
			int pool = 0;

			for (int i = 0; i < fb.length; i++)
			{
				//System.out.println(fb[i] + " -> " + s[fb[i] & 0xff] + " : ");
				System.out.print(s[fb[i] & 0xff]);

				for(int j = 0; j < s[fb[i] & 0xff].length(); j++)
				{
					pool <<= 1;
					// Percorre a string populando o bitset
					if (s[fb[i] & 0xff].charAt(j) == '1') 
					{
						pool |= 1;
					} 
					
					contador += 1;
					//http://introcs.cs.princeton.edu/java/stdlib/BinaryOut.java.html
					if (contador == 8)
					{
						fos.write( pool );
						contador = 0;
						pool = 0;
					}
				}

			}
			
			// "flush"
			if (contador > 0)
			{
				pool <<= (8-contador);
				fos.write( pool );
				contador = 0;
			}
			
			fos.close();
			
			File f = new File("/home/leonardo/workspace/Huffman/Util/arquivo.ilm");
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[(int)f.length()];
			fis.read(buffer);
			System.out.println("\n");
			for (int i = 0; i < buffer.length; i++) 
			{
				System.out.println(buffer[i]  & 0xff);
		       	/*for (int j = 0; j < 8; j++)
		       	{
		       		if ((buffer[i] & 1) == 1)
		       			System.out.print("1");
		       		else System.out.print("0");
		       		buffer[i] >>=1;
		       	}*/
			}
			
			fis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
