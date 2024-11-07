package TDACola;

import Excepciones.EmptyQueueException;

public class TDACola<E> implements Queue<E> {
	protected E[] cola;

	@SuppressWarnings("unchecked")
	public TDACola() {
		cola = (E[]) new Object[0];
	}

	@Override
	public int size() {
		return cola.length;
	}

	@Override
	public boolean isEmpty() {
		return cola.length == 0;
	}

	@Override
	public E front() throws EmptyQueueException {
		E element = null;
		if(!isEmpty()) {
			element = cola[0];

		} else {
			throw new EmptyQueueException("The Queue is empty.");
		}

		return element;
	}

	@Override
	public void enqueue(E element) {
		@SuppressWarnings("unchecked")
		E[] aux = (E[]) new Object[size() + 1];

		for(int i = 0; i < size(); i++) {
			aux[i] = cola[i];
		}

		aux[aux.length - 1] = element;
		cola = aux;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		E element = front();

		if(!isEmpty()) {
			@SuppressWarnings("unchecked")
			E[] aux = (E[]) new Object[size() - 1];


			for(int i = 1; i < size() - 1; i++) {
				aux[i] = cola[i];
			}

			cola = aux;

		} else {
			throw new EmptyQueueException("The Queue is empty.");
		}

		return element;
	}

}
