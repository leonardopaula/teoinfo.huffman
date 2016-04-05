import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Vector;

public class Huffman{

	// Adicionado para caminhamento
	private int[] frequencia      = null;
	private byte[] codigo_huffman = null;

	// Array indexado pelo código ASCII (https://rosettacode.org/wiki/Huffman_coding)
	public No montaArvore(int[] frequencia)
	{
		
		this.frequencia = frequencia;
		
		Vector<No> arvore = new Vector<No>();
		for (int i = 0; i < frequencia.length; i++ )
		{
			// Se possuir frequencia adiciona na fila (é uma folha - todos ascii são terminais)
			if (frequencia[i] != 0) 
			{
				arvore.addElement(new No(i, frequencia[i], null, null));
			}
			
		}
		// Vai pegando as menores frequencias e fazendo unindo
		while (arvore.size() > 1)
		{
			// Menor frequencia
			No c1 = arvore.remove(this.menorFrequencia(arvore));
			
			// Menor frequencia (Anterior foi removido)
			No c2 = arvore.remove(this.menorFrequencia(arvore));
			
			// Soma as frequencias e adiciona no vetor novamente em um nó intermediário
			No pai = new No('.', c1.getFrequencia() + c2.getFrequencia(), c1, c2);
			
			// Pai (União)
			arvore.addElement(pai);
		}

		// Raiz da árvore
		return arvore.elementAt(this.menorFrequencia(arvore));
	}
	
	// Retornar um array com os códigos por ASCII na chave (igual da frequencia)
	public String[] compacta(No raiz)
	{
		String[] s = new String[256];

		compacta(s, raiz, "");
		
		return s;
	}
	
	public void compacta(String[] s, No n, String compactado)
	{
		if (n.getEsq() == null && n.getDir() == null)
		{
			s[n.getData()] = compactado;
			
			// Sai da recursão
			return;
		}
		
		// Caminha para esquerda - 0
		compacta(s, n.getEsq(), compactado+'0');
		
		// Caminha para direita - 1
		compacta(s, n.getDir(), compactado+'1');
	}
	/*
	public byte[] compacta1(No raiz)
	{
		byte[] s = new byte[256]; // Tabela com os bytes do arquivo (Posição ascii)
		byte[] b = new byte[8];   // Temporária com o caminhamento - binário, representação do arquivo

		compacta1(s, raiz, b);
		
		return s;
	}
	
	public void compacta1(byte[] s, No n, byte compactado)
	{
		if (n.getEsq() == null && n.getDir() == null)
		{
			s[n.getData()] = compactado;
			
			// Sai da recursão
			return;
		}

		// Caminha para esquerda - 0
		compacta1(s, n.getEsq(), compactado.put((byte) 0));
		
		// Caminha para direita - 1
		compacta1(s, n.getDir(), compactado.put((byte) 1));
	}*/

	public int menorFrequencia(Vector<No> v)
	{
		int menor = 0;
		
		for (int i = 0; i < v.size(); i++)
		{
			if (v.get(i).getFrequencia() < v.get(menor).getFrequencia())
			{
				menor = i;
			}
		}
		
		return menor;
	}

}
