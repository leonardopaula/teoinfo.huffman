
public class Data {

	private byte         data;
	private int    quantidade;
	private double percentual;
	private Node          noh;

	public void setNoh(Node noh) {
		this.noh = noh;
	}

	public Data (byte data, int quantidade, double percentual)
	{
		this.data       = data;
		this.quantidade = quantidade;
		this.percentual = percentual;
	}
	
	public Data (byte data, int quantidade, double percentual, Node noh)
	{
		this.data       = data;
		this.quantidade = quantidade;
		this.percentual = percentual;
		this.noh        = noh;
	}
	
	public Node getNoh() {
		return noh;
	}
	
	public byte getData() {
		return data;
	}
	public void setData(byte data) {
		this.data = data;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPercentual() {
		return percentual;
	}
	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}
}
