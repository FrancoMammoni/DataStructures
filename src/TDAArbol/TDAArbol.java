package TDAArbol;
import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidOperationException;
import Excepciones.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDALista.TDALista;
import TDAMapa.TDAMapa;

public class TDAArbol<E> implements Tree<E>{
	protected TNode<E> root;
	protected int size;

	public TDAArbol() {
		size = 0;
		root = null;
	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> lista = new TDALista<E>();
		for(Position<E> pos : this.positions()) {
			lista.addLast(pos.element());
		}
		return lista.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {

		PositionList<Position<E>> toRet = new TDALista<Position<E>>();
		if(!this.isEmpty()) {
			preOrdenPositions(root, toRet);
		}
		return toRet;
	}

	private void preOrdenPositions(TNode<E> n, PositionList<Position<E>> lista) {
		lista.addLast(n);

		for(TNode<E> nodo: n.getHijos()) {
			preOrdenPositions(nodo, lista);
		}
	}

	private TNode<E> checkPosition(Position<E> pos) throws InvalidPositionException {
		TNode<E> toRet = null;
		if(isEmpty() || pos == null)
			throw new InvalidPositionException();

		try {
			toRet = (TNode<E>) pos;
		} catch(Exception e) {
			throw new InvalidPositionException();
		}
		return toRet;
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		E toRet = nodo.element();
		nodo.setElement(e);
		return toRet;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException();

		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNode<E> nodo = checkPosition(v);
		if(nodo.equals(root))
			throw new BoundaryViolationException();

		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		PositionList<Position<E>> toRet = new TDALista<Position<E>>();

		for(Position<E> pos : nodo.getHijos()) {
			toRet.addLast(pos);
		}

		return toRet;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return !isExternal(nodo);
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);

		return nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(v);
		return nodo.equals(root);
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(!isEmpty()) 
			throw new InvalidOperationException();
		root = new TNode<E>(e);
		size++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(p);
		TNode<E> hijo = new TNode<E>(e);

		hijo.setPadre(nodo);

		nodo.getHijos().addFirst(hijo);
		size++;

		return hijo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(p);
		TNode<E> hijo = new TNode<E>(e);

		hijo.setPadre(nodo);

		nodo.getHijos().addLast(hijo);
		size++;

		return hijo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNode<E> padre = checkPosition(p);
		TNode<E> hermano = checkPosition(rb);
		TNode<E> hijo = new TNode<E>(e);

		hijo.setPadre(padre);

		Iterator<Position<TNode<E>>> iterador = padre.getHijos().positions().iterator();
		boolean corte = false;
		Position<TNode<E>> posHermano = null;

		while(iterador.hasNext() && !corte) {
			Position<TNode<E>> actual = iterador.next();
			if(actual.element().equals(hermano)) {
				corte = true;
				posHermano = actual;
			}
		}

		if(!corte)
			throw new InvalidPositionException();

		padre.getHijos().addBefore(posHermano, hijo);
		size++;

		return hijo;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNode<E> padre = checkPosition(p);
		TNode<E> hermano = checkPosition(lb);
		TNode<E> hijo = new TNode<E>(e);

		hijo.setPadre(padre);

		Iterator<Position<TNode<E>>> iterador = padre.getHijos().positions().iterator();
		boolean corte = false;
		Position<TNode<E>> posHermano = null;

		while(iterador.hasNext() && !corte) {
			Position<TNode<E>> actual = iterador.next();
			if(actual.element().equals(hermano)) {
				corte = true;
				posHermano = actual;
			}
		}

		if(!corte)
			throw new InvalidPositionException();

		padre.getHijos().addAfter(posHermano, hijo);
		size++;

		return hijo;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(p);

		// Si es Raiz:
			if(isRoot(nodo)) {
				if(isInternal(nodo)) {
					throw new InvalidPositionException();
				} else {
					root = null;
					size--;
				}
			} else {
				if(isInternal(nodo)) {
					throw new InvalidPositionException();
				} else {
					TNode<E> padre = nodo.getPadre();

					Iterator<Position<TNode<E>>> iterator = padre.getHijos().positions().iterator();
					Position<TNode<E>> posNodo = null;
					boolean corte = false;

					while(iterator.hasNext() && !corte) {
						posNodo = iterator.next();
						if(posNodo.element().equals(nodo)) {
							padre.getHijos().remove(posNodo);
							size--;
							corte = true;
						}
					}

					if(!corte) {
						throw new InvalidPositionException();
					}
				}

			}

	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(p);

		//Si es Raiz:
		if(isRoot(nodo)) {
			if(nodo.getHijos().size() == 1) {
				try {
					Position<TNode<E>> pos = nodo.getHijos().first();
					pos.element().setPadre(null);
					root = pos.element();
				} catch (EmptyListException e) {
					e.printStackTrace();
				}
			} else { throw new InvalidPositionException();}

			//Si no es Raiz:
		} else {
			if(isInternal(nodo)) {

				// Eliminamos al nodo de la Lista del Padre:
				Iterator<Position<TNode<E>>> iterator = nodo.getPadre().getHijos().positions().iterator();
				boolean corte = false;
				Position<TNode<E>> actual;
				while(iterator.hasNext() && !corte) {
					actual = iterator.next();
					if(actual.element() == nodo) {
						nodo.getPadre().getHijos().remove(actual);
						corte = true;
						size--;
					}
				}

				if(!corte) {throw new InvalidPositionException();}

				// Añadimos los hijos del nodo a la lista de hijos del padre de nodo:
				for(TNode<E> n : nodo.getHijos()) {
					n.setPadre(nodo.getPadre());
					nodo.getPadre().getHijos().addLast(n);
				}

			} else {throw new InvalidPositionException();}
		}
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		TNode<E> nodo = checkPosition(p);
		if(isInternal(nodo)) {
			this.removeInternalNode(nodo);
		} else {
			this.removeExternalNode(nodo);
		}
	}


