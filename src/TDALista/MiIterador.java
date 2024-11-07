package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Excepciones.EmptyListException;

public class MiIterador<E> implements Iterator<E> {
protected Position<E> cursor;
protected PositionList<E> lista;
	

	public MiIterador(PositionList<E> _lista) {
		 this.lista = _lista;
		 if(lista.isEmpty())
		 	cursor = null;
		else
			try {
				cursor = lista.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
	}

	@Override
	public boolean hasNext() {
		return cursor != null;
	}

	@Override
	public E next() {
		E resultado = null;
		try {
		if (this.cursor!=null) {
			resultado = this.cursor.element();
			if( this.cursor == this.lista.last() ) {
				this.cursor = null;
			} else {
				this.cursor = this.lista.next(this.cursor);
			}
		} else {
			throw new NoSuchElementException();
		}
		}
		catch(Exception e) {
			
		}
		return resultado;
	}

}
