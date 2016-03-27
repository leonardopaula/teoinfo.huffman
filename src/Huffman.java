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
			
		
			//Data d = new Data(this);
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
