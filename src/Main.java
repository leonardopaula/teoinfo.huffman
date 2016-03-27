import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

	public static void main(String[] args) {
		
		String file = "/home/leonardo/workspace/Huffman/Util/arquivo.txt";
		Huffman huff = new Huffman();
	
		try {
			Path p = FileSystems.getDefault().getPath("", file);
			byte[] fb = Files.readAllBytes(p);
			
			for (byte fl : fb)
			{
				// Adiciona no vetor auxiliar 
				huff.addData(fl);
			}

			// Computa estatisticas
			huff.updateEstatisticas();
			
			huff.montaArvore();
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
