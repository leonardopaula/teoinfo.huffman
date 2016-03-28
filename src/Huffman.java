import java.util.Vector;

public class Huffman {

	private Vector<Data> data = new Vector<Data>();
	private int tamanho_data  = 0;
	private Node raiz         = null;

	public void addData(byte b)
	{
		// Procura byte
		int tem = this.search(b);
		
		if (tem >= 0) // Atualiza a quantidade
		{
			int qtde = this.data.elementAt(tem).getQuantidade() + 1;
			this.data.elementAt(tem).setQuantidade(qtde);
		} else { // Insere novo item
			Data d = new Data(b, 1, 0.0);
			this.data.addElement(d);
		}
		this.tamanho_data++;
	}
	
	public void montaArvore()
	{
		int[] par = getPar();
		do 
		{
			Node esq = null;
			Node dir = null;
			
			Data d1 = this.data.remove(par[0]);
			Data d2 = this.data.remove(par[1]);

			// Verifica se é folha (menor)
			if (d1.getNoh() == null)
			{
				esq = new Node(null, null, d1.getData()); 
			}
			
			if (d2.getNoh() == null)
			{
				dir = new Node(null, null, d2.getData()); 
			}

			Node pai = new Node(esq, dir);
			
			Data d = new Data(esq.getData(), -1, d1.getPercentual() + d2.getPercentual());
			this.data.addElement(d);
			
		}while(par != null);
	}
	
	public int[] getPar()
	{
		int[] ret = new int[2];
		
		ret[0] = getMenor(-1);
		ret[1] = getMenor(ret[0]);

		return ret;
	}
	
	public int getMenor(int except)
	{
		int menor;

		if (except == 0) menor = 1;
		else menor = 0;

		for (int i = 0; i < this.data.size(); i++)
		{
			if (i != except)
			{
				menor = (this.data.elementAt(i).getPercentual() < this.data.elementAt(menor).getPercentual()) 
						? i
						: menor;
			}
		}

		return menor;
	}
	
	public void updateEstatisticas()
	{
		for (Data d : this.data)
		{
			double perc = (double)d.getQuantidade() / (double)this.tamanho_data;
			d.setPercentual(perc);
		}
	}
	
	public int search(byte p)
	{
		for (int i = 0; i < this.data.size(); i++)
		{
			if (p == this.data.get(i).getData())
			{
				return i;
			}
		}
		return -1;
	}
	

}