	// Ejercicio 2;

	/*
	 * Este método deberá eliminar del árbol receptor del mensaje a la posición p siempre que p sea el último hijo
 	(de izq a der) de su padre. La raíz no se considera último hijo, en este caso el método deberá lanzar
 	InvalidOperationException.
	 */
	public  void eliminarUltimoHijo(Position<E> p) throws InvalidPositionException, InvalidOperationException {
		TNode<E> nodo = checkPosition(p);
		if(isRoot(nodo)) {
			throw new InvalidOperationException();
		} else {
			TNode<E> padre = nodo.getPadre();
			// Comprobamos que el nodo es el ultimo hijo de su padre, en caso de que lo sea, lo 
			// eliminamos del arbol...
			try {
				if(padre.getHijos().last().element() == nodo) {
					if(isInternal(nodo)) {
						this.removeInternalNode(nodo); // O(n)
					} else {
						this.removeExternalNode(nodo); // O(n)
					}
				}
			} catch(Exception e) {
				throw new InvalidOperationException();
			}	
		}
	}
	// EL orden del metodo es O(n);

	// **** EJERCICIOS EXTRA ****

	public int sizeSubarbol(Position<E> p) throws InvalidPositionException {
		int toRet = 0;
		TNode<E> nodo = checkPosition(p);

		toRet += preOrdenExtra1(nodo);

		return toRet;
	}


	public int preOrdenExtra1(TNode<E> p) {
		int toRet = 1;


		for(TNode<E> n : p.getHijos()) {
			toRet += preOrdenExtra1(n);
		}



		return toRet;
	}

	public TDAMapa<Position<E>, Integer> mapSizeSubarboles() throws InvalidPositionException {
		TDAMapa<Position<E>, Integer> toRet = new TDAMapa<Position<E>, Integer>();

		preOrdenExtra2(toRet, this.root);

		return toRet;
	}

	private void preOrdenExtra2(TDAMapa<Position<E>, Integer> m, Position<E> pos) {
		try {
			TNode<E> nodo = this.checkPosition(pos);

			m.put(nodo, this.sizeSubarbol(nodo));

			for(Position<E> p : nodo.getHijos()) {
				preOrdenExtra2(m, p);
			}


		} catch(Exception e) {System.out.println(e.getMessage());}
	}


	public int podarSubarbol(Position<E> p) throws InvalidPositionException {
		int toRet = this.podarSubarbol(p);
		TNode<E> nodo = this.checkPosition(p);

		size -= toRet;

		// Elimino a p de la lista de su padre.
		if(isRoot(p)) {
			this.root = null;
		} else {
			TNode<E> padre = nodo.getPadre();
			Iterator<Position<TNode<E>>> iterator = padre.getHijos().positions().iterator();
			boolean corte = false;

			while(iterator.hasNext() && !corte) {
				Position<TNode<E>> actual = iterator.next();
				if(actual.element().equals(nodo)) {
					padre.getHijos().remove(actual);
				}
			}
		}

		return toRet;
	}

	public void cambiarRotulo(E e, E f) {
		Iterator<Position<E>> iterator = this.positions().iterator();
		try {

			while(iterator.hasNext()) {
				TNode<E> actual = checkPosition(iterator.next());
				if(actual.element().equals(e)) {
					actual.setElement(f);
				}
			}

		} catch(Exception ex) {System.out.println(ex.getMessage());}
	}

	public void insertarMasivo(E e, int x) {
		PositionList<Position<E>> lista = new TDALista<Position<E>>();
		Iterator<Position<E>> iterator = this.positions().iterator();

		try {

			while(iterator.hasNext()) {
				TNode<E> actual = this.checkPosition(iterator.next());
				if(nivel(actual) == x) {
					lista.addLast(actual);
				}
			}

			for(Position<E> p : lista) {
				this.removeNode(p);
			}

		} catch(Exception ex) {System.out.println(ex.getMessage());}

	}


	private int nivel(Position<E> pos) {
		int toRet = 1;
		try {

			TNode<E> actual = this.checkPosition(pos);
			while(!actual.equals(this.root)) {
				actual = actual.getPadre();
				toRet++;
			}


		} catch(Exception ex) {System.out.println(ex.getMessage());}


		return toRet;
	}


	public void ejercicio2(Tree<Character> A, int x, char c) {
		try {

			Position<Character> raiz = A.root();
			postOrdenE2(raiz, x, c, A);


		} catch (Exception e) {System.out.println(e.getMessage());}
	}

	@SuppressWarnings("unchecked")
	protected void postOrdenE2(Position<Character> p, int x, char c, Tree<Character> A) {
		try {

			TNode<Character> nodo = (TNode<Character>) p;
			for(TNode<Character> n: nodo.getHijos()) {
				postOrdenE2(n, x, c, A);
			}

			if(A.isExternal(nodo) && nivel((Position<E>) nodo) == x) {
				nodo.getHijos().addLast(new TNode<Character>(c));
			}
		} catch (Exception e) {System.out.println(e.getMessage());}
	}



}
