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

public class Main {

	public static void main(String[] args) {
		
		// Tamanho do alfabeto [ASCII]
		int[] frequencias = new int[256];

		// Huffman
		Huffman huf = new Huffman();
		
		// Caminho do arquivo a ser lido
		String file = "/home/leonardo/workspace/Huffman/Util/imgMINI1.jpg";
		
		// Padding byte
		int padding = 0;
		
		try {
			Path p    = FileSystems.getDefault().getPath("", file);
			byte[] fb = Files.readAllBytes(p);
			
			for (byte fl : fb)
			{
				// Adiciona no array de frequencias, utilizando o código ASCII como chave
				frequencias[ fl & 0xff ] += 1;
				//System.out.print((fl & 0xff) + ", ");
			}
			
			System.out.println("");
			// Constrói árvore para compactar
			No arvoreHuffman = huf.montaArvore(frequencias);
			
			FileOutputStream fos = new FileOutputStream("/home/leonardo/workspace/Huffman/Util/arquivo.ilm");
			String[] s = huf.compacta(arvoreHuffman);

			int contador = 0;
			int pool = 0;

			for (int i = 0; i < fb.length; i++)
			{
				//System.out.println(fb[i] + " -> " + s[fb[i] & 0xff] + " : ");
				//System.out.println(s[fb[i] & 0xff]);
				//System.out.println(s[fb[i] & 0xff] + " = " + (fb[i] & 0xff));
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
				
				padding = 8 - contador;
				contador = 0;
			}
			//System.out.println(padding);
			fos.close();
			
			File f = new File("/home/leonardo/workspace/Huffman/Util/arquivo.ilm");
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream in = new BufferedInputStream(fis);
			contador   = 0;
			int buffer = 0;
			StringBuilder sb = new StringBuilder();

			do {
				if (contador == 0)
				{
					buffer   = in.read();
					contador = 8;
				}
				
				if (buffer > 0)
				{
					contador--;
					boolean bit = ((buffer >> contador) & 1) == 1;
					//if (bit) System.out.print("1"); else System.out.print("0");
					sb.append((bit) ? "1" : "0");
				}
				
			}while(buffer > 0);

			List<Integer> saida = huf.descompacta(arvoreHuffman, sb.toString(), padding);
			FileOutputStream out = new FileOutputStream("/home/leonardo/workspace/Huffman/Util/saida.jpg");
			//System.out.println(saida);
			for(int i = 0; i < saida.size(); i++)
			{
				out.write(saida.get(i));
			}
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
