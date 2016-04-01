import java.util.Vector;

public class Huffman{

	// Array indexado pelo código ASCII (https://rosettacode.org/wiki/Huffman_coding)
	public No montaArvore(int[] frequencia)
	{
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
	
	public StringBuffer compacta(No raiz, StringBuffer arquivo)
	{
		compacta(arquivo, raiz, "");
		
		return arquivo;
	}
	
	public void compacta(StringBuffer s, No n, String compactado)
	{
		if (n.getEsq() == null && n.getDir() == null)
		{
			//s[n.getAscii_code()] = compactado;
			
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

}
