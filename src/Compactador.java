import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;

import javax.swing.JFileChooser;

public class Compactador {

	public Compactador()
	{
		final JFileChooser fc = new JFileChooser();
		int choice = fc.showOpenDialog(fc);
		if (choice != JFileChooser.APPROVE_OPTION) return;

		File chosenFile = fc.getSelectedFile();
		
		compacta(chosenFile);
	}
	
	public void compacta(File arquivo)
	{
		// Tamanho do alfabeto [ASCII]
		int[] frequencias = new int[256];
	
		// Huffman
		Huffman huf = new Huffman();
			
		// Caminho do arquivo a ser lido
		String file = arquivo.getPath();
			
		// Padding byte
		int padding = 0;
			
		try {
			Path p    = FileSystems.getDefault().getPath("", file);
			byte[] fb = Files.readAllBytes(p);
			int tamanho_dicionario = 0;
				
			for (byte fl : fb)
			{
				// Adiciona no array de frequencias, utilizando o código ASCII como chave
				if (frequencias[ fl & 0xff ] == 0) tamanho_dicionario++;
				frequencias[ fl & 0xff ] += 1;
			}

			frequencias = huf.frequenciaRefatorada(frequencias);
			// Constrói árvore para compactar
			No arvoreHuffman = huf.montaArvore(frequencias);

			FileOutputStream fos = new FileOutputStream(arquivo.getPath() + ".ilm");
		
			int contador = 0;
			int pool = 0;

			/* Cabeçalho do arquivo */
			System.out.println("Dicionario: " +tamanho_dicionario);
			fos.write(tamanho_dicionario);
			

			for(int i = 0; i < frequencias.length; i++)
			{
				if (frequencias[i] > 0 )
				{
					System.out.println(i + " => " + frequencias[i]);
					fos.write(i);
					fos.write(frequencias[i]);
				}
			}

			String[] s = huf.compacta(arvoreHuffman);
			for (int i = 0; i < fb.length; i++)
			{

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
			System.out.println("Padding: " + padding);
			fos.write(padding);
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
