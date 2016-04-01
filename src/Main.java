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
		String file = "/home/leonardo/workspace/Huff/Util/arquivo.txt";
		
		try {
			Path p    = FileSystems.getDefault().getPath("", file);
			byte[] fb = Files.readAllBytes(p);
			StringBuffer arquivo = new StringBuffer();
			arquivo.append(new String (fb));
			
			for (byte fl : fb)
			{
				// Adiciona no array de frequencias, utilizando o código ASCII como chave
				frequencias[ fl ] += 1; 
			}
			
			// Constrói árvore para compactar
			No arvoreHuffman = huf.montaArvore(frequencias);
			
			StringBuffer s = huf.compacta(arvoreHuffman, arquivo);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
