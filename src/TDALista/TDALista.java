package TDALista;
import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

public class TDALista<E> implements PositionList<E>, Iterable<E> {
	protected int size;
	protected DNodo<E> head, tail;

	public TDALista() {
		size = 0;
		head = new DNodo<E>(null, null, null);
		tail = new DNodo<E>(null, null, null);
		head.setNext(tail);
		tail.setPrev(head);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException();
		return head.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException();
		return tail.getPrev();
	}

	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if(p == null) {
			throw new InvalidPositionException();
		}
		if(p == head) {
			throw new InvalidPositionException();
		}
		if(p == tail) {
			throw new InvalidPositionException();
		}
		try {
			DNodo<E> temp = (DNodo<E>) p;//casteo para devolver la posicion casteada a DNode
			if((temp.getPrev() == null) || (temp.getNext() == null)) {
				throw new InvalidPositionException();
			}
			return temp;
		}
		catch(ClassCastException e) {
			throw new InvalidPositionException();
		}
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if(n.getNext() == tail)
			throw new BoundaryViolationException();
		return n.getNext();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if(n.getPrev()==head)
			throw new BoundaryViolationException();
		return n.getPrev();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> nodo = new DNodo<E>(element, head.getNext(), head);
		head.setNext(nodo);
		nodo.getNext().setPrev(nodo);
		size++;
	}

	@Override
	public void addLast(E element) {
		DNodo<E> nodo = new DNodo<E>(element, tail, tail.getPrev());
		tail.setPrev(nodo);
		nodo.getPrev().setNext(nodo);
		size++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		size++;
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nodo = new DNodo<E>(element);
		nodo.setNext(pos.getNext());
		nodo.setPrev(pos);
		pos.setNext(nodo);
		nodo.getNext().setPrev(nodo);

	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n = this.checkPosition(p);

		if (this.isEmpty()) {
			throw new InvalidPositionException();
		}

		DNodo<E> currentNode = new DNodo<E>(element, n, n.getPrev());
		n.setPrev(currentNode);
		currentNode.getPrev().setNext(currentNode);
		size++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		if(isEmpty()) {
			throw new InvalidPositionException();
		}

		else {
			n.getPrev().setNext(n.getNext());
			n.getNext().setPrev(n.getPrev());
			size--;
		}

		return n.element();
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> nodo = checkPosition(p);
		E retorno = nodo.element();
		if(isEmpty()) {
			throw new InvalidPositionException();
		} else 
			nodo.setElement(element);

		return retorno;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> retorno = new MiIterador<E>(this);
		return retorno;
	}

	@Override
	public Iterable<Position<E>> positions() {
		TDALista<Position<E>> resultado = new TDALista<Position<E>>();
		if(!this.isEmpty()) {
			try {
				Position<E> pos = this.first();
				while(pos!=this.last()) {
					resultado.addLast(pos);
					pos = this.next(pos);
				}
				resultado.addLast(pos);
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
	}

	/*
	 *  Ejercicio 2:
 Agregue un método a la lista programada en el ejercicio 1 tal que reciba dos elementos, e1 y e2, y modifique la lista
 receptora del mensaje de la siguiente manera:
 ● Deberáagregar ae1comosegundoelemento de la lista;
 ● Deberáagregar ae2comoante-último elemento de la lista;
 Considera casos especiales: ¿Qué sucede si la lista está vacía?, ¿Qué sucede si la lista tiene un elemento?. Recuerde
 que tiene total acceso a la estructura.
	 */

	public void ejercicio2(E e1, E e2) {
		DNodo<E> n1 = new DNodo<E>(e1);
		DNodo<E> n2 = new DNodo<E>(e2);
		if(!isEmpty() && size() > 1) {
			// Agrego a e1 como segundo elemento de la lista:
			n1.setNext(head.getNext().getNext());
			n1.setPrev(head.getNext());
			n1.getNext().setPrev(n1);
			n1.getPrev().setNext(n1);
			size++;
			// Agrego a e2 como anteultimo de la lista:
			n2.setPrev(tail.getPrev().getPrev());
			n2.setNext(tail.getPrev());
			n2.getPrev().setNext(n2);
			n2.getNext().setPrev(n2);
			size++;
		} else if(isEmpty()) {
			// Si la lista esta vacia, agrego e1 y agrego e2 como anteultimo.
			this.addFirst(e1);
			this.addFirst(e2);
			size = size + 2;
		} else if(size() == 1) {
			// Si la lista tiene un solo elemento, agrego a e1 como 2do y a e2 como anteultimo;
			this.addLast(e2);
			this.addLast(e1);
			size = size + 2;
		}

	}

	public String toString() {
		String ret = "[ ";
		for(E elem: this) {
			ret = ret + elem + ", ";
		}
		ret += "]";
		return ret;
	}

}
