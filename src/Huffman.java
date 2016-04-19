import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
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
	
	public List<Integer> descompacta(No raiz, String entrada, int padding)
	{
		List<Integer> bbf = new ArrayList<Integer>();

		No no = raiz;
		String pool = "";

		//System.out.println(entrada);System.exit(0);
		for (int i = 0; i < entrada.length()+1; i++)
		{
			// Procura na árvore
			if (no.getEsq() == null && no.getDir() == null)
			{
				bbf.add(new Integer(no.getData()));
				//System.out.println("Achou: " + no.getData() + " " + pool);
				no = raiz;
				pool = "";
				
			}
			
			if (entrada.length() - padding <= i+1) 
				break;

			pool = pool + "" + entrada.charAt(i); 

			// Caminha para esquerda
			if (entrada.charAt(i) == '0')
			{
				no = no.getEsq();
			} else {
				no = no.getDir();
			}
		}
		
		//System.out.println(bbf);
		return bbf;
	}

}
