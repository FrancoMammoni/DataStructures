package TDAArbol;
import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyTreeException;
import Excepciones.InvalidOperationException;
import Excepciones.InvalidPositionException;
import TDALista.Position;

public interface Tree<E> extends Iterable<E>
{
	/**
	 * Consulta la cantidad de nodos en el �rbol.
	 * @return Cantidad de nodos en el �rbol.
	 */
	public int size();
	
	/**
	 * Consulta si el �rbol est� vac�o.
	 * @return Verdadero si el �rbol est� vac�o, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve un iterador de los elementos almacenados en el �rbol en preorden.
	 * @return Iterador de los elementos almacenados en el �rbol.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una colecci�n iterable de las posiciones de los nodos del �rbol.
	 * @return Colecci�n iterable de las posiciones de los nodos del �rbol.
	 */
	public Iterable<Position<E>> positions();
	
	/**
	 * Reemplaza el elemento almacenado en la posici�n dada por el elemento pasado por par�metro. Devuelve el elemento reemplazado.
	 * @param v Posici�n de un nodo.
	 * @param e Elemento a reemplazar en la posici�n pasada por par�metro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException;
	
	/**
	 * Devuelve la posici�n de la ra�z del �rbol.
	 * @return Posici�n de la ra�z del �rbol.
	 * @throws EmptyTreeException si el �rbol est� vac�o.
	 */
	public Position<E> root() throws EmptyTreeException;
	
	/**
	 * Devuelve la posici�n del nodo padre del nodo correspondiente a una posici�n dada.
	 * @param v Posici�n de un nodo.
	 * @return Posici�n del nodo padre del nodo correspondiente a la posici�n dada.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde a la ra�z del �rbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve una colecci�n iterable de los hijos del nodo correspondiente a una posici�n dada.
	 * @param v Posici�n de un nodo.
	 * @return Colecci�n iterable de los hijos del nodo correspondiente a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n corresponde a un nodo interno.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n dada corresponde a un nodo externo.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Consulta si una posici�n dada corresponde a la ra�z del �rbol.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero, si la posici�n pasada por par�metro corresponde a la ra�z del �rbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException;
	
	/**
	 * Crea un nodo con r�tulo e como ra�z del �rbol.
	 * @param E R�tulo que se asignar� a la ra�z del �rbol.
	 * @throws InvalidOperationException si el �rbol ya tiene un nodo ra�z.
	 */
	public void createRoot(E e) throws InvalidOperationException;
	
	/**
	 * Agrega un nodo con r�tulo e como primer hijo de un nodo dado.
	 * @param e R�tulo del nuevo nodo.
	 * @param padre Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws	InvalidPositionException;
	
	/**
	 * Agrega un nodo con r�tulo e como �timo hijo de un nodo dado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
	 */
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException;
	
	/**
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre dado. El nuevo nodo se agregar� delante de otro nodo tambi�n dado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @param rb Posici�n del nodo que ser� el hermano derecho del nuevo nodo.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida, o el �rbol est� vac�o, o la posici�n rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException;
	
	/**
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre dado. El nuevo nodo se agregar� a continuaci�n de otro nodo tambi�n dado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @param lb Posici�n del nodo que ser� el hermano izquierdo del nuevo nodo.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida, o el �rbol est� vac�o, o la posici�n lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posici�n dada, si se trata de un nodo externo. 
	 * @param n Posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o no corresponde a un nodo externo, o el �rbol est� vac�o.
	 */
	public void removeExternalNode (Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posici�n dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la ra�z del �rbol,  �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param n Posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o no corresponde a un nodo interno o corresponde a la ra�z (con m�s de un hijo), o el �rbol est� vac�o.
	 */
	public void removeInternalNode (Position<E> p) throws InvalidPositionException;
	
	/**
	 * Elimina el nodo referenciado por una posici�n dada. Si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la ra�z del �rbol,  �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param n Posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o corresponde a la ra�z (con m�s de un hijo), o el �rbol est� vac�o.
	 */
	public void removeNode (Position<E> p) throws InvalidPositionException;
	
	
	

}