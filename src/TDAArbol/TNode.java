package TDAArbol;
import TDALista.Position;
import TDALista.PositionList;
import TDALista.TDALista;

public class TNode<E> implements Position<E>{
protected E elem;
protected TNode<E> padre;
protected PositionList<TNode<E>> hijos;

	public TNode(E element) {
		padre = null;
		hijos = new TDALista<TNode<E>>();
		elem = element;
	}
	
	public TNode(E elem, TNode<E> papi) {
		this.elem = elem;
		padre = papi;
		hijos = new TDALista<TNode<E>>();
 	}

	@Override
	public E element() {
		return elem;
	}
	
	public TNode<E> getPadre() {
		return padre;
	}
	
	public PositionList<TNode<E>> getHijos() {
		return hijos;
	}
	
	public void setElement(E element) {
		elem = element;
	} 
	
	public void setPadre(TNode<E> n) {
		padre = n;
	}
	
	
}
