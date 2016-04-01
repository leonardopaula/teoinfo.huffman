
public class No {

	private No esq;
	private No  dir;
	private int    data;
	private int frequencia;
	
	public No (int data, int frequencia, No esquerda, No direita)
	{
		this.setFrequencia(frequencia);
		this.esq = esquerda;
		this.dir  = direita;
		this.data     = data;
	}

	public No getEsq() {
		return esq;
	}
	public void setEsq(No esquerda) {
		this.esq = esquerda;
	}
	public No getDir() {
		return dir;
	}
	public void setDir(No direita) {
		this.dir = direita;
	}
	public int getData() {
		return data;
	}
	public void setData(byte data) {
		this.data = data;
	}

	public int getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}

}
