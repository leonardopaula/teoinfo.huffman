import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;

public class Descompactador {

	public Descompactador()
	{
		final JFileChooser fc = new JFileChooser();
		int choice = fc.showOpenDialog(fc);
		if (choice != JFileChooser.APPROVE_OPTION) return;

		File chosenFile = fc.getSelectedFile();
		this.descompacta(chosenFile);
	}
	
	public void descompacta(File arquivo)
	{
		// Tamanho do alfabeto [ASCII]
		int[] frequencias = new int[256];
		
		int padding = 0;

		// Huffman
		Huffman huf = new Huffman();

		int tamanho_dicionario = 0;

		try {
			FileInputStream fis = new FileInputStream(arquivo);
			BufferedInputStream in = new BufferedInputStream(fis);
			
			in.skip(arquivo.length()-1);
			padding = in.read();
			padding = padding; // Tamanho do padding no fim do arquivo
			System.out.println();
			
			fis = new FileInputStream(arquivo);
			in = new BufferedInputStream(fis);

			System.out.println("Padding:" + padding);
			tamanho_dicionario = in.read();
			tamanho_dicionario = (tamanho_dicionario == 0) ? 256 : tamanho_dicionario; // Se tiver 256 símbolos

			System.out.println("Dicionario: " + tamanho_dicionario);
			for(int i = 0; i < tamanho_dicionario; i++ )
			{
				int ii = in.read();
				int freq = in.read();
				frequencias[ii] = (freq == 0) ? 256 : freq;
				System.out.println(ii +" => "+freq);
			}

			// Constrói árvore para descompactar
			No arvoreHuffman = huf.montaArvore(frequencias);

			int total = 0;
			int contador   = 0;
			int buffer = 0;
			StringBuilder sb = new StringBuilder();
	
			do {
				if (contador == 0)
				{
					total++;
					buffer   = in.read();
					//System.out.println(buffer);
					contador = 8;
				}
				
				if (buffer > -1)
				{
					contador--;
					boolean bit = ((buffer >> contador) & 1) == 1;
					//if (bit) System.out.print("1"); else System.out.print("0");
					sb.append((bit) ? "1" : "0");
				}
				
			}while(buffer > -1);
	
			List<Integer> saida = huf.descompacta(arvoreHuffman, sb.toString(), padding);

			String sarq = arquivo.getPath().replace(".ilm", ""); // Nome original
			String ext  = sarq.substring(sarq.lastIndexOf(".") + 1);
			String nome = sarq.replace(ext, "output" + "." + ext);

			System.out.println(nome);
			FileOutputStream out = new FileOutputStream(nome);
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
