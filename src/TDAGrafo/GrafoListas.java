package TDAGrafo;

import java.util.Iterator;
import Excepciones.InvalidEdgeException;
import Excepciones.InvalidVertexException;
import TDALista.Position;
import TDALista.PositionList;
import TDALista.TDALista;

public class GrafoListas<V, E> implements Graph<V, E>{
	protected PositionList<Vertice<V>> listaNodos;
	protected PositionList<Arco<V, E>> listaArcos;

	public GrafoListas() {
		listaNodos = new TDALista<Vertice<V>>();
		listaArcos = new TDALista<Arco<V, E>>();
	}

	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> toRet = new TDALista<Vertex<V>>();
		for(Vertice<V> vertice : listaNodos) {
			toRet.addLast(vertice);
		}

		return toRet;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> toRet = new TDALista<Edge<E>>();
		for(Arco<V,E> arco : listaArcos) {
			toRet.addLast(arco);
		}

		return toRet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		if(v == null) throw new InvalidVertexException("El vertice pasado por parametro es nulo");

		Vertice<V> vert = (Vertice<V>) v;

		PositionList<Edge<E>> toRet = new TDALista<Edge<E>>();

		for(Edge<E> arco : vert.getListaArcosIncidentes()) {
			toRet.addLast(arco);
		}

		return toRet;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {

		if(v == null) throw new InvalidVertexException("");

		if(e == null) throw new InvalidEdgeException();


		@SuppressWarnings("unchecked")
		Arco<V, E> arco = (Arco<V, E>) e;
		Vertex<V> toRet = null;

		if(v.equals(arco.getV1()))
			toRet = arco.getV2();

		else if(v.equals(arco.getV2()))
			toRet = arco.getV1();

		else throw new InvalidEdgeException();

		return toRet;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		if(e == null) throw new InvalidEdgeException();

		@SuppressWarnings("unchecked")
		Arco<V, E> arco = (Arco<V, E>) e;

		@SuppressWarnings("unchecked")
		Vertex<V>[] toRet = new Vertice[2];

		toRet[0] = arco.getV1();
		toRet[1] = arco.getV2();

		return toRet;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		if(!(v != null && w != null)) throw new InvalidVertexException("");

		Vertice<V> _v = (Vertice<V>) v;

		Iterator<Edge> iterador = _v.getListaArcosIncidentes().iterator();

		boolean toRet = false;
		while(iterador.hasNext() && !toRet) {
			Arco<V,E> actual = (Arco<V,E>) iterador.next();
			if(actual.getV1().equals(w) || actual.getV2().equals(w))
				toRet = true;
		}

		return toRet;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		if(v == null) throw new InvalidVertexException("");

		Vertice<V> _v = (Vertice<V>) v;
		V toRet = _v.getRotulo();
		_v.setRotulo(x);

		return toRet;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V> toRet = new Vertice<V>();
		toRet.setRotulo(x);
		this.listaNodos.addLast(toRet);
		try {

			toRet.setPosicionEnListaVertices(listaNodos.last());

		} catch(Exception e) { System.out.println("Exception at method: insertVertex(V x)...");}
		return toRet;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		if(v == null || w == null) throw new InvalidVertexException("");

		Arco<V, E> toRet = new Arco<V,E>();
		Vertice<V> _v = (Vertice<V>) v;
		Vertice<V> _w = (Vertice<V>) w;


		toRet.setV1(_v);
		toRet.setV2(_w);
		toRet.setRotulo(e);

		_v.addArcoIncidente(toRet);
		_w.addArcoIncidente(toRet);

		this.listaArcos.addLast(toRet);

		try {
			toRet.setPosicionEnListaArco(listaArcos.last());
		} catch(Exception ex) { System.out.println("Exception at method: insertEdge()...");}

		return toRet;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		if(v == null) throw new InvalidVertexException("");

		Vertice<V> vertice = (Vertice<V>) v;

		for(Edge<E> arco : vertice.getListaArcosIncidentes()) {
			Arco<V, E> actual = (Arco<V,E>) arco;
			try {
				// Remuevo el arco incidente de v de la lista de arcos del grafo;
				this.listaArcos.remove(actual.getPosicionEnListaArco());

				// Remuevo el arco de la lista de arcos incidentes del vertice opuesto; ????
				Vertice<V> opuesto = (Vertice<V>) this.opposite(vertice, actual);
				Iterator<Position<Edge>> it1 = opuesto.getListaArcosIncidentes().positions().iterator();
				boolean encontre1 = false;

				while(it1.hasNext() && !encontre1) {
					Position<Edge> actual2 = it1.next();
					if(actual.element().equals(arco)) {
						try {
							opuesto.getListaArcosIncidentes().remove(actual2);
						} catch(Exception ex) {}
						encontre1 = true;
					}
				}
			} catch(Exception e) { System.out.println("Exception at method: removeVertex(Vertex<V> v)"); }
		}

		// Remuevo el vertice de la lista de vertices;
		try {
			this.listaNodos.remove(vertice.getPosicionEnListaVertices());
		} catch (Exception e) {System.out.println("Exception at method: removeVertex(Vertex<V> v), 2nd try{}");}



		return vertice.getRotulo();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		if(e == null) throw new InvalidEdgeException();

		Arco<V, E> arco = (Arco<V,E>) e;
		Vertice<V> v1 = arco.getV1();
		Vertice<V> v2 = arco.getV2();

		// Remuevo el arco de las listas de incidencias de los vertices: O(grado(v1) + grado(v2))
		Iterator<Position<Edge>> it1 = v1.getListaArcosIncidentes().positions().iterator();
		boolean encontre1 = false;

		while(it1.hasNext() && !encontre1) {
			Position<Edge> actual = it1.next();
			if(actual.element().equals(arco)) {
				try {
					v1.getListaArcosIncidentes().remove(actual);
				} catch(Exception ex) {}
				encontre1 = true;
			}
		}

		Iterator<Position<Edge>> it2 = v2.getListaArcosIncidentes().positions().iterator();
		boolean encontre2 = false;

		while(it2.hasNext() && !encontre2) {
			Position<Edge> actual = it2.next();
			if(actual.element().equals(arco)) {
				try {
					v2.getListaArcosIncidentes().remove(actual);
				} catch(Exception ex) {}
				encontre2 = true;
			}
		}

		// Remuevo el arco de la lista de arcos;
		try {
			this.listaArcos.remove(arco.getPosicionEnListaArco());
		} catch(Exception ex) {}

		return arco.getRotulo();
	}

}
