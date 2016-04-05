import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
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
		String file = "/home/leonardo/workspace/Huffman/Util/face-frontal-cow_318-74529.png";
		
		try {
			Path p    = FileSystems.getDefault().getPath("", file);
			byte[] fb = Files.readAllBytes(p);
			StandardCharsets.UTF_8.decode(ByteBuffer.wrap(fb)).toString();
			
			for (byte fl : fb)
			{
				// Adiciona no array de frequencias, utilizando o código ASCII como chave
				frequencias[ fl ] += 1; 
			}
			
			// Constrói árvore para compactar
			No arvoreHuffman = huf.montaArvore(frequencias);
			
			String[] s = huf.compacta(arvoreHuffman);
			for (int i = 0; i < fb.length; i++)
			{
				System.out.println(fb[i] + " -> " + s[fb[i]]);
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
