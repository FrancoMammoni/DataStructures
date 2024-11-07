package TDAGrafo;

import TDALista.Position;

public class Arco<V, E> implements Edge<E> {
protected E rotuloArco;
protected Position<Arco<V,E>> posicionEnListaArco;
protected Vertice<V> v1, v2;

	public Arco() {
		this.rotuloArco = null;
		this.posicionEnListaArco = null;
		this.v1 = null;
		this.v2 = null;
	}

	@Override
	public E element() {
		return rotuloArco;
	}

	public Vertice<V> getV1() {
		return v1;
	}
	
	public Vertice<V> getV2() {
		return v2;
	}
	
	public E getRotulo() {
		return rotuloArco;
	}
	
	public Position<Arco<V,E>> getPosicionEnListaArco() {
		return this.posicionEnListaArco;
	}
	
	public void setRotulo(E rot) {
		this.rotuloArco = rot;
	}
	
	public void setV1(Vertice<V> v) {
		this.v1 = v;
	}
	
	public void setV2(Vertice<V> v) {
		this.v2 = v;
	}
	
	public void setPosicionEnListaArco(Position<Arco<V, E>> pos) {
		this.posicionEnListaArco = pos;
	}
}
