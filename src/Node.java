
public class Node {

	private Node esquerda;
	private Node  direita;
	private byte     data;
	
	public Node (Node esquerda, Node direita)
	{
		this.esquerda = esquerda;
		this.direita  = direita;
	}
	
	public Node (Node esquerda, Node direita, byte data)
	{
		this.esquerda = esquerda;
		this.direita  = direita;
		this.data     = data;
	}

	public Node getEsquerda() {
		return esquerda;
	}
	public void setEsquerda(Node esquerda) {
		this.esquerda = esquerda;
	}
	public Node getDireita() {
		return direita;
	}
	public void setDireita(Node direita) {
		this.direita = direita;
	}
	public byte getData() {
		return data;
	}
	public void setData(byte data) {
		this.data = data;
	}

}
