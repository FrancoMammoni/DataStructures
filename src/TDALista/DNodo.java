package TDALista;
public class DNodo<E> implements Position<E> {
protected E elem;
protected DNodo<E> next, prev;
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return elem;
	}

	public DNodo(E e) {
		elem = e;
		prev = null;
		next = null;
	}
	
	public DNodo(E e, DNodo<E> _next, DNodo<E> _prev) {
		elem = e;
		next = _next;
		prev = _prev;
	}
	
	public void setElement(E e) {
		elem = e;
	}
	
	public void setNext(DNodo<E> nodo) {
		next = nodo;
	}
	
	public void setPrev(DNodo<E> nodo) {
		prev = nodo;
	}
	
	public DNodo<E> getNext() {
		return next;
	}
	
	public DNodo<E> getPrev() {
		return prev;
	}
}


