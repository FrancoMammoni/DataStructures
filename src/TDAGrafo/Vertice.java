package TDAGrafo;

import TDALista.Position;
import TDALista.PositionList;
import TDALista.TDALista;

public class Vertice<V> implements Vertex<V> {
protected V rotulo;
protected Position<Vertice<V>> posicionEnListaVertices;
@SuppressWarnings("rawtypes")
protected PositionList<Edge> listaArcosIncidentes;
protected boolean visitado; 

	@SuppressWarnings("rawtypes")
	public Vertice() {
		rotulo = null;
		posicionEnListaVertices = null;
		listaArcosIncidentes = new TDALista<Edge>();
		visitado = false;
	}

	@Override
	public V element() {
		return rotulo;
	}
	
	public V getRotulo() {
		return this.rotulo;
	}
	
	public Position<Vertice<V>> getPosicionEnListaVertices() {
		return this.posicionEnListaVertices;
	}
	
	@SuppressWarnings("rawtypes")
	public PositionList<Edge> getListaArcosIncidentes() {
		return listaArcosIncidentes;
	}
	
	public void setRotulo(V rot) {
		this.rotulo = rot;
	}

	public void setPosicionEnListaVertices(Position<Vertice<V>> pos) {
		this.posicionEnListaVertices = pos;
	}
	
	public void addArcoIncidente(@SuppressWarnings("rawtypes") Edge arco) {
		this.listaArcosIncidentes.addLast(arco);
	}
	
	public boolean getVisitado() {
		return visitado;
	}
	
	public void setVisitado(Boolean b) {
		this.visitado = b;
	}
	
	public String toString() {
		return rotulo.toString();
	}
}
