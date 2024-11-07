package TDAPila;
import java.util.EmptyStackException;


public class TDAPila<E> implements Stack<E> {

	protected E[] pila;
	
	@SuppressWarnings("unchecked")
	public TDAPila() {
		pila = (E[]) new Object[0];
	} 
	
	@Override
	public int size() {
		return pila.length;
	}

	@Override
	public boolean isEmpty() {
		
		return pila.length == 0;
	}

	@Override
	public E top() throws EmptyStackException {
		E element = null;
		
		if(!isEmpty()) {
			element = pila[pila.length - 1];
		} else {
			throw new EmptyStackException(); 
		}
		return element;
	}

	@Override
	public void push(E element) {
		
		if(element != null) {
			@SuppressWarnings("unchecked")
			E[] aux = (E[]) new Object[size() + 1];
			
			for(int i = 0; i < pila.length; i++) {
				aux[i] = pila[i];
			}
			
			aux[aux.length - 1] = element;
			
			pila = aux;
		}
	}

	@Override
	public E pop() throws EmptyStackException {
		E element = null;
		if(!isEmpty()) {
			element = pila[pila.length - 1];
			
			@SuppressWarnings("unchecked")
			E[] aux = (E[]) new Object[pila.length - 1];
			
			for(int i = 0; i < aux.length; i++) {
				aux[i] = pila[i];
			}
			
			pila = aux;
		} else {
			throw new EmptyStackException();
		}
			return element;
	}

}
