package TDAPila;

import java.util.EmptyStackException;

/**
 * Interface Stack
 * @author C�tedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computaci�n, UNS.
 */

public interface Stack<E> {

	/**
	 * Consulta la cantidad de elementos de la pila.
	 * @return Cantidad de elementos de la pila.
	 */
	public int size();

	/**
	 * Consulta si la pila est� vac�a.
	 * @return Verdadero si la pila est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty();

	/**
	 * Examina el elemento que se encuentra en el tope de la pila.
	 * @return Elemento que se encuentra en el tope de la pila.
	 * @throws EmptyStackException si la pila est� vac�a. 
	 */
	public E top()throws EmptyStackException;

	/**
	 * Inserta un elemento en el tope de la pila.
	 * @param element Elemento a insertar.
	 */
	public void push(E element);

	/**
	 * Remueve el elemento que se encuentra en el tope de la pila.
	 * @return Elemento removido.
	 * @throws EmptyStackException si la pila est� vac�a. 
	 */
	public E pop() throws EmptyStackException;
}